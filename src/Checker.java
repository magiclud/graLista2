import java.util.List;


public class Checker {

	public static Card selectHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza karta to piata karta
		return cardsToTest.get(4);
	}

	public static Card selectHighestFromFourOfAKind(List<Card> cardsToTest) {
		// pierwsze cztery karty tworza czworke, wiec 4-ta karta ma najwyzsza
		// wartosc
		if (cardsToTest.get(0).getCourt().ordinal() == cardsToTest.get(1).getCourt().ordinal()) {
			return cardsToTest.get(3);
		}// jesli nie, to pierwsza karta nie tworzy czorki
			// najwyzsza karta to piata karta
		return cardsToTest.get(4);
	}

	public static Card selectHighestFromThree(List<Card> cardsToTest) {
		// pierwsze trzy karty tworza 'trojke'
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())) {
			// 3-cia karta to najwyzsza wsrod tych 'trojek'
			return cardsToTest.get(2);
		}
		// 'trojke' tworzy 2-ga, 3-cia, 4-ta karta', najwyzsza wsrod nich to
		// 4-ta
		if (cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())
				&& cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(3);
		}// jesli nie to trojke tworza ostatnie 3 karty
		return cardsToTest.get(4);
	}

	public static Card selectHighestFromTwoPair(List<Card> cardsToTest) {
		// dwojke tworzy 1.2. karta i 3.4.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(3);
		}// 1.2. i 4.5.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(3).getCourt().equals(cardsToTest.get(4).getCourt())) {
			return cardsToTest.get(4);
		}// dwojke tworzy 2.3. i 4.5
		return cardsToTest.get(4);
	}

	public static Card slectSecondHighestFromTwoPair(List<Card> cardsToTest) {
		// dwojke tworzy 1.2. karta i 3.4.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(3);
		}// 1.2. i 4.5.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(3).getCourt().equals(cardsToTest.get(4).getCourt())) {
			return cardsToTest.get(4);
		}// dwojke tworzy 2.3. i 4.5
		return cardsToTest.get(4);
	}

	public static Card selectHighestFromOnePair(List<Card> cardsToTest) {
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())) {
			return cardsToTest.get(1);
		}
		if (cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())) {
			return cardsToTest.get(2);
		}
		if (cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(3);
		}
		return cardsToTest.get(4);
	}

	private static boolean checkIfStraightFlash(List<Card> cardsToTest) {
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

	private static boolean checkIfFourOfAKing(List<Card> cardsToTest) {
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

	private static boolean checkIfFullHouse(List<Card> cardsToTest) {
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

	private static boolean checkIfFlush(List<Card> cardsToTest) {
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

	private static boolean checkIfStraight(List<Card> cardsToTest) {
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

	private static boolean checkIfThreeOfAKind(List<Card> cardsToTest) {
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

	private static boolean checkIfTwoPair(List<Card> cardsToTest) {
		return false;
		// TODO
	}

	private static boolean checkIfOnePair(List<Card> cardsToTest) {
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

	public static SequenceEnum checkScore(List<Card> cardsToTest) {
		if (checkIfStraightFlash(cardsToTest)) {
			return SequenceEnum.STRAIGHT_FLUSH;
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
