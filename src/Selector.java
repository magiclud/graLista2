import java.util.List;

public class Selector {

	public static Card selectHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza karta to piata karta
		return cardsToTest.get(4);
	}

	public static Card selectSecondHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza druga karta to czwarta karta
		return cardsToTest.get(3);
	}

	public static Card selectThirdHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza trzecia karta
		return cardsToTest.get(2);
	}

	public static Card selectFourthHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza czwarta karta
		return cardsToTest.get(1);
	}

	public static Card selectFifthHighestFromFlushOrStraight(List<Card> cardsToTest) {
		// najwyzsza piata karta
		return cardsToTest.get(0);
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

	public static Card selectHighestFromFullHouse(List<Card> cardsToTest) {
		// jedna z kart tworzacych trojke zawsze bedzie na miejscu trzecim
		return cardsToTest.get(2);
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

	public static Card selectSecondHighestFromTwoPair(List<Card> cardsToTest) {
		// dwojke tworzy 1.2. karta i 3.4.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(1);
		}// 1.2. i 4.5.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(3).getCourt().equals(cardsToTest.get(4).getCourt())) {
			return cardsToTest.get(1);
		}// dwojke tworzy 2.3. i 4.5
		return cardsToTest.get(2);
	}

	public static Card selectKickerFromTwoPair(List<Card> cardsToTest) {
		// dwojke tworzy 1.2. karta i 3.4.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(4);
		}// 1.2. i 4.5.
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())
				&& cardsToTest.get(3).getCourt().equals(cardsToTest.get(4).getCourt())) {
			return cardsToTest.get(2);
		}// dwojke tworzy 2.3. i 4.5
		return cardsToTest.get(0);
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

	public static Card selectSecondHighestFromOnePair(List<Card> cardsToTest) {
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())) {
			return cardsToTest.get(4);
		}
		if (cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())) {
			return cardsToTest.get(4);
		}
		if (cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(4);
		}
		return cardsToTest.get(2);
	}

	public static Card selectThirdHighestFromOnePair(List<Card> cardsToTest) {
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())) {
			return cardsToTest.get(3);
		}
		if (cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())) {
			return cardsToTest.get(3);
		}
		if (cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(0);
		}
		return cardsToTest.get(1);
	}

	public static Card selectFourthHighestFromOnePair(List<Card> cardsToTest) {
		if (cardsToTest.get(0).getCourt().equals(cardsToTest.get(1).getCourt())) {
			return cardsToTest.get(2);
		}
		if (cardsToTest.get(1).getCourt().equals(cardsToTest.get(2).getCourt())) {
			return cardsToTest.get(0);
		}
		if (cardsToTest.get(2).getCourt().equals(cardsToTest.get(3).getCourt())) {
			return cardsToTest.get(0);
		}
		return cardsToTest.get(0);
	}

	public static Card selectHighestFromHighCard(List<Card> cardsToTest) {
		// najwyzsza karta to piata karta
		return cardsToTest.get(4);
	}

	public static Card selectSecondHighestFromHighCard(List<Card> cardsToTest) {
		// najwyzsza druga karta to czwarta karta
		return cardsToTest.get(3);
	}

	public static Card selectThirdHighestFromHighCard(List<Card> cardsToTest) {
		// najwyzsza trzecia karta
		return cardsToTest.get(2);
	}

	public static Card selectFourthHighestFromHighCard(List<Card> cardsToTest) {
		// najwyzsza czwarta karta
		return cardsToTest.get(1);
	}

	public static Card selectFifthHighestFromHighCard(List<Card> cardsToTest) {
		// najwyzsza piata karta
		return cardsToTest.get(0);
	}

}
