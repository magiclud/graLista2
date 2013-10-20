import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
	
	ArrayList<Card> ownCards;



	public Player(ArrayList<Card> givenCards) { // Player ma przecież dostawać karty od stołu !
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

	public void chooseCardsToExchange() {

	}
}
