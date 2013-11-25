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

			boolean koniecRundy = false;

			// pytam wszystkihc playerow czy graja, jesli tak, to pobieram
			// wpisowe
			askEverybodyToJoinTheGame(scanIn, myTable);

			while (!koniecRundy) {
				// pierwssza licytacja
				firstBidding(scanIn, myTable);

				if (myTable.getPlayersInGame().size() == 0) {
					System.out.println("Wszyscy gracze spasowali. \n Koniec rundy.");
					koniecRundy = true;
				}
				if (myTable.getPlayersInGame().size() == 1) {
					System.out.println("Jeden gracz zgarnia cala pule ");
					koniecRundy = true;
				}
				for (Player player : myTable.getPlayersInGame()) {
					if (!myTable.getStatusPlayersInGame().equals(StatusEnum.ALL_IN)
							|| !myTable.getStatusPlayersInGame().equals(StatusEnum.RAISE)) {
						System.out.println("Gracze wyrownali swoje stawki");
						koniecRundy = true;
					}
				}
			}
			
			

			for (int i = 0; i < numHum; ++i) {
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
		// TUTAJ PROSZĘ WPISZ METODĘ JAK MAJĄ BYĆ OBSŁUGIWANE BOTY KTÓRY, JAK I
		// CZY WYGRYWA
		//
		//

		}
		System.out.println("Doszedłem do końca");
		scanIn.close();
	}



	private static void firstBidding(Scanner scanIn, Table myTable) {
		for (Player player : myTable.getPlayersInGame()) {
			// TODO
			System.out.println("System: Okresl ruch  <<CHECK / BET / RAISE / CALL / FOLD / ALL-IN>>");
			if (player.isHuman()) {// jesli to human
				askPlayersWhatMove(scanIn, player);
			}
			// jesli bot
			askBotWhatMove(player, scanIn);

		}
	}

	private static void askBotWhatMove(Player player, Scanner scanIn) {
		Bot bot = (Bot) player;
		System.out.println("Player: Bot wykonuje " + bot.makeMove());
		playersMove(bot.makeMove(), player, scanIn);
	}

	private static void playersMove(StatusEnum move, Player player, Scanner scanIn) {

		int howMuch = 0;
		switch (move) {
		case CHECK:
			player.check();
			break;
		case BET:// TODO how much?
			if (!player.isHuman()) {
				System.out.println("System: Okresl wysokosc stawki ");
				System.out.println("Player(BOT): BET ustawiam na " + player.getChipsForBidding());
				player.bet(player.getChipsForBidding());// minimalna stawka?
			}
			System.out.println("System: Okresl wysokosc stawki ");
			System.out.println("Player: BET ustawiam na " + getAnswerForMoney(scanIn));
			howMuch = Integer.parseInt(getAnswerForMoney(scanIn));
			player.bet(howMuch);
			break;
		case RAISE:
			if (!player.isHuman()) {
				Bot bot = (Bot) player;
				System.out.println("System: Okresl wysokosc stawki");
				System.out.println("Player(BOT): RAISE ustawiam na " + bot.chipsToRaise());
				player.raise(bot.chipsToRaise());
			}
			System.out.println("System: Okresl wysokosc stawki");
			System.out.println("Player: RAISE ustawiam na " + getAnswerForMoney(scanIn));
			howMuch = Integer.parseInt(getAnswerForMoney(scanIn));
			player.raise(howMuch);
			break;
		case ALL_IN:
			player.allin();
			break;
		case CALL:
			player.call();
			break;
		case FOLD:
			player.fold();
			break;
		default:
			break;
		}
	}

	private static void askPlayersWhatMove(Scanner scanIn, Player player) {

		String odpowiedz = "";
		while (!odpowiedz.equals("CHECK") && !odpowiedz.equals("BET") && !odpowiedz.equals("RAISE") && !odpowiedz.equals("CALL")
				&& !odpowiedz.equals("FOLD") && !odpowiedz.equals("ALL-IN")) {

			StatusEnum move = StatusEnum.valueOf(odpowiedz);
			playersMove(move, player, scanIn);
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
