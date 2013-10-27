import java.util.List;


public class Judge {

	List<List<Card>> playersCards;

	List<Player> indexesOfWinnners;// tu beda indeksy tych graczy ktorzy wygrali
	// boolean scoreFirst;
	// boolean scoreSecond;
	// boolean scoreThird;
	// boolean scoreFourth;
	SequenceEnum scoreFirst;
	SequenceEnum scoreSecond;
	SequenceEnum scoreThird;
	SequenceEnum scoreFourth;

	Table collectCards = new Table(4, 0);// TODO co z tym Table

	// boolean[] score = { scoreFirst, scoreSecond, scoreThird, scoreFourth };

	Checker check = new Checker();

	public Judge(List<List<Card>> playersCards) {
		this.playersCards = collectCards.endOfGame;
		selectWinners();
		}


	public void compareResult() {
	}

	public List<Player> selectWinners() {

		scoreFirst = check.checkScore(playersCards.get(0));
		scoreSecond = check.checkScore(playersCards.get(1));
		scoreThird = check.checkScore(playersCards.get(2));
		scoreFourth = check.checkScore(playersCards.get(3));

		if (scoreFirst.ordinal() > scoreSecond.ordinal() && scoreFirst.ordinal() > scoreThird.ordinal()
				&& scoreFirst.ordinal() > scoreFourth.ordinal()) {
			indexesOfWinnners.add(collectCards.players.get(0));
		}
		if (scoreSecond.ordinal() > scoreFirst.ordinal() && scoreSecond.ordinal() > scoreThird.ordinal()
				&& scoreSecond.ordinal() > scoreFourth.ordinal()) {
			indexesOfWinnners.add(collectCards.players.get(1));
		}
		if (scoreThird.ordinal() > scoreFirst.ordinal() && scoreThird.ordinal() > scoreSecond.ordinal()
				&& scoreThird.ordinal() > scoreFourth.ordinal()) {
			indexesOfWinnners.add(collectCards.players.get(2));
		}
		if (scoreFourth.ordinal() > scoreFirst.ordinal() && scoreFourth.ordinal() > scoreSecond.ordinal()
				&& scoreFourth.ordinal() > scoreThird.ordinal()) {
			indexesOfWinnners.add(collectCards.players.get(3));
		}
		return indexesOfWinnners;

	}
}
