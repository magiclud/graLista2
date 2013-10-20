import java.util.ArrayList;

public class Player {
	
	ArrayList<Card> ownCards;


	public Player(ArrayList<Card> givenCards) { // Player ma przecież dostawać karty od stołu !
		if (givenCards.size() > 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.ownCards = givenCards;
	}

	
}
