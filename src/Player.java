import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Player {

	ArrayList<Card> ownCards;
	Table currentTable;

	public Player(ArrayList<Card> givenCards, Table currentTable) { // Player ma przecież dostawać
												// karty od stołu !
		if (givenCards.size() != 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.ownCards = givenCards;
		this.currentTable = currentTable;
		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		sort(ownCards); // było sort(givenCards) 
		// W sensie, że sortowanie to układanie kart na ręku "po kolei" ????????//
	}
	
	abstract ArrayList<Card> joinGame(); // Jak human albo bot będzie sobie grał
	
	// żądanie kart od stołu, tutaj kontrolujemy wyjątkami liczbę arraylist itemków i numbOfCards żądanych !
	// i w ogóle tylko od 1 do 4 wymieniamy !
	void requestCards(int numbOfCards, ArrayList<Card> abandonedCards ) {
		
		// Usuwa stare karty
		for(int i = 0; i < abandonedCards.size(); ++i) { 
			ownCards.remove(abandonedCards.get(i));
		}
		// Pobiera nowe karty
		List<Card> tempCards = currentTable.giveCards(numbOfCards);
		for(int i = 0; i < tempCards.size(); ++i) {
			ownCards.add(tempCards.get(i));
		}
		// Sortuje karty
		sort(ownCards);
	}
	
	// Przeraża mnie ta ilość metod na dole !

	public void sort(ArrayList<Card> cardsToSort) {
		// Collections.sort(cardsToSort);
		Collections.sort(cardsToSort, new cardsSuitComparator());
		Collections.sort(cardsToSort, new cardsCourtComparator());

	}

	class cardsCourtComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getCourt().ordinal() - card2.getCourt().ordinal();
		}
	}

	// Ja w ogóle bym zrobił nową klasę COMPARATOR jakiś i CHECKER JAKIŚ 
	class cardsSuitComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getSuit().ordinal() - card2.getSuit().ordinal();
		}
	}

	public ArrayList<Card> getOwnCards() {
		return ownCards;
	}

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

	public void chooseCardsToExchange() {
		// TODO

	}
}
