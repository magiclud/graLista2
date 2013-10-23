import java.util.Collections;
import java.util.Comparator;
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
	
	// // żądanie kart od stołu, tutaj kontrolujemy wyjątkami liczbę arraylist
	// itemków i numbOfCards żądanych !
	// // i w ogóle tylko od 1 do 4 wymieniamy !
	// public void requestCards(int numbOfCards, List cardsToReturn) {
	// /**
	// * numbOfCards jest nie potrzebny bo usuwam tyle kart ile jest na liscie
	// **/
	// // Usuwa stare karty
	// for (int i = 0; i < cardsToReturn.size(); ++i) {
	// ownCards.remove(cardsToReturn.get(i));
	// }
	// // Pobiera nowe karty
	// List<Card> tempCards = currentTable.giveCards(numbOfCards);
	// for(int i = 0; i < tempCards.size(); ++i) {
	// ownCards.add(tempCards.get(i));
	// }
	// // Sortuje karty
	// sort(ownCards);
	// }
	
	/**
	 * metoda powyzej triche zmieniona
	 **/
	public void changeCards(List indexesOfAbandonedCards) {

		int numberCardsToReturn = indexesOfAbandonedCards.size();

		if (numberCardsToReturn > 4) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}

		/** usuwam karty po indeksie **/
		for (int i = 0; i < numberCardsToReturn; ++i) {
			ownCards.remove(indexesOfAbandonedCards.get(i));
		}
		// Pobiera nowe karty
		List<Card> tempCards = currentTable.giveCards(numberCardsToReturn);
		for (int i = 0; i < tempCards.size(); ++i) {
			ownCards.add(tempCards.get(i));
		}
		// Sortuje karty
		ownSorter.sort(ownCards);
	}

	// Przeraża mnie ta ilość metod na dole !
/*
<<<<<<< .mine
	
=======
	public void sort(List<Card> ownCards2) {
		// Collections.sort(cardsToSort);
		Collections.sort(ownCards2, new cardsSuitComparator());
		Collections.sort(ownCards2, new cardsCourtComparator());

	}

	class cardsCourtComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getCourt().ordinal() - card2.getCourt().ordinal();
		}
	}
>>>>>>> .r21

	// Zrobiłem nową klasę checker
	// Zrobiłem nową klasę sorter
	

<<<<<<< .mine
	
	public ArrayList<Card> getOwnCards() {
=======
	public List<Card> getOwnCards() {
>>>>>>> .r21
		return ownCards;
	}

<<<<<<< .mine
	
// depriciated :D zamieniłem to z metodą requestCards :D
//	public void chooseCardsToExchange() {
=======
	private boolean checkIfStraightFlash() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);

		if (pierwszaKarta.getCourt().ordinal() + 1 == drugaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 2 == trzeciaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 3 == czwartaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 4 == piataKarta.getCourt().ordinal()) {
			if (pierwszaKarta.getSuit().equals(drugaKarta.getSuit()) && drugaKarta.getSuit().equals(trzeciaKarta.getSuit())
					&& trzeciaKarta.getSuit().equals(czwartaKarta.getSuit()) && czwartaKarta.getSuit().equals(piataKarta.getSuit())) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfFourOfAKing() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()) {
			return true;
		}
		if (drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFullHouse() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFlush() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getSuit().ordinal() == drugaKarta.getSuit().ordinal()
				&& drugaKarta.getSuit().ordinal() == trzeciaKarta.getSuit().ordinal()
				&& trzeciaKarta.getSuit().ordinal() == czwartaKarta.getSuit().ordinal()
				&& czwartaKarta.getSuit().ordinal() == piataKarta.getSuit().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfStrit() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getCourt().ordinal() + 1 == drugaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 2 == trzeciaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 3 == czwartaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 4 == piataKarta.getCourt().ordinal()) {
			return true;
		}
		if (pierwszaKarta.getCourt() == CourtEnum.TWO && drugaKarta.getCourt() == CourtEnum.THREE
				&& trzeciaKarta.getCourt() == CourtEnum.FOUR && czwartaKarta.getCourt() == CourtEnum.FIVE
				&& piataKarta.getCourt() == CourtEnum.ACE) {
			return true;
		}
		return false;
	}

	private boolean checkIfThreeOfAKind() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getCourt().equals(drugaKarta.getCourt()) && drugaKarta.getCourt().equals(trzeciaKarta.getCourt())) {
			return true;
		}
		if (drugaKarta.getCourt().equals(trzeciaKarta.getCourt()) && trzeciaKarta.getCourt().equals(czwartaKarta.getCourt())) {
			return true;
		}
		if (trzeciaKarta.getCourt().equals(czwartaKarta.getCourt()) && czwartaKarta.getCourt().equals(piataKarta.getCourt())) {
			return true;
		}
		return false;
	}

	private boolean checkIfTwoPair() {
		Card pierwszaKarta = ownCards.get(0);
		Card drugaKarta = ownCards.get(1);
		Card trzeciaKarta = ownCards.get(2);
		Card czwartaKarta = ownCards.get(3);
		Card piataKarta = ownCards.get(4);
		if (pierwszaKarta.getCourt().equals(drugaKarta.getCourt()) || drugaKarta.getCourt().equals(trzeciaKarta.getCourt())
				|| trzeciaKarta.getCourt().equals(czwartaKarta.getCourt()) || czwartaKarta.getCourt().equals(piataKarta.getCourt())) {
			return true;
		}
		return false;
	}

	public SequenceEnum checkScore() {
		if (checkIfStraightFlash()) {
			return SequenceEnum.STRAIGHT_TFLUSH;
		}
		if (checkIfFourOfAKing()) {
			return SequenceEnum.FOUR_OF_A_KIND;
		}
		if (checkIfFullHouse()) {
			return SequenceEnum.FULL_HOUSE;
		}
		if (checkIfFlush()) {
			return SequenceEnum.FLUSH;
		}
		if (checkIfStrit()) {
			return SequenceEnum.STRAIGHT;
		}
		if (checkIfThreeOfAKind()) {
			return SequenceEnum.TREE_OF_A_KIND;
		}
		if (checkIfTwoPair()) {
			return SequenceEnum.TWO_PAIR;
		}
		return SequenceEnum.HIGH_CARD;
	}

	// public void chooseCardsToExchange() {
>>>>>>> .r21
		// TODO

<<<<<<< .mine
//	}
=======
	// }
>>>>>>> .r21*/
}
