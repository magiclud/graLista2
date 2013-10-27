import java.util.ArrayList;
import java.util.List;

public class Judge {

	public static List<Integer> selectWinners(List<Player> listOfPlayers) {

		int winnerPlayerIndex = 0;

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
