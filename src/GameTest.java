import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameTest {
	private static int numHum = 2;
	private static int numBot = 2;
	private static int poczatkoweZetony = 2000;
	private static int wpisowe = 10;
	static String poprawnyNapis = "human|bot";

	public static void main(String[] args) {

		try {
			numHum = Integer.parseInt(args[0]);
			args[1].matches(poprawnyNapis);
			numBot = Integer.parseInt(args[2]);
			args[3].matches(poprawnyNapis);
			poczatkoweZetony = Integer.parseInt(args[4]);
			wpisowe = Integer.parseInt(args[5]);
			if (args[1].equals("bot")) {
				int temp = numHum;
				numHum = numBot;
				numBot = temp;
			}

		} catch (NumberFormatException ex) {
			System.out.println("nieprawidło wprowadzona liczba graczy/zetonow/wpisowergo");
		}

		System.out.println("System: Jeżeli chcesz zacząć grę, wpisz START i naciśnij ENTER,\n"
				+ "w przeciwnym razie naciśnij ENTER i wyjdziesz z gry");
		String inString;
		Scanner scanIn = new Scanner(System.in);
		inString = "START";
		if (!inString.equals("START")) {
			System.out.println("System: Wychodzę z gry");
			scanIn.close();
		} else {

			inString = scanIn.nextLine();

			Table myTable = new Table(numHum, numBot);
			myTable.setStartWpisowe(wpisowe);
			myTable.setInitialChipsForPlayers(poczatkoweZetony);
			// pytam wszystkihc playerow czy graja, jesli tak, to pobieram
			// wpisowe
			askEverybodyToJoinTheGame(scanIn, myTable);

			firstBiddingLoop(scanIn, myTable);

			askToChangeCards(scanIn, myTable);

			firstBiddingLoop(scanIn, myTable);

			showWhoWin(myTable);

			dotychczasowyRanking(myTable);
		}
		System.out.println("Doszedłem do końca");
		scanIn.close();
	}

	private static void showWhoWin(Table myTable) {
		List<Integer> winners = Judge.selectWinners(myTable.getPlayers());
		for (int i = 0; i < winners.size(); ++i) {
			System.out.println("System: Player " + (winners.get(i) + 1) + "<< wygrywa");
			if (myTable.getWinners().size() == 1) {
				Player oneWinner = myTable.getPlayersInGame().get(winners.get(i));
				oneWinner.increaseScore(oneWinner.getScore() + 1);
				System.out.println("Gracz otrzymuje " + myTable.getPool());
				myTable.setCoinsIfOnePlayerWin();
			} else {
				System.out.println("Jest remis. Pula gry przechodzi do nastepnej rundy");// TODO
			}
		}

	}

	private static void dotychczasowyRanking(Table myTable) {
		for (Player player : myTable.getPlayersInGame()) {
			System.out.println("Gracz " + player.getPlayerID() + " posiada " + player.getOwnChips() + " zetonow oraz " + player.getScore()
					+ " punktow");
		}
	}

	private static void firstBiddingLoop(Scanner scanIn, Table myTable) {

		// dopoki licytacja sie NIE skonczyla
		while (!myTable.isBiddingOver()) {
			// pierwssza licytacja
			firstBidding(scanIn, myTable);
		}
	}

	private static void firstBidding(Scanner scanIn, Table myTable) {
		for (Player player : myTable.getPlayersInGame()) {
			System.out.println("System: Okresl ruch  <<CHECK / BET / RAISE / CALL / FOLD / ALL-IN>>");
			if (player.isHuman()) {// jesli to human
				String odpowiedz = askPlayersWhatMove(scanIn, player);
				StatusEnum move = StatusEnum.valueOf(odpowiedz);
				playersMove(move, player, scanIn);
			} else {
				// jesli bot
				askBotWhatMove(player, scanIn);
			}
		}
	}

	private static void askBotWhatMove(Player player, Scanner scanIn) {
		Bot bot = (Bot) player;
		StatusEnum move = bot.makeMove();
		System.out.println("Player: Bot wykonuje " + move);
		playersMove(move, player, scanIn);
	}

	private static void playersMove(StatusEnum move, Player player, Scanner scanIn) {

		int howMuch = 0;
		switch (move) {
		case CHECK:
			player.check();
			break;
			case BET:
			if (!player.isHuman()) {
				System.out.println("System: Okresl wysokosc stawki ");
				// System.out.println("Player(BOT): BET ustawiam na " +
				// player.getChipsForBidding());
				// player.bet(player.getChipsForBidding());// minimalna stawka?
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
				// TODO te czesc nizej zawsze si wykonuje!!!!! nie powinien tu byc else? uruchamiasz czasem ta gre, bo
				// jak nie piszecie testow, to musisz non stop testowac co napiszesz, bo zaufaj mi zawsze cos jest zle
				// do poprawy wszystkie casy z tego co widze
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

	private static String askPlayersWhatMove(Scanner scanIn, Player player) {

		String odpowiedz = "";
		while (!odpowiedz.equals("CHECK") && !odpowiedz.equals("BET") && !odpowiedz.equals("RAISE") && !odpowiedz.equals("CALL")
				&& !odpowiedz.equals("FOLD") && !odpowiedz.equals("ALL-IN")) {

			odpowiedz = scanIn.nextLine();
		}
		return odpowiedz;
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

	private static void askToChangeCards(Scanner scanIn, Table myTable) {
		for (Player player : myTable.getPlayersInGame()) {
			if (player.isHuman()) {

				System.out.println("\nSystem: Wpisz jakie karty chcesz pobrać wpisując ich indeksy.\n"
						+ "Na przykład wpisz <<0,1,3>> aby pobrać karty [0], [1], [3]\n" + "Obecnie masz: " + player + "\n"
						+ "Aby nic nie pobierać, po prostu naciśnij ENTER.");
				player.showCards();
				String odpowiedz = scanIn.nextLine();
				if (!odpowiedz.isEmpty()) {
					String[] indexesString = odpowiedz.split(",");
					List<Integer> cardIndexesToChange = changeToIntegerList(indexesString);
					player.changeCards(cardIndexesToChange);
					System.out.println("Po zmianie: " + player);
				}
			} else {
				Bot bot = (Bot) player;
				bot.changeCardsUsingStrategy();
			}
		}
	}

	private static List<Integer> changeToIntegerList(String[] indexesString) {
		List<Integer> indexes = new ArrayList<Integer>();

		for (String indexString : indexesString) {
			indexes.add(Integer.parseInt(indexString));
		}
		return indexes;
	}

}
