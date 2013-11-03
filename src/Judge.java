import java.util.ArrayList;
import java.util.List;

public class Judge {

	private static List<Integer> indexesWinners;
	private static List<Integer> indexesHighest;
	private static Integer highest;
	private static List<Player> listPlayers;

	public static List<Integer> selectWinners(List<Player> listOfPlayers) {

		listPlayers = new ArrayList<>();
		listPlayers = listOfPlayers;

		int winnerPlayerIndex = 0;
		// Najpierw wybieram gracza z najelpszy układem
		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			if (listOfPlayers.get(winnerPlayerIndex).checkScore().ordinal() < listOfPlayers.get(i + 1).checkScore().ordinal()) {
				winnerPlayerIndex = i + 1;
			}
		}
		indexesWinners = new ArrayList<>();
		indexesWinners.add(winnerPlayerIndex);
		// sprawdzam jeszcze raz czy ktos ma ten sam uklad
		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(winnerPlayerIndex).checkScore().ordinal() == listOfPlayers.get(i).checkScore().ordinal()
					&& winnerPlayerIndex != i) {
				winnerPlayerIndex = i;
				indexesWinners.add(winnerPlayerIndex);
			}
		}

		// tutaj rozstrzygam wynik gdy najlepszy uklad kart powtarza sie wsrod
		// graczy
		if (indexesWinners.size() > 1) {
			// wyszukuje jaki ukladbjest najlepszy
			SequenceEnum layout = listOfPlayers.get(indexesWinners.get(0)).checkScore();

			// domyslnie ustalam ze pierwszy z graczy z wysokim ukladem ma
			// rowniez najlepsze karty
			highest = indexesWinners.get(0);

			// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
			// najwyzszych kartach
			indexesHighest = new ArrayList<>();

			// okreslam wynik pomiedzy graczami gdy maja okreslony uklad -
			// algorytm powtarza sie dla Staiht flush, straight
			if (layout.equals(SequenceEnum.STRAIGHT_FLUSH) || layout.equals(SequenceEnum.STRAIGHT)) {

				findWinnerIfPlsyerHaveStraightFlushOrStraight();
			}

			if (layout.equals(SequenceEnum.FOUR_OF_A_KIND)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromFourOfAKind(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromFourOfAKind(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);
				// nie sprawdzam jeszcze raz gdyz czworka jest unikatowa
			}
			if (layout.equals(SequenceEnum.FULL_HOUSE)) {// TODO

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromFullHouse(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromFullHouse(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);
				// nie sprawdzam jeszcze raz gdyz 'trojka i para' jest unikatowa
			}
			if (layout.equals(SequenceEnum.FLUSH)) {// TODO

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 1) {// sprawdzam druga najwyzesza
												// karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
								.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()) {
							highest = indexesWinners.get(i);
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);
					// sprawdzam jeszcze raz czy ktos ma taka sama druga
					// najwyzsza karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
								.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()
								&& highest != indexesWinners.get(i)) {
							// highest = i + 1;
							indexesHighest.add(indexesWinners.get(i));
						}
					}
					if (indexesHighest.size() > 1) {// sprawdzam trzecia
													// najwyzesza karte
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
									.ordinal() < Selector
									.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
									.getCourt().ordinal()) {
								highest = indexesWinners.get(i);
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);
						// sprawdzam jeszcze raz czy ktos ma taka sama trzecia
						// najwyzsza karte
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
									.ordinal() == Selector
									.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
									.getCourt().ordinal()
									&& highest != indexesWinners.get(i)) {
								// highest = i + 1;
								indexesHighest.add(indexesWinners.get(i));
							}
						}
						if (indexesHighest.size() > 1) {// sprawdzam czwarta
														// najwyzesza karte
							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
										.ordinal() < Selector
										.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
										.getCourt().ordinal()) {
									highest = indexesWinners.get(i);
								}
							}
							indexesHighest.clear();
							indexesHighest.add(highest);
							// sprawdzam jeszcze raz czy ktos ma taka sama
							// czwarta najwyzsza karte
							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
										.ordinal() == Selector
										.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
										.getCourt().ordinal()
										&& highest != indexesWinners.get(i)) {
									// highest = i + 1;
									indexesHighest.add(indexesWinners.get(i));
								}
							}
							if (indexesHighest.size() > 1) {// sprawdzam piata
															// najwyzesza karte
								for (int i = 0; i < indexesWinners.size(); i++) {
									if (Selector.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() < Selector
											.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
											.getCourt().ordinal()) {
										highest = indexesWinners.get(i);
									}
								}
								indexesHighest.clear();
								indexesHighest.add(highest);
								// sprawdzam jeszcze raz czy ktos ma taka sama
								// piata najwyzsza karte
								for (int i = 0; i < indexesWinners.size(); i++) {
									if (Selector.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() == Selector
											.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
											.getCourt().ordinal()
											&& highest != indexesWinners.get(i)) {
										// highest = i + 1;
										indexesHighest.add(indexesWinners.get(i));
									}
								}
							}
						}
					}
				}
			}
			if (layout.equals(SequenceEnum.TREE_OF_A_KIND)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromThree(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromThree(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);
			}
			if (layout.equals(SequenceEnum.TWO_PAIR)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
							.selectHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 0) {// sprawdzam najwyzsza karte w
												// druiej parze
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.slectSecondHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
								.slectSecondHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
							highest = indexesWinners.get(i);
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);
					// sprawdzam jeszcze raz czy ktos ma taka sama druga
					// najwyzsza karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.slectSecondHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
								.slectSecondHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
								&& highest != indexesWinners.get(i)) {
							// highest = i + 1;
							indexesHighest.add(indexesWinners.get(i));
						}
					}
					if (indexesHighest.size() > 1) {// sprawdzam najwyzszego
													// 'kickera'
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectKickerFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
									.selectKickerFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
								highest = indexesWinners.get(i);
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);
						// sprawdzam jeszcze raz czy ktos ma takiego samego
						// 'kickera'
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectKickerFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
									.selectKickerFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
									&& highest != indexesWinners.get(i)) {
								// highest = i + 1;
								indexesHighest.add(indexesWinners.get(i));
							}
						}
					}
				}
			}
			if (layout.equals(SequenceEnum.ONE_PAIR)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
							.selectHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 1) {// sprawdzam pierwsza najwyzsza
												// karte po parze

					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
								.selectSecondHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()) {
							highest = indexesWinners.get(i);
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);
					// sprawdzam jeszcze raz czy ktos jeszcze ma pierwsza
					// najwyzsza karte po parze
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
								.selectSecondHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()
								&& highest != indexesWinners.get(i)) {
							// highest = i + 1;
							indexesHighest.add(indexesWinners.get(i));
						}
					}
					if (indexesHighest.size() > 1) {// sprawdzam druga najwyzsza
													// karte po parze

						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
									.selectThirdHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
									.ordinal()) {
								highest = indexesWinners.get(i);
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);
						// sprawdzam jeszcze raz czy ktos jeszcze ma druga
						// najwyzsza karte po parze
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
									.selectThirdHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
									.ordinal()
									&& highest != indexesWinners.get(i)) {
								// highest = i + 1;
								indexesHighest.add(indexesWinners.get(i));
							}
						}
						if (indexesHighest.size() > 1) {// sprawdzam trzecia
														// najwyzsza karte po
														// parze

							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
										.selectFourthHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
										.ordinal()) {
									highest = indexesWinners.get(i);
								}
							}
							indexesHighest.clear();
							indexesHighest.add(highest);
							// sprawdzam jeszcze raz czy ktos jeszcze ma trzecia
							// najwyzsza karte po parze
							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromOnePair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
										.selectFourthHighestFromOnePair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
										.ordinal()
										&& highest != indexesWinners.get(i)) {
									// highest = i + 1;
									indexesHighest.add(indexesWinners.get(i));
								}
							}
						}
					}
				
				}
			}
			if (layout.equals(SequenceEnum.HIGH_CARD)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
							.selectHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Selector.selectHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
							.selectHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 1) {

					// sprawdzam druga najwyzsza karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
								.selectSecondHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()) {
							highest = indexesWinners.get(i);
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);

					// sprawdzam jeszcze raz czy ktos jeszcze ma taka sama druga
					// najwyzsza karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Selector.selectSecondHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
								.selectSecondHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
								.ordinal()
								&& highest != indexesWinners.get(i)) {
							// highest = i + 1;
							indexesHighest.add(indexesWinners.get(i));
						}
					}
					if (indexesHighest.size() > 1) {

						// sprawdzam trzecia najwyzsza karte
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
									.selectThirdHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
									.ordinal()) {
								highest = indexesWinners.get(i);
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);

						// sprawdzam jeszcze raz czy ktos jeszcze ma taka sama
						// trzecia najwyzsza karte
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Selector.selectThirdHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
									.selectThirdHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
									.ordinal()
									&& highest != indexesWinners.get(i)) {
								// highest = i + 1;
								indexesHighest.add(indexesWinners.get(i));
							}
						}
						if (indexesHighest.size() > 1) {

							// sprawdzam czwarta najwyzsza karte
							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
										.selectFourthHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
										.ordinal()) {
									highest = indexesWinners.get(i);
								}
							}
							indexesHighest.clear();
							indexesHighest.add(highest);

							// sprawdzam jeszcze raz czy ktos jeszcze ma taka
							// sama czwarta najwyzsza karte
							for (int i = 0; i < indexesWinners.size(); i++) {
								if (Selector.selectFourthHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
										.selectFourthHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
										.ordinal()
										&& highest != indexesWinners.get(i)) {
									// highest = i + 1;
									indexesHighest.add(indexesWinners.get(i));
								}
							}
							if (indexesHighest.size() > 1) {

								// sprawdzam piata karte
								for (int i = 0; i < indexesWinners.size(); i++) {
									if (Selector.selectFifthHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() < Selector
											.selectFifthHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
											.getCourt().ordinal()) {
										highest = indexesWinners.get(i);
									}
								}
								indexesHighest.clear();
								indexesHighest.add(highest);

								// sprawdzam jeszcze raz czy ktos jeszcze ma
								// taka sama piata karte
								for (int i = 0; i < indexesWinners.size(); i++) {
									if (Selector.selectFifthHighestFromHighCard(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() == Selector
											.selectFifthHighestFromHighCard(listOfPlayers.get(indexesWinners.get(i)).getOwnCards())
											.getCourt().ordinal()
											&& highest != indexesWinners.get(i)) {
										// highest = i + 1;
										indexesHighest.add(indexesWinners.get(i));
									}
								}
							}
						}
					}
				}
			}
			// sortHighest.sort(highestCards);
			// }
			// if (!highestCards.get(0).equals(highestCards.get(1))) {
			// // wygral pierwszy index
			//
			indexesWinners.clear();
			indexesWinners = indexesHighest;
		}
			
		return indexesWinners;
			
	}

	private static void findWinnerIfPlsyerHaveStraightFlushOrStraight() {
		// // gdy nie ma remisu to najwyzsza karta rozstrzyga gre
		for (int i = 0; i < indexesWinners.size(); i++) {
			if (Selector.selectHighestFromFlushOrStraight(listPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Selector
					.selectHighestFromFlushOrStraight(listPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
				highest = indexesWinners.get(i);
			}
		}
		indexesHighest.add(highest);

		// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
		for (int i = 0; i < indexesWinners.size(); i++) {
			if (Selector.selectHighestFromFlushOrStraight(listPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Selector
					.selectHighestFromFlushOrStraight(listPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
					&& highest != indexesWinners.get(i)) {
				// highest = i + 1;
				indexesHighest.add(indexesWinners.get(i));
			}
		}

	}
		
	// static List<Player> sortListOfPlayers(List<Player> players) {
	// // na zasadzie sortowania przez wybor
	// /*
	// * public static int[] sotowaniePrzezWybor(int tablica[]) { for (int i =
	// * 0; i < tablica.length - 1; i++) { int najmniejsza = i; for (int j = i
	// * + 1; j < tablica.length; j++) { if (tablica[j] <
	// * tablica[najmniejsza]) { najmniejsza = j; } } int pomocnicza =
	// * tablica[najmniejsza]; tablica[najmniejsza] = tablica[i]; tablica[i] =
	// * pomocnicza; } return tablica; }
	// */
	// for (Player player : players) {
	// int najmniejsza = player.hashCode();
	// for (int j = player.hashCode() + 1; j < players.size(); j++) {// Szukanie
	// // indeksu
	// // min. elementu
	// if (players.get(j).checkScore().ordinal() <
	// players.get(najmniejsza).checkScore().ordinal()) {// porownanie
	// // dwoch
	// // elementow listy
	// najmniejsza = j;
	// }
	// int pomocnicza = players.get(najmniejsza).hashCode();
	// players.remove(players.get(najmniejsza).hashCode());
	// players.add(pomocnicza, players.get(player.hashCode()));
	// players.remove(players.get(player.hashCode()));
	// players.add(player.hashCode(), players.get(najmniejsza));
	//
	// }
	// }
	// return players;
	// }
}

