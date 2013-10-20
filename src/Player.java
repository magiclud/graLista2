import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {

	ArrayList<Card> ownCards;

	public Player(ArrayList<Card> givenCards) { // Player ma przecież dostawać
												// karty od stołu !
		if (givenCards.size() != 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.ownCards = givenCards;

		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		sort(givenCards);
	}

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

	class cardsSuitComparator implements Comparator<Card> {
		public int compare(Card card1, Card card2) {
			return card1.getSuit().ordinal() - card2.getSuit().ordinal();
		}
	}

	public ArrayList<Card> getOwnCards() {
		return ownCards;
	}

	public boolean checkIfStraightFlash() {
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

	public boolean checkIfFourOfAKing() {
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

	public boolean checkIfFullHouse() {
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

	public boolean checkIfFlush() {
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

	public boolean checkIfStrit() {
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
		return false;
	}

	public boolean checkIfThreeOfAKind() {
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

	public boolean checkIfTwoPair(){
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


	public void chooseCardsToExchange() {


	}
}
