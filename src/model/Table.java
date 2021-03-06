package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.PublicInformation;

public class Table {
	// Ta lista odpowiada List<PlayerWindow> playerWindowsList z klasy
	// Rozgrywka, jest tak samo indeksowana
	private List<Player> players = new ArrayList<>();
	private List<Player> playersInGame;
	private List<Integer> winners = new ArrayList<>();

	private int startWpisowe, startZetony;
	private int pool = 0; // pula gry

	private int currentMax; // ile maksymalnie ostatnio
							// obstawiono
	public int maxNumbOfPlayers;
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

	public void updatePlayerInformation(int playerIndex, PublicInformation playerInformation) {
		players.get(playerIndex).updatePublicInformation(playerInformation);
	}

	public PublicInformation getPlayerInformation(int playerIndex, PublicInformation playerInformation) {
		return players.get(playerIndex).getPublicInformation(playerInformation);
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

	public Table(int numHumans, int numBots, int poczatkoweZetony, int startWpisowe) {

		this.startWpisowe = startWpisowe;
		this.startZetony = poczatkoweZetony;
		this.actualDeck = new Deck(); // Tutaj już mają być karty rozdane,
		this.maxNumbOfPlayers = numHumans;
		// znaczy się :-)
		// Dodawanie graczy przy starcie, trzeba dodać wyjątki
		if (numHumans + numBots < 2 || numHumans + numBots > 4)
			throw new IllegalStateException("Niepoprawna ilość graczy.");
		for (int i = 0; i < numHumans; ++i) {
			try {
				players.add(new Human(actualDeck.giveCards(5), this, i));
			} catch (ExceptionsInGame e) {
				System.out.println("Cos nie tak przy tworzeniu humana");
			}
		}
		for (int i = numHumans; i < numHumans + numBots; ++i) {
			try {
				players.add(new Bot(actualDeck.giveCards(5), this, i));
			} catch (ExceptionsInGame e) {
				System.out.println("Cos nie tak przy tworzeniu bota");

			}
		}

		// trzeba ustawaic cos co bedzie mowilo ile graczy faktycznie chce
		// zagrac i zaplaciic
		//
	}

	void addPlayer(int oldPlayerID) {

		try {
			players.add(new Human(actualDeck.giveCards(5), this, oldPlayerID));
		} catch (ExceptionsInGame e) {
			e.getMessage();
		}
	}

	void check() {
		rozpocznijRunde();
	}

	// public List<PlayerWindow> losujGraczaRozpoczynajacego(List<PlayerWindow>
	// playerWindowsList) {
	// playersInGame = new ArrayList();
		// for(int i; i<playerWindowsList.size(); i++){
		// playersInGame.add(playerWindowsList.get(i).);
		// }
		// // losuje gracza rozpoczynajacego runde
		// Player random = playerWindowsList.get(playerWindowsList.size());
		// playerWindowsList.add(random);
		// playersInGame.remove(random);
		// // nastepnie do listy grajacych dodaje pozostalych graczy
		// for (int i = 0; i < playersInGame.size(); i++) {
		// tempListOfPlayers.add(playersInGame.get(i));
		// }
		// this.playersInGame.clear();
		// this.playersInGame = tempListOfPlayers;
		// return playerWindowsList.g;

	// }

	public void ustawGraczaRozpoczynajacego() {
		if (getPlayersInGame().size() == 4) {
			// jesli plajerow grajacych jest cztery to zamieniam ich miejscami
			// przesuwajac kazdego o 1 pozycje w lewo
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
		Iterator<Player> iterator = playersInGame.iterator();

		while (iterator.hasNext()) {

			Player player = iterator.next();

			if (player.getOwnChips() < startWpisowe) {
				// usuwam gracza z listy grajacych gdy ma mniej zetonow niz
				// wynosi wpisowe
				iterator.remove();
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
		player.zaplacWpisowe(startWpisowe);
		playersInGame.add(player);
	}

	public int getInitialChipsForPlayers() {
		return startZetony;
	}

	// public void setInitialChipsForPlayers(int startZetony) {
	// this.startZetony = startZetony;
	// }

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
		if (sprawdzCzyGraczeMajaJednakoweStawkiWRundzie()) {
			System.out.println("Gracze wyrownali swoje stawki");
			getStatusPlayersInGame().clear();
			currentMax = 0;
			return true;
		}

		return false;
	}

	/**
	 * ignorujemy grazcy ktorzy maja allIn, bo oni moga miec miejsza kwota niz
	 * wszyscy, ale jesli inni maja taka sama to licytacja jest skonczona
	 * 
	 * @return
	 */
	private boolean sprawdzCzyGraczeMajaJednakoweStawkiWRundzie() {

		int stawkaJakiegosGracza = playersInGame.get(0).getObecniePostawioneZetony();
		System.out.println("stawkaJakiegosGracza " + stawkaJakiegosGracza);
		for (Player player : playersInGame) {
			if (player.getPlayerStatus() != StatusEnum.ALL_IN) {

				if (stawkaJakiegosGracza == 0 && player.getObecniePostawioneZetony() == 0) {
					// oznacza to ze jeszcze gracze nie zaczeli licytowac
					return false;
				}
				if (stawkaJakiegosGracza != player.getObecniePostawioneZetony()) {
					return false;
				}
			}
		}
		return true;

	}

	public void addToPool(int chipsForBidding) {
		pool = pool + chipsForBidding;
	}
}
