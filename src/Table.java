import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Table {

	private List<Player> players = new ArrayList<>();
	private List<List<Card>> playersCards = new ArrayList<>();
	private Random randomPlayer = new Random();
	private List<List<Card>> endOfGame;
	private List<Boolean> alreadyChangedCards = new ArrayList<>();
	private List<Player> playersInGame = new ArrayList<>();
	private List<Integer> winners = new ArrayList<>();

	private int startWpisowe, startZetony;
	private int pool = 0; // pula gry

	private int numHumans, numBots;

	private int currentMax = startWpisowe; // ile maksymalnie ostatnio
											// obstawiono

	private StatusEnum roundStatus = StatusEnum.CLEAN;// początkowy status
	// rundy

	private Deck actualDeck;

	// Zwraca wskaźnik na playera jeżeli znaleziono takiego w naszej puli graczy
	public Player findPlayer(String szukanyPlayerID) {
		for (int i = 0; i < players.size(); ++i) {
			if (players.get(i).getNewPlayerID().equals(szukanyPlayerID))
				return players.get(i);
		}
		return null;
	}

	public List<StatusEnum> getStatusPlayersInGame() {
		List<StatusEnum> statusPlayersInGame = new ArrayList<>();
		for (Player player : getPlayersInGame()) {
			statusPlayersInGame.add(player.getPlayerStatus());
		}
		return statusPlayersInGame;
	}

	public Table() {
	};

	public Table(int numHumans, int numBots) throws ExceptionsInGame {

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

	// public void startGame() {
	//
	// int currentMax = startWpisowe;
	//
	// }

	/*
	 * void startGame() {
	 * 
	 * StatusEnum status = StatusEnum.CLEAN; // int currentMax = minimum; //
	 * ustaw każdemu graczowi pole newToBet na minimum ! ! ! void startRound() {
	 * void makeRequest(PlayerID) //TO BĘDZIE NASŁUCHIWANE ACTION LISTENEREM
	 * PRZEZ SERVER ADAPTER }
	 * 
	 * }
	 */
	void check() {
		rozpocznijRunde();
	}

	public void losujGraczaRozpoczynajacego() {
		List<Player> tempListOfPlayers = new ArrayList();
		// losuje gracza rozpoczynajacego runde
		Player random = playersInGame.get(playersInGame.size());
		tempListOfPlayers.add(random);
		playersInGame.remove(random);
		// nastepnie do listy grajacych dodaje pozostalych graczy
		for (int i = 0; i < playersInGame.size(); i++) {
			tempListOfPlayers.add(playersInGame.get(i));
		}
		playersInGame.clear();
		playersInGame = tempListOfPlayers;

	}

	public void ustawGraczaRozpoczynajacego() {
		if (getPlayersInGame().size() == 4) {
			Player temp = getPlayersInGame().get(3);
			getPlayersInGame().set(3, getPlayersInGame().get(0));
			getPlayersInGame().set(0, getPlayersInGame().get(1));
			getPlayersInGame().set(1, getPlayersInGame().get(2));
			getPlayersInGame().set(2, temp);
		}
		if (getPlayersInGame().size() == 3) {
			Player temp = getPlayersInGame().get(2);
			getPlayersInGame().set(2, getPlayersInGame().get(0));
			getPlayersInGame().set(0, getPlayersInGame().get(1));
			getPlayersInGame().set(1, temp);
		}
		Player temp = getPlayersInGame().get(1);
		getPlayersInGame().set(1, getPlayersInGame().get(0));
		getPlayersInGame().set(0, temp);
	}

	public void sprawdzCzyGraczeMajaWystarczajacaIloscZetonow() {
		for (Player player : getPlayersInGame()) {
			if (player.getOwnChips() < getStartWpisowe()) {
				// usuwam gracza z listy grajacych gdy ma mniej zetonow niz
				// wynosi wpisowe
				getPlayersInGame().remove(player);
			}
		}
	}

	public void rozpocznijRunde() {
		// 1. ktory gracz pierwszy ? byc moze zrob jakas liste playerow

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

	/*********************************************
	 * do usuniecia ***************
	 * 
	 * public void bet(String PlayerID, int howMuch) throws Exception { Player
	 * player = findPlayer(PlayerID); if (howMuch < currentMax) throw new
	 * IllegalStateException("Za mało obstawiasz !"); if (howMuch >
	 * player.getChipsForBidding());{ throw new
	 * IllegalStateException("Za mało masz coins żeby tak zrobić !");} //
	 * roundStatus = StatusEnum.BET; currentMax = howMuch; }
	 * 
	 * // \/\/\/\/\/\/ bardzo WAŻNE ! ! ! ! ! przykłąd /* void bet(String
	 * PlayerID, int howMuch) throws Exception { Player player =
	 * findPlayer(PlayerID); if(howMuch < currentMax) throw new
	 * IllegalStateException("za mało obstawiasz !"); changeStatus
	 * player.newToBet = howmuch; currentMax = newToBet
	 * 
	 * }
	 */

	public List<Card> giveCards(int numbOfCards) {
		return actualDeck.giveCards(numbOfCards);
	}

	public List<Card> newSafeChangeCards(List<Integer> abandonedIndexes, String PlayerID) {
		int numberCardsToReturn = abandonedIndexes.size();
		Player player = findPlayer(PlayerID);
		if (player.isNewAlreadyChangedCards())
			throw new IllegalStateException("Już wymieniałeś karty !");
		else
			player.setNewAlreadyChangedCards(true);
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

	public void setCoinsIfOnePlayerWin() {
		if (winners.size() == 1) {
			// przekazanie wygranych pieniedzy graczowi
			players.get(getWinners().get(0)).winChips(pool);
			pool = 0;
		}
	}

	/**
	 * wejscie gracza do gry kosztuje go wpisowe
	 * 
	 * @param player
	 */
	public void addPlayerToGame(Player player) {
		player.payChipsToPool(startWpisowe);
		playersInGame.add(player);
	}

	public int getInitialChipsForPlayers() {
		return startZetony;
	}

	public void setInitialChipsForPlayers(int startZetony) {
		this.startZetony = startZetony;
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

	public List<Integer> getWinners() {
		return winners;
	}

	public void setWinners(List<Integer> winners) {
		this.winners = Judge.selectWinners(getPlayers());
	}

	public int getStartWpisowe() {
		return startWpisowe;
	}

	public void setStartWpisowe(int startWpisowe) {
		this.startWpisowe = startWpisowe;
	}

	/**
	 * sprawdza czy licytacja sie zakonczyla
	 * 
	 * @return
	 */
	public boolean isBiddingOver() {
		int iloscAll_inWGrze = 0, iloscRaiseWGrze = 0;
		if (playersInGame.size() == 0) {
			System.out.println("Wszyscy gracze spasowali. \n Koniec rundy.");
			getStatusPlayersInGame().clear();
			return true;
		}
		if (playersInGame.size() == 1) {
			System.out.println("Jeden gracz zgarnia cala pule: " + getPool());
			Player oneWinner = players.get(getWinners().get(0));
			oneWinner.increaseScore();// dodaj punkt graczowi
			setCoinsIfOnePlayerWin();// przekaz mu kase
			currentMax = 0;
			return true;
		}
		// zliczam ile razy wystapilo All-in i ile razy raise w statusach graczy
		// jesli raz to znaczy ze doszlo juz do konca licytacji
		for (Player player : getPlayersInGame()) {
			if (getStatusPlayersInGame().contains(StatusEnum.ALL_IN)) {
				iloscAll_inWGrze++;
			}
			if (getStatusPlayersInGame().contains(StatusEnum.ALL_IN)) {
				iloscRaiseWGrze++;
			}
		}
		// gracze mogli zagrac jedynie call, check, fold, bo gdyby zagralii
		// all-in lub raise to licytacja nadal trwa
		if ((!getStatusPlayersInGame().contains(StatusEnum.ALL_IN) && !getStatusPlayersInGame().contains(StatusEnum.RAISE))
				|| iloscAll_inWGrze == 1 || iloscRaiseWGrze == 1) {
			System.out.println("Gracze wyrownali swoje stawki");
			getStatusPlayersInGame().clear();
			currentMax = 0;
			return true;
		}
		return false;
	}

	public void addToPool(int chipsForBidding) {
		pool = pool + chipsForBidding;
	}
}
