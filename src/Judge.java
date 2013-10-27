import java.util.ArrayList;
import java.util.List;

public class Judge {

	static List<Player> players;
	static List<Integer> indexesWinners = new ArrayList<>();

	public static List<Integer> selectWinners(List<Player> listOfPlayers) {// czy
																			// moga
																		// byc 4
																		// remisy?

		players = sortListOfPlayers(listOfPlayers);
		if (players.get(players.size() - 1).checkScore().ordinal() > players.get(players.size() - 2).checkScore().ordinal()) {
			indexesWinners.add(players.get(players.size() - 1).hashCode());
		}
		if (players.get(players.size() - 1).checkScore().ordinal() == players.get(players.size() - 2).checkScore().ordinal()) {
			indexesWinners.add(players.get(players.size() - 1).hashCode());
			indexesWinners.add(players.get(players.size() - 2).hashCode());
		}
		return indexesWinners;

	}

	static List<Player> sortListOfPlayers(List<Player> players) {
		// na zasadzie sortowania przez wybor
		/*
		 * public static int[] sotowaniePrzezWybor(int tablica[]) { for (int i =
		 * 0; i < tablica.length - 1; i++) { int najmniejsza = i; for (int j = i
		 * + 1; j < tablica.length; j++) { if (tablica[j] <
		 * tablica[najmniejsza]) { najmniejsza = j; } } int pomocnicza =
		 * tablica[najmniejsza]; tablica[najmniejsza] = tablica[i]; tablica[i] =
		 * pomocnicza; } return tablica; }
		 */
		for (Player player : players) {
			int najmniejsza = player.hashCode();
			for (int j = player.hashCode() + 1; j < players.size(); j++) {// Szukanie
																			// indeksu
				// min. elementu
				if (players.get(j).checkScore().ordinal() < players.get(najmniejsza).checkScore().ordinal()) {// porownanie
																												// dwoch
					// elementow listy
					najmniejsza = j;
				}
				int pomocnicza = players.get(najmniejsza).hashCode();
				players.remove(players.get(najmniejsza).hashCode());
				players.add(pomocnicza, players.get(player.hashCode()));
				players.remove(players.get(player.hashCode()));
				players.add(player.hashCode(), players.get(najmniejsza));

			}
		}
		return players;
	}
}
