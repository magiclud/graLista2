import java.util.ArrayList;


public class Checker {

	private boolean checkIfStraightFlash(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);

		if (pierwszaKarta.getCourt().ordinal() + 1 == drugaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 2 == trzeciaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 3 == czwartaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 4 == piataKarta.getCourt().ordinal()) {
			if (pierwszaKarta.getSuit().equals(drugaKarta.getSuit()) && drugaKarta.getSuit().equals(trzeciaKarta.getSuit())
					&& trzeciaKarta.getSuit().equals(czwartaKarta.getSuit()) && czwartaKarta.getSuit().equals(piataKarta.getSuit())) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfFourOfAKing(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()) {
			return true;
		}
		if (drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFullHouse(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& drugaKarta.getCourt().ordinal() == trzeciaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		if (pierwszaKarta.getCourt().ordinal() == drugaKarta.getCourt().ordinal()
				&& trzeciaKarta.getCourt().ordinal() == czwartaKarta.getCourt().ordinal()
				&& czwartaKarta.getCourt().ordinal() == piataKarta.getCourt().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfFlush(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getSuit().ordinal() == drugaKarta.getSuit().ordinal()
				&& drugaKarta.getSuit().ordinal() == trzeciaKarta.getSuit().ordinal()
				&& trzeciaKarta.getSuit().ordinal() == czwartaKarta.getSuit().ordinal()
				&& czwartaKarta.getSuit().ordinal() == piataKarta.getSuit().ordinal()) {
			return true;
		}
		return false;
	}

	private boolean checkIfStrit(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getCourt().ordinal() + 1 == drugaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 2 == trzeciaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 3 == czwartaKarta.getCourt().ordinal()
				&& pierwszaKarta.getCourt().ordinal() + 4 == piataKarta.getCourt().ordinal()) {
			return true;
		}
		if (pierwszaKarta.getCourt() == CourtEnum.TWO && drugaKarta.getCourt() == CourtEnum.THREE
				&& trzeciaKarta.getCourt() == CourtEnum.FOUR && czwartaKarta.getCourt() == CourtEnum.FIVE
				&& piataKarta.getCourt() == CourtEnum.ACE) {
			return true;
		}
		return false;
	}

	private boolean checkIfThreeOfAKind(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getCourt().equals(drugaKarta.getCourt()) && drugaKarta.getCourt().equals(trzeciaKarta.getCourt())) {
			return true;
		}
		if (drugaKarta.getCourt().equals(trzeciaKarta.getCourt()) && trzeciaKarta.getCourt().equals(czwartaKarta.getCourt())) {
			return true;
		}
		if (trzeciaKarta.getCourt().equals(czwartaKarta.getCourt()) && czwartaKarta.getCourt().equals(piataKarta.getCourt())) {
			return true;
		}
		return false;
	}

	private boolean checkIfTwoPair(ArrayList<Card> cardsToTest) {
		Card pierwszaKarta = cardsToTest.get(0);
		Card drugaKarta = cardsToTest.get(1);
		Card trzeciaKarta = cardsToTest.get(2);
		Card czwartaKarta = cardsToTest.get(3);
		Card piataKarta = cardsToTest.get(4);
		if (pierwszaKarta.getCourt().equals(drugaKarta.getCourt()) || drugaKarta.getCourt().equals(trzeciaKarta.getCourt())
				|| trzeciaKarta.getCourt().equals(czwartaKarta.getCourt()) || czwartaKarta.getCourt().equals(piataKarta.getCourt())) {
			return true;
		}
		return false;
	}

	public SequenceEnum checkScore(ArrayList<Card> cardsToTest) {
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
		if (checkIfStrit(cardsToTest)) {
			return SequenceEnum.STRAIGHT;
		}
		if (checkIfThreeOfAKind(cardsToTest)) {
			return SequenceEnum.TREE_OF_A_KIND;
		}
		if (checkIfTwoPair(cardsToTest)) {
			return SequenceEnum.TWO_PAIR;
		}
		return SequenceEnum.HIGH_CARD;
	}

}
