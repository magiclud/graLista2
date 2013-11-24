import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Table {

	private static final int WPISOWE = 50;
	private List<Player> players = new ArrayList<>();
	private List<List<Card>> playersCards = new ArrayList<>();
	private Random randomPlayer= new Random();
	private List<List<Card>> endOfGame;
	private List<Boolean> alreadyChangedCards = new ArrayList<>();
	private List<Player> playersInGame = new ArrayList<>();

	private int pool = 0; // pula gry

	private int numHumans, numBots;

	// SZKIC
	private int startZetony, startWpisowe;

	private int currentMax; // ile maksymalnie ostatnio obstawiono

	private StatusEnum roundStatus = StatusEnum.CLEAN;// początkowy status
	// rundy

	private Deck actualDeck;

	// Zwraca wskaźnik na playera jeżeli znaleziono takiego w naszej puli graczy
	public Player findPlayer(String szukanyPlayerID) {
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).newPlayerID.equals(szukanyPlayerID))
				return players.get(i);
		}
		return null;
	}

	public Table(int numHumans, int numBots) {

		this.actualDeck = new Deck(); // Tutaj już mają być karty rozdane,
										// znaczy się :-)
		// Dodawanie graczy przy starcie, trzeba dodać wyjątki
		if (numHumans + numBots < 2 || numHumans + numBots > 4)
			throw new IllegalStateException("Niepoprawna ilość graczy.");
		for (int i = 0; i < numHumans + numBots; ++i) {

			playersCards.add(actualDeck.giveCards(5));
			alreadyChangedCards.add(false);
		}
		for (int i = 0; i < numHumans; ++i) {
			players.add(new Human(playersCards.get(i), this, i));
		}
		for (int i = numHumans; i < numHumans + numBots; ++i) {
			players.add(new Bot(playersCards.get(i), this, i));
		}
		this.numHumans = numHumans;
		this.numBots = numBots;

		// trzeba ustawaic cos co bedzie mowilo ile graczy faktycznie chce
		// zagrac i zaplaciic
		//
	}

	// Metoda, która rozpoczyna grę

	// public void startGame() { for (int i = 0; i < players.size(); ++i) {
	// endOfGame.add(players.get(i).joinGame()); } }

	// Metoda, która odbiera dla gracza od Deck żądane karty do wymiany

	public void startGame() {

		int currentMax = startWpisowe;

		// 1. wydaj wszystkim graczom żetony player.ownChips = startZetony;

		rozpocznijRunde();

	}

	public void rozpocznijRunde() {
		// 1. ktory gracz pierwszy ? byc moze zrob jakas liste playerow
			List<Player> tempListOfPlayers= new ArrayList();
			Player random = playersInGame.get(playersInGame.size());
			tempListOfPlayers.add(random); 
			playersInGame.remove(random);
			for (int i=0; i<playersInGame.size(); i++){
				tempListOfPlayers.add(playersInGame.get(i));
			}
			playersInGame.clear();
			playersInGame = tempListOfPlayers;
		
			
			
		roundStatus = StatusEnum.CLEAN;
		// 2. ustaw status player.playerStatus = StatusEnum.CLEAN;
		actualDeck = new Deck();
		// 3. rozdaj graczom karty
		// 4. Kolejno graczom nadawaj rozkaz licytacji
		// Player kolejny = find(NextPlayerID);
		// try {
		// int currentMax = kolejny.zacznijLicytacje(currentMax,
		// List<StatusEnum> playersStatuses,
		// mozliweRuchy) } catch (Exception ex) {}
		// uproszczenie statusRundy = player.playerStatus;

		// Wywolujemy u kazdego player player.gameStrategy() zeby wymienil karty

		// ZNOWU LICYTACJA

		// Wywolujemy u kazdego player player.gameStrategy() zeby wymienil karty

		// ZABIERAMY gRACZON CZIPS I ODDAJEMY WYGRANEMU
	}

	// trzeba ZROBIC FUNKCJĘ
	public List<ActionsEnum> mozliweRuchy(StatusEnum statusRundy) {
		List<ActionsEnum> solution = null;
		return solution;
	}

	public void check() {

	}

	public void bet(String PlayerID, int howMuch) throws Exception {
		Player player = findPlayer(PlayerID);
		if (howMuch < currentMax)
			throw new IllegalStateException("Za mało obstawiasz !");
		if (howMuch > player.chipsForBidding)
			throw new IllegalStateException("Za mało masz coins żeby tak zrobić !");
		roundStatus = StatusEnum.BET;
		currentMax = howMuch;
	}

	// \/\/\/\/\/\/ bardzo WAŻNE ! ! ! ! ! przykłąd
	/*
	 * void bet(String PlayerID, int howMuch) throws Exception { Player player =
	 * findPlayer(PlayerID); if(howMuch < currentMax) throw new
	 * IllegalStateException("za mało obstawiasz !"); changeStatus
	 * player.newToBet = howmuch; currentMax = newToBet
	 * 
	 * }
	 */
	/*********************************************
	 * do uduniecia ********************************* public void raise() {
	 * 
	 * }
	 * 
	 * public void raise(String PlayerID, int howMuch) throws Exception { Player
	 * player = findPlayer(PlayerID); if (howMuch <= currentMax) throw new
	 * IllegalStateException("Za mało obstawiasz !"); if (howMuch >
	 * player.chipsForBidding) throw new
	 * IllegalStateException("Za mało masz coins żeby tak zrobić !"); currentMax
	 * = howMuch; player.setPlayerStatus(StatusEnum.RAISE); roundStatus =
	 * StatusEnum.RAISE; }
	 * 
	 * public void call() {
	 * 
	 * }
	 * 
	 * public void call(String PlayerID) { Player player = findPlayer(PlayerID);
	 * if (player.chipsForBidding < currentMax) throw new
	 * IllegalStateException("Za mało masz coins !"); roundStatus =
	 * StatusEnum.CALL; player.setPlayerStatus(StatusEnum.CALL); }
	 * 
	 * public void fold() {
	 * 
	 * }
	 * 
	 * public void fold(String PlayerID) { Player player = findPlayer(PlayerID);
	 * player.setPlayerStatus(StatusEnum.FOLD); }
	 * 
	 * public void allin(String PlayerID) { Player player =
	 * findPlayer(PlayerID); roundStatus = StatusEnum.ALL_IN; }
	 ***************************************************************************************/
	public List<Card> giveCards(int numbOfCards) {
		return actualDeck.giveCards(numbOfCards);
	}

	public List<Card> newSafeChangeCards(List<Integer> abandonedIndexes, String PlayerID) {
		int numberCardsToReturn = abandonedIndexes.size();
		Player player = findPlayer(PlayerID);
		if (player.newAlreadyChangedCards)
			throw new IllegalStateException("Już wymieniałeś karty !");
		else
			player.newAlreadyChangedCards = true;
		if (numberCardsToReturn > 4) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		List<Card> tempPlayerCards = player.ownCards;
		Sorter s = new Sorter();
		s.sort(tempPlayerCards);

		/** usuwam karty po indeksie **/
		// SORTOWANIE AHAHAHAH :D
		int[] sorted = new int[abandonedIndexes.size()];
		for (int i = 0; i < abandonedIndexes.size(); ++i) {
			sorted[i] = abandonedIndexes.get(i);
		}
		Arrays.sort(sorted);
		for (int i = 0; i < abandonedIndexes.size(); ++i) {
			abandonedIndexes.set(i, sorted[abandonedIndexes.size() - 1 - i]);
		}
		// KONIEC SORTOWANIA
		for (int i = 0; i < numberCardsToReturn; ++i) {
			System.out.println("System: Gracz wymienia : [" + abandonedIndexes.get(i) + "]");
			tempPlayerCards.remove((int) abandonedIndexes.get(i));
		}
		/*
		 * for(int i = 0; i < tempPlayerCards.size(); ++i) {
		 * System.out.println("Po usunieciu: " +
		 * tempPlayerCards.get(i).getCourt() + "" +
		 * tempPlayerCards.get(i).getSuit() + " " + i); }
		 */
		// Pobiera nowe karty
		List<Card> tempCards = giveCards(abandonedIndexes.size());
		for (int i = 0; i < tempCards.size(); ++i) {
			tempPlayerCards.add(tempCards.get(i));
			System.out
					.println("System: Oddaję graczowi: " + tempCards.get(i).getCourt() + "" + tempCards.get(i).getSuit() + " [" + i + "]");
		}
		player.ownCards = tempPlayerCards;
		return tempPlayerCards;
	}

	public List<Card> safeChangeCards(List<Integer> abandonedIndexes, Player player) {

		int numberCardsToReturn = abandonedIndexes.size();
		if (alreadyChangedCards.get(player.getPlayerID()))
			throw new IllegalStateException("Już wymieniałeś karty !");
		else
			alreadyChangedCards.set(player.getPlayerID(), true);
		if (numberCardsToReturn > 4) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		// Player tempPlayer = players.get(player.getPlayerID());
		List<Card> tempPlayerCards = playersCards.get(player.getPlayerID());
		Sorter s = new Sorter();
		s.sort(tempPlayerCards);

		/** usuwam karty po indeksie **/
		// SORTOWANIE AHAHAHAH :D
		int[] sorted = new int[abandonedIndexes.size()];
		for (int i = 0; i < abandonedIndexes.size(); ++i) {
			sorted[i] = abandonedIndexes.get(i);
		}
		Arrays.sort(sorted);
		for (int i = 0; i < abandonedIndexes.size(); ++i) {
			abandonedIndexes.set(i, sorted[abandonedIndexes.size() - 1 - i]);
		}
		// KONIEC SORTOWANIA
		for (int i = 0; i < numberCardsToReturn; ++i) {
			System.out.println("System: Gracz wymienia : [" + abandonedIndexes.get(i) + "]");
			tempPlayerCards.remove((int) abandonedIndexes.get(i));
		}
		/*
		 * for(int i = 0; i < tempPlayerCards.size(); ++i) {
		 * System.out.println("Po usunieciu: " +
		 * tempPlayerCards.get(i).getCourt() + "" +
		 * tempPlayerCards.get(i).getSuit() + " " + i); }
		 */
		// Pobiera nowe karty
		List<Card> tempCards = giveCards(abandonedIndexes.size());
		for (int i = 0; i < tempCards.size(); ++i) {
			tempPlayerCards.add(tempCards.get(i));
			System.out
					.println("System: Oddaję graczowi: " + tempCards.get(i).getCourt() + "" + tempCards.get(i).getSuit() + " [" + i + "]");
		}
		playersCards.set(player.getPlayerID(), tempPlayerCards);
		return tempPlayerCards;
	}

	/**
	 * wejscie gracza do gry kosztuje go wpisowe
	 * 
	 * @param player
	 */
	public void addPlayerToGame(Player player) {
		player.payChips(WPISOWE);
		pool = pool + WPISOWE;
		playersInGame.add(player);
	}

	public int getInitialChipsForPlayers() {
		return startZetony;
	}

	public static int getWpisowe() {
		return WPISOWE;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Player> getPlayersInGame() {
		return playersInGame;
	}

	public void setCurrentMax(int currentMax) {
		this.currentMax = currentMax;
	}

	public int getCurrentMax() {
		return currentMax;
	}

	public StatusEnum getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(StatusEnum roundStatus) {
		this.roundStatus = roundStatus;
	}

	public int getPool() {
		return pool;
	}

	public void setPool(int pool) {
		this.pool = pool;
	}

}
