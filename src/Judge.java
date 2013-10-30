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
		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			if (listOfPlayers.get(winnerPlayerIndex).checkScore().ordinal() == listOfPlayers.get(i + 1).checkScore().ordinal()
					&& winnerPlayerIndex != i + 1) {
				winnerPlayerIndex = i + 1;
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

			// okreslam wynik pomiedzy graczami gdy maja okreslony uklad -
			// algorytm powtarza sie dla Staiht flush, flush, straight
			if (layout.equals(SequenceEnum.STRAIGHT_FLUSH) || layout.equals(SequenceEnum.FLUSH) || layout.equals(SequenceEnum.STRAIGHT)) {
				// sprawdzam jeszcze raz czy ktos ma taka sama najwyzsza karte
				for (int i = 0; i < indexesWinners.size(); i++) {
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() == Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i + 1)).getOwnCards()).getCourt()
							.ordinal()) {
						int highest2 = indexesWinners.get(i + 1);
						indexesWinners.clear();
						indexesWinners.add(highest);
						indexesWinners.add(highest2);
						break;
					}
					// gdy nie ma remisu to najwyzsza karta rozstrzyga gre
					if (Checker.selectHighestFromFlushOrStraight(listOfPlayers.get(highest).getOwnCards()).getCourt().ordinal() < Checker
							.selectHighestFromFlushOrStraight(listOfPlayers.get(indexesWinners.get(i + 1)).getOwnCards()).getCourt()
							.ordinal()) {
						highest = highest + 1;
					}
					indexesWinners.clear();
					indexesWinners.add(highest);
				}

				// sortHighest.sort(highestCards);
			}
			// if (layout.equals(SequenceEnum.FOUR_OF_A_KIND)) {
			// for (int i = 0; i < indexesWinners.size() - 1; i++) {
			// highestCards.add(listOfPlayers.get(indexesWinners.get(i)).selectHighestFromFourOfAKind());
			// }
			// sortHighest.sort(highestCards);
			// }
			// // if(layout.equals(SequenceEnum.FULL_HOUSE)){//TODO
			// // for (int i = 0; i < indexesWinners.size() - 1; i++) {
			// // highestCards.add(listOfPlayers.get(indexesWinners.get(i)).);
			// // }
			// // sortHighest.sort(highestCards);
			// // }
			// if (layout.equals(SequenceEnum.TREE_OF_A_KIND)) {
			// for (int i = 0; i < indexesWinners.size() - 1; i++) {
			// highestCards.add(listOfPlayers.get(indexesWinners.get(i)).selectHighestFromThree());
			// }
			// sortHighest.sort(highestCards);
			// }
			// if (layout.equals(SequenceEnum.TWO_PAIR)) {
			// for (int i = 0; i < indexesWinners.size() - 1; i++) {
			// highestCards.add(listOfPlayers.get(indexesWinners.get(i)).selectHighestFromTwoPair());
			// }
			// sortHighest.sort(highestCards);
			// }
			// if (layout.equals(SequenceEnum.ONE_PAIR)) {
			// for (int i = 0; i < indexesWinners.size() - 1; i++) {
			// highestCards.add(listOfPlayers.get(indexesWinners.get(i)).selectHighestFromOnePair());
			// }
			// sortHighest.sort(highestCards);
			// }
			// if (!highestCards.get(0).equals(highestCards.get(1))) {
			// // wygral pierwszy index
			//
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
