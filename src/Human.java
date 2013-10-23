import java.util.ArrayList;


public class Human extends Player {

	ArrayList cardsToReturn = new ArrayList();


	public Human(ArrayList<Card> givenCards, Table currentTable) {
		super(givenCards, currentTable);
	}

	ArrayList<Card> joinGame() {
		Player cardsToGiveBack = new Human(cardsToReturn, currentTable);
		cardsToGiveBack.requestCards(3, cardsToReturn);
		return null;
	}

	public ArrayList getCardsToReturn() {
		return cardsToReturn;
	}

	public void setCardsToReturn(ArrayList cardsToReturn) {
		this.cardsToReturn.add(ownCards.get(1));
		this.cardsToReturn.add(ownCards.get(2));
		this.cardsToReturn.add(ownCards.get(4));
	}


}
