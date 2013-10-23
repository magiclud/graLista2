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

	
	/**
	 * metoda powyzej triche zmieniona
	 **/
	public void changeCards(List cardsToReturn) {

		int numberCardsToReturn = cardsToReturn.size();

		if (numberCardsToReturn > 4) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}

		/** usuwam karty po indeksie **/
		for (int i = 0; i < numberCardsToReturn; ++i) {
			ownCards.remove(cardsToReturn.get(i));
		}
		// Pobiera nowe karty
		List<Card> tempCards = currentTable.giveCards(numberCardsToReturn);
		for (int i = 0; i < tempCards.size(); ++i) {
			ownCards.add(tempCards.get(i));
		}
		// Sortuje karty
		ownSorter.sort(ownCards);
	}


}
