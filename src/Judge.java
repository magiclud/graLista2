import java.util.ArrayList;
import java.util.List;

public class Judge {

	public static List<Integer> selectWinners(List<Player> listOfPlayers) {

		int winnerPlayerIndex = 0;
		// Najpierw wybieram gracza z najelpszy układem
		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			if (listOfPlayers.get(winnerPlayerIndex).checkScore().ordinal() < listOfPlayers.get(i + 1).checkScore().ordinal()) {
				winnerPlayerIndex = i + 1;
			}
		}
		List<Integer> indexesWinners = new ArrayList<>();
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
			int highest = indexesWinners.get(0);

			// teorze liste na wypadek gdyby bylo wiecej graczy o jednakowych
			// najwyzszych kartach
			List<Integer> indexesHighest = new ArrayList<>();

			// okreslam wynik pomiedzy graczami gdy maja okreslony uklad -
			// algorytm powtarza sie dla Staiht flush, straight
			if (layout.equals(SequenceEnum.STRAIGHT_FLUSH) || layout.equals(SequenceEnum.STRAIGHT)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
							.ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt()
							.ordinal()
							&& highest != indexesWinners.get(i)) {
						//highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
			}
			if (layout.equals(SequenceEnum.FOUR_OF_A_KIND)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromFourOfAKind(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
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
					if (Checker.selectHighestFromFullHouse(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
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
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 1) {// sprawdzam druga najwyzesza
												// karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Checker.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
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
						if (Checker.selectSecondHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
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
							if (Checker.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
									.ordinal() < Checker
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
							if (Checker.selectThirdHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
									.ordinal() == Checker
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
								if (Checker.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
										.ordinal() < Checker
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
								if (Checker.selectFourthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
										.ordinal() == Checker
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
									if (Checker.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() < Checker
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
									if (Checker.selectFifthHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt()
											.ordinal() == Checker
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
					if (Checker.selectHighestFromThree(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
							.selectHighestFromThree(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);
			}
			if (layout.equals(SequenceEnum.TWO_PAIR)) {

				// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
							.selectHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
						highest = indexesWinners.get(i);
					}
				}
				indexesHighest.add(highest);

				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
							.selectHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
							&& highest != indexesWinners.get(i)) {
						// highest = i + 1;
						indexesHighest.add(indexesWinners.get(i));
					}
				}
				if (indexesHighest.size() > 0) {// sprawdzam najwyzsza karte w
												// druiej parze
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Checker.slectSecondHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
								.slectSecondHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
							highest = indexesWinners.get(i);
						}
					}
					indexesHighest.clear();
					indexesHighest.add(highest);
					// sprawdzam jeszcze raz czy ktos ma taka sama druga
					// najwyzsza karte
					for (int i = 0; i < indexesWinners.size(); i++) {
						if (Checker.slectSecondHighestFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
								.slectSecondHighestFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
								&& highest != indexesWinners.get(i)) {
							// highest = i + 1;
							indexesHighest.add(indexesWinners.get(i));
						}
					}
					if (indexesHighest.size() > 0) {// sprawdzam najwyzszego
													// 'kickera'
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Checker.selectKickerFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
									.selectKickerFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()) {
								highest = indexesWinners.get(i);
							}
						}
						indexesHighest.clear();
						indexesHighest.add(highest);
						// sprawdzam jeszcze raz czy ktos ma takiego samego
						// 'kickera'
						for (int i = 0; i < indexesWinners.size(); i++) {
							if (Checker.selectKickerFromTwoPair(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
									.selectKickerFromTwoPair(listOfPlayers.get(indexesWinners.get(i)).getOwnCards()).getCourt().ordinal()
									&& highest != indexesWinners.get(i)) {
								// highest = i + 1;
								indexesHighest.add(indexesWinners.get(i));
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
