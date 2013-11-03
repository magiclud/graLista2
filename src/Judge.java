import java.util.ArrayList;
import java.util.List;

public class Judge {

	public static List<Integer> selectWinners(List<Player> listOfPlayers) {

		int possibleWinnerIndex = possibleWinnerIndex(listOfPlayers);

		List<Integer> possibleWinnersIndexes = possibleWinnersIndexes(listOfPlayers, possibleWinnerIndex);

		// tutaj rozstrzygam wynik gdy najlepszy uklad kart powtarza sie wsrod
		// innych graczy
		if (possibleWinnersIndexes.size() > 1) {
			// wyszukuje jaki ukladbjest najlepszy
			SequenceEnum layout = listOfPlayers.get(possibleWinnersIndexes.get(0)).checkScore();

			// domyslnie ustalam ze pierwszy z graczy z wysokim ukladem ma
			// rowniez najlepsze karty
			Integer highest = possibleWinnersIndexes.get(0);

			// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
			// najwyzszych kartach
			List<Integer> indexesHighest = new ArrayList<>();

			// okreslam wynik pomiedzy graczami gdy maja okreslony uklad -
			// algorytm powtarza sie dla Staiht flush, straight
			if (layout.equals(SequenceEnum.STRAIGHT_FLUSH) || layout.equals(SequenceEnum.STRAIGHT)) {

				return findWinnerIfPlayerHaveStraightFlushOrStraight(possibleWinnersIndexes, listOfPlayers);
			}

			if (layout.equals(SequenceEnum.FOUR_OF_A_KIND)) {

				return findWinnerIfPlayerHaveFourOfAKind(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.FULL_HOUSE)) {

				return findWinnerIfPlayerHaveFullHouse(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.FLUSH)) {

				return findWinnerIfPlayerHaveFlush(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.THREE_OF_A_KIND)) {

				return findWinnerIfPlayerHaveThreeOfAKind(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.TWO_PAIR)) {

				return findWinnerIfPlayerHaveTwoPair(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.ONE_PAIR)) {

				return findWinnerIfPlayerHaveOnePair(possibleWinnersIndexes, listOfPlayers);
			}
			if (layout.equals(SequenceEnum.HIGH_CARD)) {

				return findWinnerIfPlayerHaveHighCard(possibleWinnersIndexes, listOfPlayers);
			}
		}
		return possibleWinnersIndexes;
	}

	private static List<Integer> possibleWinnersIndexes(List<Player> listOfPlayers, int possibleWinnerIndex) {
		List<Integer> possibleIndexesWinners = new ArrayList<>();
		possibleIndexesWinners.add(possibleWinnerIndex);
		// sprawdzam jeszcze raz czy ktos ma ten sam uklad
		SequenceEnum posiibleWinnerScore = listOfPlayers.get(possibleWinnerIndex).checkScore();
		for (int i = 0; i < listOfPlayers.size(); i++) {// dla enumow == to to
														// samo co equals
			if (posiibleWinnerScore == listOfPlayers.get(i).checkScore() && possibleWinnerIndex != i) {
				possibleIndexesWinners.add(i);
			}
		}
		return possibleIndexesWinners;
	}

	private static int possibleWinnerIndex(List<Player> listOfPlayers) {
		int possibleWinnerPlayerIndex = 0;
		// Najpierw wybieram gracza z najelpszym układem
		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			if (listOfPlayers.get(possibleWinnerPlayerIndex).checkScore().ordinal() < listOfPlayers.get(i + 1).checkScore().ordinal()) {
				possibleWinnerPlayerIndex = i + 1;
			}
		}
		return possibleWinnerPlayerIndex;
	}

	private static List<Integer> findWinnerIfPlayerHaveStraightFlushOrStraight(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			
			if (Selector.selectHighestFromFlushOrStraight(highestPlayerCards).getCourt().isLess(Selector.selectHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			if (Selector.selectHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector.selectHighestFromFlushOrStraight(currentPlayerCards).getCourt()
					&& highest != index) {
				indexesHighest.add(index);
			}
		}
		return indexesHighest;

	}

	private static List<Integer> findWinnerIfPlayerHaveFourOfAKind(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromFourOfAKind(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromFourOfAKind(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		// nie sprawdzam kart innych graczy gdyz czworka jest unikatowa
		indexesHighest.add(highest);
		return indexesHighest;
	}

	private static List<Integer> findWinnerIfPlayerHaveFullHouse(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromFullHouse(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromFullHouse(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// nie sprawdzam jeszcze raz gdyz 'trojka i para' jest unikatowa
		return indexesHighest;
	}

	private static List<Integer> findWinnerIfPlayerHaveFlush(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromFlushOrStraight(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			if (Selector.selectHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector.selectHighestFromFlushOrStraight(
					currentPlayerCards).getCourt()
					&& highest != index) {
				indexesHighest.add(index);
			}
		}
		if (indexesHighest.size() > 1) {
			// sprawdzam druga najwyzsza karte
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

				if (Selector.selectSecondHighestFromFlushOrStraight(highestPlayerCards).getCourt()
						.isLess(Selector.selectSecondHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
					highest = index;
				}
			}
			indexesHighest.clear();
			indexesHighest.add(highest);

			// sprawdzam jeszcze raz czy ktos ma taka sama druga karte
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
				if (Selector.selectSecondHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector
						.selectSecondHighestFromFlushOrStraight(currentPlayerCards).getCourt() && highest != index) {
					indexesHighest.add(index);
				}
			}
			// w przypadku nie rozstrzygniecia remisu
			if (indexesHighest.size() > 1) {
				// sprawdzam trzecia najwyzsza karte
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

					if (Selector.selectThirdHighestFromFlushOrStraight(highestPlayerCards).getCourt()
							.isLess(Selector.selectThirdHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
						highest = index;
					}
				}
				indexesHighest.clear();
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama trzecia z kolei
				// karte
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
					if (Selector.selectThirdHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector
							.selectThirdHighestFromFlushOrStraight(currentPlayerCards).getCourt() && highest != index) {
						indexesHighest.add(index);
					}
				}
				if (indexesHighest.size() > 1) {
					// sprawdzam czwarta najwyzsza karte
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

						if (Selector.selectFourthHighestFromFlushOrStraight(highestPlayerCards).getCourt()
								.isLess(Selector.selectFourthHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
							highest = index;
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);

					// sprawdzam jeszcze raz czy ktos ma taka sama czwarta z
					// kolei karte
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
						if (Selector.selectFourthHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector
								.selectFourthHighestFromFlushOrStraight(currentPlayerCards).getCourt() && highest != index) {
							indexesHighest.add(index);
						}
					}
					if (indexesHighest.size() > 1) {
						// sprawdzam piata z kolei karte
						for (Integer index : indexesWinners) {

							List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
							List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

							if (Selector.selectFifthHighestFromFlushOrStraight(highestPlayerCards).getCourt()
									.isLess(Selector.selectFifthHighestFromFlushOrStraight(currentPlayerCards).getCourt())) {
								highest = index;
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);

						// sprawdzam jeszcze raz czy ktos ma taka sama piata z
						// kolei karte
						for (Integer index : indexesWinners) {

							List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
							List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
							if (Selector.selectFifthHighestFromFlushOrStraight(highestPlayerCards).getCourt() == Selector
									.selectFifthHighestFromFlushOrStraight(currentPlayerCards).getCourt() && highest != index) {
								indexesHighest.add(index);
							}
						}
					}
				}
			}
		}

		return indexesHighest;

	}

	private static List<Integer> findWinnerIfPlayerHaveThreeOfAKind(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromThree(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromThree(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// nie sprawdzam jeszcze raz gdyz 'trojka i para' jest unikatowa
		return indexesHighest;
	}

	private static List<Integer> findWinnerIfPlayerHaveTwoPair(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromTwoPair(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromTwoPair(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			if (Selector.selectHighestFromTwoPair(highestPlayerCards).getCourt() == Selector.selectHighestFromTwoPair(
					currentPlayerCards).getCourt()
					&& highest != index) {
				indexesHighest.add(index);
			}
		}

		if (indexesHighest.size() > 1) {
			// sprawdzam najwyzsza karte w druiej parze
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

				if (Selector.selectSecondHighestFromTwoPair(highestPlayerCards).getCourt()
						.isLess(Selector.selectSecondHighestFromTwoPair(currentPlayerCards).getCourt())) {
					highest = index;
				}
			}
			indexesHighest.clear();
			indexesHighest.add(highest);

			// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte w
			// drugiej parze
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
				if (Selector.selectSecondHighestFromTwoPair(highestPlayerCards).getCourt() == Selector.selectSecondHighestFromTwoPair(
						currentPlayerCards).getCourt()
						&& highest != index) {
					indexesHighest.add(index);
				}
			}
			if (indexesHighest.size() > 1) {
				// sprawdzam najwyzszego 'kickera'
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

					if (Selector.selectKickerFromTwoPair(highestPlayerCards).getCourt()
							.isLess(Selector.selectKickerFromTwoPair(currentPlayerCards).getCourt())) {
						highest = index;
					}
				}
				indexesHighest.clear();
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma takiego samego 'kickera'
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
					if (Selector.selectKickerFromTwoPair(highestPlayerCards).getCourt() == Selector.selectKickerFromTwoPair(
							currentPlayerCards).getCourt()
							&& highest != index) {
						indexesHighest.add(index);
					}
				}

			}

		}

		return indexesHighest;
	}

	private static List<Integer> findWinnerIfPlayerHaveOnePair(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromOnePair(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromOnePair(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			if (Selector.selectHighestFromOnePair(highestPlayerCards).getCourt() == Selector.selectHighestFromOnePair(currentPlayerCards)
					.getCourt() && highest != index) {
				indexesHighest.add(index);
			}
		}

		if (indexesHighest.size() > 1) {
			// sprawdzam pierwsza najwyzsza karte po parze
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

				if (Selector.selectSecondHighestFromOnePair(highestPlayerCards).getCourt()
						.isLess(Selector.selectSecondHighestFromOnePair(currentPlayerCards).getCourt())) {
					highest = index;
				}
			}
			indexesHighest.clear();
			indexesHighest.add(highest);

			// sprawdzam jeszcze raz czy ktos jeszcze ma pierwsza najwyzsza
			// karte po parze
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
				if (Selector.selectSecondHighestFromOnePair(highestPlayerCards).getCourt() == Selector.selectSecondHighestFromOnePair(
						currentPlayerCards).getCourt()
						&& highest != index) {
					indexesHighest.add(index);
				}
			}
			if (indexesHighest.size() > 1) {
				// sprawdzam druga najwyzsza karte po parze
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

					if (Selector.selectThirdHighestFromOnePair(highestPlayerCards).getCourt()
							.isLess(Selector.selectThirdHighestFromOnePair(currentPlayerCards).getCourt())) {
						highest = index;
					}
				}
				indexesHighest.clear();
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos jeszcze ma taka sama druga
				// najwyzsza
				// karte po parze
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
					if (Selector.selectThirdHighestFromOnePair(highestPlayerCards).getCourt() == Selector.selectThirdHighestFromOnePair(
							currentPlayerCards).getCourt()
							&& highest != index) {
						indexesHighest.add(index);
					}
				}
				if (indexesHighest.size() > 1) {
					// sprawdzam trzecia najwyzsza karte po parze
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

						if (Selector.selectFourthHighestFromOnePair(highestPlayerCards).getCourt()
								.isLess(Selector.selectFourthHighestFromOnePair(currentPlayerCards).getCourt())) {
							highest = index;
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);

					// sprawdzam jeszcze raz czy ktos jeszcze ma taka sama
					// trzecia najwyzsza
					// karte po parze
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
						if (Selector.selectFourthHighestFromOnePair(highestPlayerCards).getCourt() == Selector
								.selectFourthHighestFromOnePair(currentPlayerCards).getCourt() && highest != index) {
							indexesHighest.add(index);
						}
					}
				}
			}
		}

		return indexesHighest;
	}

	private static List<Integer> findWinnerIfPlayerHaveHighCard(List<Integer> indexesWinners, List<Player> listPlayers) {

		int highest = indexesWinners.get(0);
		// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
		// najwyzszych kartach
		List<Integer> indexesHighest = new ArrayList<>();

		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

			if (Selector.selectHighestFromHighCard(highestPlayerCards).getCourt()
					.isLess(Selector.selectHighestFromHighCard(currentPlayerCards).getCourt())) {
				highest = index;
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (Integer index : indexesWinners) {

			List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
			List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
			if (Selector.selectHighestFromHighCard(highestPlayerCards).getCourt() == Selector.selectHighestFromHighCard(currentPlayerCards)
					.getCourt() && highest != index) {
				indexesHighest.add(index);
			}
		}

		if (indexesHighest.size() > 1) {
			// sprawdzam druga najwyzsza karte
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

				if (Selector.selectSecondHighestFromHighCard(highestPlayerCards).getCourt()
						.isLess(Selector.selectSecondHighestFromHighCard(currentPlayerCards).getCourt())) {
					highest = index;
				}
			}
			indexesHighest.clear();
			indexesHighest.add(highest);

			// sprawdzam jeszcze raz czy ktos ma taka sama druga najwyzsza karte
			for (Integer index : indexesWinners) {

				List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
				List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
				if (Selector.selectSecondHighestFromHighCard(highestPlayerCards).getCourt() == Selector.selectSecondHighestFromHighCard(
						currentPlayerCards).getCourt()
						&& highest != index) {
					indexesHighest.add(index);
				}
			}
			if (indexesHighest.size() > 1) {
				// sprawdzam trzecia najwyzsza karte
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

					if (Selector.selectThirdHighestFromHighCard(highestPlayerCards).getCourt()
							.isLess(Selector.selectThirdHighestFromHighCard(currentPlayerCards).getCourt())) {
						highest = index;
					}
				}
				indexesHighest.clear();
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama trzecia najwyzsza
				// karte
				for (Integer index : indexesWinners) {

					List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
					List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
					if (Selector.selectThirdHighestFromHighCard(highestPlayerCards).getCourt() == Selector.selectThirdHighestFromHighCard(
							currentPlayerCards).getCourt()
							&& highest != index) {
						indexesHighest.add(index);
					}
				}
				if (indexesHighest.size() > 1) {
					// sprawdzam czwarta najwyzsza karte
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

						if (Selector.selectFourthHighestFromHighCard(highestPlayerCards).getCourt()
								.isLess(Selector.selectFourthHighestFromHighCard(currentPlayerCards).getCourt())) {
							highest = index;
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);

					// sprawdzam jeszcze raz czy ktos ma taka sama czwarta
					// najwyzsza karte
					for (Integer index : indexesWinners) {

						List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
						List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
						if (Selector.selectFourthHighestFromHighCard(highestPlayerCards).getCourt() == Selector
								.selectFourthHighestFromHighCard(currentPlayerCards).getCourt() && highest != index) {
							indexesHighest.add(index);
						}
					}
					if (indexesHighest.size() > 1) {
						// sprawdzam piata najwyzsza karte
						for (Integer index : indexesWinners) {

							List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
							List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();

							if (Selector.selectFifthHighestFromHighCard(highestPlayerCards).getCourt()
									.isLess(Selector.selectFifthHighestFromHighCard(currentPlayerCards).getCourt())) {
								highest = index;
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);

						// sprawdzam jeszcze raz czy ktos ma taka sama piata
						// najwyzsza karte
						for (Integer index : indexesWinners) {

							List<Card> highestPlayerCards = listPlayers.get(highest).getOwnCards();
							List<Card> currentPlayerCards = listPlayers.get(index).getOwnCards();
							if (Selector.selectFifthHighestFromHighCard(highestPlayerCards).getCourt() == Selector
									.selectFifthHighestFromHighCard(currentPlayerCards).getCourt() && highest != index) {
								indexesHighest.add(index);
							}
						}
					}
				}
			}
		}
		return indexesHighest;
	}

}

