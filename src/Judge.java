import java.util.List;


public class Judge {

	List<Card> firstPlayersCards;
	List<Card> secondPlayersCards;
	List<Card> thirdPlayersCards;
	List<Card> fourthPlayersCards;
	boolean scoreFirst;
	boolean scoreSecond;
	boolean scoreThird;
	boolean scoreFourth;

	Table collectCards = new Table(0, 0);// TODO co z tym Table

	boolean[] score = { scoreFirst, scoreSecond, scoreThird, scoreFourth };

	Checker check = new Checker();

	// public Judge(List<List<Card> players) { or
	public Judge(List<Player> players) {
		firstPlayersCards = collectCards.getPlayersCards(0);
		secondPlayersCards = collectCards.getPlayersCards(1);
		thirdPlayersCards = collectCards.getPlayersCards(2);
		fourthPlayersCards = collectCards.getPlayersCards(3);
		compareResult();
	}

	public void compareResult() {

	}

}
