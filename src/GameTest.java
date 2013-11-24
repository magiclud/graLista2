import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameTest {

	public static void main(String[] args) {

		System.out.println("System: Jeżeli chcesz zacząć grę, wpisz START i naciśnij ENTER,\n"
				+ "w przeciwnym razie naciśnij ENTER i wyjdziesz z gry");
		String inString;
		Scanner scanIn = new Scanner(System.in);
		inString = "START";
		if (!inString.equals("START")) {
			System.out.println("System: Wychodzę z gry");
			scanIn.close();
		} else {
			int numHum = 0, numBot = 0;
			System.out.println("System: Zaczynam grę");
			System.out.println("System: Wpisz ilu ma grać ludzi << 2<=numHum+numBot<=4 >>:");
			try {
				numHum = scanIn.nextInt();
			} catch (Exception e) {
				System.out.println("System:\nWprowadzono śmieciowe dane, kończę !");
				scanIn.close();
				System.exit(0);
			}
			System.out.println("System: Wpisz ile ma grać botów << 2<=numHum+numBot<=4 >>:");
			try {
				numBot = scanIn.nextInt();
			} catch (Exception e) {
				System.out.println("System:\nWprowadzono śmieciowe dane, kończę !");
				scanIn.close();
				System.exit(0);
			}

			inString = scanIn.nextLine();

			Table myTable = new Table(numHum, numBot);

			// pytam wszystkihc playerow czy graja, jesli tak, to pobieram
			// wpisowe
			askEverybodyToJoinTheGame(scanIn, myTable);
			// pierwssza licytacja
			firstBidding(scanIn, myTable);

			for (int i = 0; i < numHum; ++i) {
				System.out.println("System: Czy human ID: " + (i + 1) + " gra? <<T or N>>");
				while (inString.equals("N") || inString.endsWith("T")) {
					if (inString.equals("T")) {
						// myTable.setPlayersInGame(myTable.players.get(i));
						System.out.println("System: Partię rozgrywa human, ID: " + (i + 1));
						System.out.println("Human: Masz na ręce:");
						myTable.getPlayers().get(i).showCards();
						System.out.println("\nSystem: Wpisz jakie karty chcesz pobrać wpisując ich indeksy.\n"
								+ "Na przykład wpisz <<0,1,3>> aby pobrać karty [0], [1], [3]\n"
								+ "Aby nic nie pobierać, po prostu naciśnij ENTER.");

						inString = scanIn.nextLine();
						Pattern pattern = Pattern.compile("^(([0-9],?)+)$");
						Matcher matcher = pattern.matcher(inString);
						Boolean properIn = false;
						List<Integer> splittedInt = new ArrayList<Integer>();
						while (matcher.find()) {
							if (matcher.group().length() == inString.length()) {
								String[] splitted = inString.split(",");
								properIn = true;
								if (splitted.length > 4) {
									properIn = false;
									System.out.println("System: możesz wymienić od 1 do 4 kart !");
								}
								for (int j = 0; j < splitted.length; ++j) {
									splittedInt.add(Integer.parseInt(splitted[j]));
									if (splittedInt.get(j) > 4 || splittedInt.get(j) < 0) {
										properIn = false;
										System.out.println("System: Podałeś niepoprawne indeksy.");
									}
									for (int k = 0; k < splittedInt.size() - 1; ++k) {
										if (splittedInt.get(j).equals(splittedInt.get(k))) {
											System.out.println("System: Wystąpiły powtórzenia w twoim ciągu.");
											properIn = false;
										}
									}
								}

							}
						}
						if (properIn == true) {
							myTable.getPlayers().get(i).requestCards(splittedInt);
						} else if (inString.equals("")) {
							System.out.println("System: Gracz nie chce wymieniać kart.");
						} else
							System.out.println("System: Gracz wprowadził niepoprawne dane.\n" + "Gracz nie wymienia kart.");
						System.out.println("Gracz: Wchodzę do gry z kartami:");
						myTable.getPlayers().get(i).showCards();
						System.out.println("\n");
						System.out.println();
					} else {
						if (!inString.equals("T") && !inString.equals("N")) {
							System.out.println("Porsze odpowiedziec tylko T - jesli tak - human gra, N - jesli nie - human nie gra");
						}
					}

				}
			}
			for (int i = numHum; i < numHum + numBot; ++i) {
				// System.out.println("System: Czy bot ID: " + (i + 1) +
				// " gra? ");
				// TODO czy bot gra?
				System.out.println("System: Partię rozgrywa bot, ID: " + (i + 1));
				System.out.println("Bot: Mam na ręce:");
				myTable.getPlayers().get(i).showCards();
				System.out.println("\nBot: Prowadzę rozgrywkę: ");
				myTable.getPlayers().get(i).joinGame();
				System.out.println("Bot: Wchodzę do gry z kartami:");
				myTable.getPlayers().get(i).showCards();
				System.out.println("\n");
			}
			List<Integer> winners = Judge.selectWinners(myTable.getPlayers());
			for (int i = 0; i < winners.size(); ++i) {
				System.out.println("System: Player " + (winners.get(i) + 1) + "<< wygrywa");
			}
		}
		// TUTAJ PROSZĘ WPISZ METODĘ JAK MAJĄ BYĆ OBSŁUGIWANE BOTY KTÓRY, JAK I
		// CZY WYGRYWA
		//
		//

		System.out.println("Doszedłem do końca");
		scanIn.close();

	}

	private static void firstBidding(Scanner scanIn, Table myTable) {
		for (Player player : myTable.getPlayersInGame()) {
			// TODO
			System.out.println("System: Okresl ruch  <<CHECK / BET / RAISE / CALL / FOLD / ALL-IN>>");
			if (player.isHuman()) {
			askPlayersWhatMove(scanIn, player);
			}
			// askBotWhatMove(myTable);
		}
	}

	// private static void askBotWhatMove(Table myTable) {
	// for (Player player : myTable.getPlayersInGame()) {
	// if(player.getPlayerStatus().equals(StatusEnum.BET)||
	// player.getPlayerStatus().equals(StatusEnum.RAISE)){
	//
	// }
	// }
	//
	// }

	private static void askPlayersWhatMove(Scanner scanIn, Player player) {

		int howMuch = 20;
		String odpowiedz = "";
		while (!odpowiedz.equals("CHECK") && !odpowiedz.equals("BET") && !odpowiedz.equals("RAISE") && !odpowiedz.equals("CALL")
				&& !odpowiedz.equals("FOLD") && !odpowiedz.equals("ALL-IN")) {

			switch (StatusEnum.valueOf(odpowiedz)) {
			case CHECK:
				player.check();
				break;
			case BET:// TODO how much?
				player.bet(player, howMuch);
				System.out.println("System: Okresl wysokosc stawki");
				System.out.println("Player: BET ustawiam na " + getAnswerForMoney(scanIn));
				break;
			case RAISE:
				player.raise(player, howMuch);
				System.out.println("System: Okresl wysokosc stawki");
				System.out.println("Player: BET ustawiam na " + getAnswerForMoney(scanIn));
				// return myTable.call(player);
				break;
			case ALL_IN:
				player.allin(player);
				break;
			case CALL:
				player.call(player);
				break;
			case FOLD:
				player.fold();
				break;
			}
			odpowiedz = scanIn.nextLine();
		}
	}

	private static String getAnswerForMoney(Scanner scanIn) {
		return scanIn.nextLine();
	}

	private static void askEverybodyToJoinTheGame(Scanner scanIn, Table myTable) {
		for (Player player : myTable.getPlayers()) {
			if (player.isHuman()) {
				String answer = getAnswerFor(player, scanIn);
				if (answer.equals("T")) {
					player.joinGame();
				}
			} else {
				Bot bot = (Bot) player;
				if (bot.randomIfJoinTogame()) {
					bot.joinGame();
				}
			}
		}
	}

	private static String getAnswerFor(Player player, Scanner scanIn) {

		String odpowiedz = "";
		while (!odpowiedz.equals("T") && !odpowiedz.equals("N")) {
			System.out.println("System: Czy human ID: " + player.getPlayerID() + " gra? <<T or N>>");
			odpowiedz = scanIn.nextLine();
		}
		return odpowiedz;
	}

}
