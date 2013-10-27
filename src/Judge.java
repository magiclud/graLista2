import java.util.List;


public class Judge {

	List<List<Card>> playersCards;

	boolean scoreFirst;
	boolean scoreSecond;
	boolean scoreThird;
	boolean scoreFourth;

	Table collectCards = new Table(0, 0);// TODO co z tym Table

	boolean[] score = { scoreFirst, scoreSecond, scoreThird, scoreFourth };

	Checker check = new Checker();

	// public Judge(List<List<Card> players) { or
	public Judge(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			playersCards.add(collectCards.getPlayersCards(i));
		compareResult();
		}
	}

	public void compareResult() {

	}

}
