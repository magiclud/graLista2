import java.util.List;


public class Human extends Player {

	private int numberCardsToExchange = 3;
	private List someNewCards;

	public Human(List<Card> giveCards) {
		super(giveCards);
	}


	public void selectCardsToExchange() {
		// przykladowo wybieram sobie karty do wymiany

		Deck newCards = new Deck();
		ownCards.remove(2);
		ownCards.remove(3);
		ownCards.remove(5);
		someNewCards = newCards.giveCards(numberCardsToExchange);
		ownCards.addAll(someNewCards);

	}

}
