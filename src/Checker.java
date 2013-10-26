import java.util.List;


public class Checker {

	private boolean checkIfStraightFlash(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// piec kolejnych kart w jednym kolorze
		if (firstCard.getCourt().ordinal() + 1 == secondCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 2 == thirdCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 3 == fourthCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 4 == fifthCard.getCourt().ordinal()) {
			if (firstCard.getSuit().equals(secondCard.getSuit()) && secondCard.getSuit().equals(thirdCard.getSuit())
					&& thirdCard.getSuit().equals(fourthCard.getSuit()) && fourthCard.getSuit().equals(fifthCard.getSuit())) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfFourOfAKing(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// cztery karty o jednakowej wartosci
		if (firstCard.getCourt().ordinal() == secondCard.getCourt().ordinal()
				&& secondCard.getCourt().ordinal() == thirdCard.getCourt().ordinal()
				&& thirdCard.getCourt().ordinal() == fourthCard.getCourt().ordinal()) {
			return true;
		}
		if (secondCard.getCourt().ordinal() == thirdCard.getCourt().ordinal()
				&& thirdCard.getCourt().ordinal() == fourthCard.getCourt().ordinal()
				&& fourthCard.getCourt().ordinal() == fifthCard.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFullHouse(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// trzy karty o jednakowej wartosci i para
		if (firstCard.getCourt().ordinal() == secondCard.getCourt().ordinal()
				&& secondCard.getCourt().ordinal() == thirdCard.getCourt().ordinal()
				&& fourthCard.getCourt().ordinal() == fifthCard.getCourt().ordinal()) {
			return true;
		}
		if (firstCard.getCourt().ordinal() == secondCard.getCourt().ordinal()
				&& thirdCard.getCourt().ordinal() == fourthCard.getCourt().ordinal()
				&& fourthCard.getCourt().ordinal() == fifthCard.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFlush(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// piec kart w jednym kolorze
		if (firstCard.getSuit().ordinal() == secondCard.getSuit().ordinal()
				&& secondCard.getSuit().ordinal() == thirdCard.getSuit().ordinal()
				&& thirdCard.getSuit().ordinal() == fourthCard.getSuit().ordinal()
				&& fourthCard.getSuit().ordinal() == fifthCard.getSuit().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfStraight(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// piec kolejnych kart
		if (firstCard.getCourt().ordinal() + 1 == secondCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 2 == thirdCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 3 == fourthCard.getCourt().ordinal()
				&& firstCard.getCourt().ordinal() + 4 == fifthCard.getCourt().ordinal()) {
			return true;
		}
		if (firstCard.getCourt() == CourtEnum.TWO && secondCard.getCourt() == CourtEnum.THREE
				&& thirdCard.getCourt() == CourtEnum.FOUR && fourthCard.getCourt() == CourtEnum.FIVE
				&& fifthCard.getCourt() == CourtEnum.ACE) {
			return true;
		}
		return false;
	}

	private boolean checkIfThreeOfAKind(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// trzy karty o jednakowej wartosci
		if (firstCard.getCourt().equals(secondCard.getCourt()) && secondCard.getCourt().equals(thirdCard.getCourt())) {
			return true;
		}
		if (secondCard.getCourt().equals(thirdCard.getCourt()) && thirdCard.getCourt().equals(fourthCard.getCourt())) {
			return true;
		}
		if (thirdCard.getCourt().equals(fourthCard.getCourt()) && fourthCard.getCourt().equals(fifthCard.getCourt())) {
			return true;
		}
		return false;
	}

	private boolean checkIfTwoPair(List<Card> cardsToTest) {
		return false;
		// TODO
	}

	private boolean checkIfOnePair(List<Card> cardsToTest) {
		Card firstCard = cardsToTest.get(0);
		Card secondCard = cardsToTest.get(1);
		Card thirdCard = cardsToTest.get(2);
		Card fourthCard = cardsToTest.get(3);
		Card fifthCard = cardsToTest.get(4);
		// dwie karty o jednakowej wartosci
		if (firstCard.getCourt().equals(secondCard.getCourt()) || secondCard.getCourt().equals(thirdCard.getCourt())
				|| thirdCard.getCourt().equals(fourthCard.getCourt()) || fourthCard.getCourt().equals(fifthCard.getCourt())) {
			return true;
		}
		return false;
	}

	public SequenceEnum checkScore(List<Card> cardsToTest) {
		if (checkIfStraightFlash(cardsToTest)) {
			return SequenceEnum.STRAIGHT_TFLUSH;
		}
		if (checkIfFourOfAKing(cardsToTest)) {
			return SequenceEnum.FOUR_OF_A_KIND;
		}
		if (checkIfFullHouse(cardsToTest)) {
			return SequenceEnum.FULL_HOUSE;
		}
		if (checkIfFlush(cardsToTest)) {
			return SequenceEnum.FLUSH;
		}
		if (checkIfStraight(cardsToTest)) {
			return SequenceEnum.STRAIGHT;
		}
		if (checkIfThreeOfAKind(cardsToTest)) {
			return SequenceEnum.TREE_OF_A_KIND;
		}
		// TODO Two_pair
		if (checkIfOnePair(cardsToTest)) {
			return SequenceEnum.ONE_PAIR;
		}
		return SequenceEnum.HIGH_CARD;
	}

}
