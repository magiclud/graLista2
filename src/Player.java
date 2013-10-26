import java.util.List;

public abstract class Player {

	List<Card> ownCards;
	Table currentTable;
	Sorter ownSorter = new Sorter();

	public Player(List<Card> givenCards, Table currentTable) { // Player ma
																// przecież
																// dostawać
												// karty od stołu !
		if (givenCards.size() != 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.ownCards = givenCards;
		this.currentTable = currentTable;
		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		ownSorter.sort(ownCards); // było sort(givenCards) 
		// W sensie, że sortowanie to układanie kart na ręku "po kolei" ????????//
	}
	
	abstract List<Card> joinGame(); // Jak human albo bot będzie sobie grał

	public SequenceEnum checkScore() {
		Checker check = new Checker();
		return check.checkScore(ownCards);
	
	}
	// // żądanie kart od stołu, tutaj kontrolujemy wyjątkami liczbę arraylist
	// itemków i numbOfCards żądanych !
	public void requestCards(List<Integer> abandonedIndexes) {

		int numberCardsToReturn = abandonedIndexes.size();

		if (numberCardsToReturn > 4) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}

		/** usuwam karty po indeksie **/
		for (int i = 0; i < numberCardsToReturn; ++i) {
			ownCards.remove(abandonedIndexes.get(i));
		}
		// Pobiera nowe karty
		List<Card> tempCards = currentTable.giveCards(abandonedIndexes.size());
		for (int i = 0; i < tempCards.size(); ++i) {
			ownCards.add(tempCards.get(i));
		}
		// Sortuje karty
		ownSorter.sort(ownCards);
	}

	public CourtEnum selectFirstFromFlushOrStraight(List<Card> flush) {
		// najwyzsza karta to piata karta
		return flush.get(4).getCourt();
	}

	public CourtEnum selectFirstFromKind(List<Card> kind) {
		// pierwsze cztery karty tworza czworke, wiec 4-ta karta ma najwyzsza
		// wartosc
		if (kind.get(0).getCourt().ordinal() == kind.get(1).getCourt().ordinal()) {
			return kind.get(3).getCourt();
		}// jesli nie, to pierwsza karta nie tworzy czorki
		// najwyzsza karta to piata karta
		return kind.get(4).getCourt();
	}

	public CourtEnum selectFirstFromThree(List<Card> three) {
		// pierwsze trzy karty tworza 'trojke'
		if (three.get(0).getCourt().equals(three.get(1).getCourt()) && three.get(1).getCourt().equals(three.get(2).getCourt())) {
			// 3-cia karta to najwyzsza wsrod tych 'trojek'
			return three.get(2).getCourt();
		}
		// 'trojke' tworzy 2-ga, 3-cia, 4-ta karta', najwyzsza wsrod nich to
		// 4-ta
		if (three.get(1).getCourt().equals(three.get(2).getCourt()) && three.get(2).getCourt().equals(three.get(3).getCourt())) {
			return three.get(3).getCourt();
		}// jesli nie to trojke tworza ostatnie 3 karty
		return three.get(4).getCourt();
	}

	public CourtEnum selectFirstFromTwoPair(List<Card> two) {
		// dwojke tworzy 1.2. karta i 3.4.
		if (two.get(0).getCourt().equals(two.get(1).getCourt()) && two.get(2).getCourt().equals(two.get(3).getCourt())) {
			return two.get(3).getCourt();
		}// 1.2. i 4.5.
		if (two.get(0).getCourt().equals(two.get(1).getCourt()) && two.get(3).getCourt().equals(two.get(4).getCourt())) {
			return two.get(4).getCourt();
		}// dwojke tworzy 2.3. i 4.5
		return two.get(4).getCourt();
	}

	public CourtEnum selectFirstFromOnePair(List<Card> one) {
		if (one.get(0).getCourt().equals(one.get(1).getCourt())) {
			return one.get(1).getCourt();
		}
		if (one.get(1).getCourt().equals(one.get(2).getCourt())) {
			return one.get(2).getCourt();
		}
		if (one.get(2).getCourt().equals(one.get(3).getCourt())) {
			return one.get(3).getCourt();
		}
		return one.get(4).getCourt();
	}

}
