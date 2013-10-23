import java.util.ArrayList;
import java.util.List;


public class Human extends Player {

	List cardsToReturn = new ArrayList();


	public Human(List someCards, Table currentTable) {
		super(someCards, currentTable);
	}

	ArrayList<Card> joinGame() {
		Player cardsToGiveBack = new Human(cardsToReturn, currentTable);
		cardsToGiveBack.changeCards(cardsToReturn);
		return null;
	}

	public List getCardsToReturn() {
		return cardsToReturn;
	}

	public void setCardsToReturn(List someCards) {
		this.cardsToReturn.add(ownCards.get(1));
		this.cardsToReturn.add(ownCards.get(2));
		this.cardsToReturn.add(ownCards.get(4));
	}


}
