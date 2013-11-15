import java.util.List;

public abstract class Player {

	protected List<Card> ownCards;
	private Table currentTable;
	private Sorter ownSorter = new Sorter();
	// private Boolean alreadyChangedCards = false;

	public int playerID;

	public Player(List<Card> givenCards, Table currentTable, int playerID) { // Player ma
																// przecież
																// dostawać
												// karty od stołu !
		if (givenCards.size() != 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.playerID = playerID;
		this.ownCards = givenCards;
		this.currentTable = currentTable;
		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		ownSorter.sort(ownCards); // było sort(givenCards)
		// W sensie, że sortowanie to układanie kart na ręku "po kolei" ????????//
	}

	public int getPlayerID() {
		return playerID;
	}
	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + ownCards.get(i).getCourt()  + ownCards.get(i).getSuit() + " " );
		}
	}

	abstract List<Card> joinGame(); // Jak human albo bot będzie sobie grał

	public SequenceEnum checkScore() {
		return Checker.checkScore(ownCards);
	}
	// // żądanie kart od stołu, tutaj kontrolujemy wyjątkami liczbę arraylist
	// itemków i numbOfCards żądanych !
	public void requestCards(List<Integer> abandonedIndexes) {

		List<Card> tempCards = currentTable.safeChangeCards(abandonedIndexes, this);

		// Sortuje karty
		this.ownCards = tempCards;
		ownSorter.sort(ownCards);

	}

	public List<Card> getOwnCards() {
		return ownCards;
	}

	// public Card selectHighestFromFlushOrStraight() {
	// // najwyzsza karta to piata karta
	// return ownCards.get(4);
	// }
	//
	// public Card selectHighestFromFourOfAKind() {
	// // pierwsze cztery karty tworza czworke, wiec 4-ta karta ma najwyzsza
	// // wartosc
	// if (ownCards.get(0).getCourt().ordinal() ==
	// ownCards.get(1).getCourt().ordinal()) {
	// return ownCards.get(3);
	// }// jesli nie, to pierwsza karta nie tworzy czorki
	// // najwyzsza karta to piata karta
	// return ownCards.get(4);
	// }
	//
	// public Card selectHighestFromThree() {
	// // pierwsze trzy karty tworza 'trojke'
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt()) &&
	// ownCards.get(1).getCourt().equals(ownCards.get(2).getCourt())) {
	// // 3-cia karta to najwyzsza wsrod tych 'trojek'
	// return ownCards.get(2);
	// }
	// // 'trojke' tworzy 2-ga, 3-cia, 4-ta karta', najwyzsza wsrod nich to
	// // 4-ta
	// if (ownCards.get(1).getCourt().equals(ownCards.get(2).getCourt()) &&
	// ownCards.get(2).getCourt().equals(ownCards.get(3).getCourt())) {
	// return ownCards.get(3);
	// }// jesli nie to trojke tworza ostatnie 3 karty
	// return ownCards.get(4);
	// }
	//
	// public Card selectHighestFromTwoPair() {
	// // dwojke tworzy 1.2. karta i 3.4.
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt()) &&
	// ownCards.get(2).getCourt().equals(ownCards.get(3).getCourt())) {
	// return ownCards.get(3);
	// }// 1.2. i 4.5.
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt()) &&
	// ownCards.get(3).getCourt().equals(ownCards.get(4).getCourt())) {
	// return ownCards.get(4);
	// }// dwojke tworzy 2.3. i 4.5
	// return ownCards.get(4);
	// }
	//
	// public Card slectSecondHighestFromTwoPair() {
	// // dwojke tworzy 1.2. karta i 3.4.
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt()) &&
	// ownCards.get(2).getCourt().equals(ownCards.get(3).getCourt())) {
	// return ownCards.get(3);
	// }// 1.2. i 4.5.
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt()) &&
	// ownCards.get(3).getCourt().equals(ownCards.get(4).getCourt())) {
	// return ownCards.get(4);
	// }// dwojke tworzy 2.3. i 4.5
	// return ownCards.get(4);
	// }
	//
	// public Card selectHighestFromOnePair() {
	// if (ownCards.get(0).getCourt().equals(ownCards.get(1).getCourt())) {
	// return ownCards.get(1);
	// }
	// if (ownCards.get(1).getCourt().equals(ownCards.get(2).getCourt())) {
	// return ownCards.get(2);
	// }
	// if (ownCards.get(2).getCourt().equals(ownCards.get(3).getCourt())) {
	// return ownCards.get(3);
	// }
	// return ownCards.get(4);
	// }

}
