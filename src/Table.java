import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {

	List<Player> players = new ArrayList<>();
	List<List<Card>> playersCards = new ArrayList<>();
	List<List<Card>> endOfGame;
	List<Boolean> alreadyChangedCards = new ArrayList<>();
	private int pool; // pula gry
	private int initialChipsForPlayers = 1000;

	int numHumans, numBots;
// SZKIC
	int startZetony, startWpisowe;

	Deck actualDeck;
	// Zwraca wskaźnik na playera jeżeli znaleziono takiego w naszej puli graczy
	Player findPlayer(String szukanyPlayerID) {
		for(int i = 0; i < players.size(); ++i) {
			if(players.get(i).newPlayerID.equals(szukanyPlayerID))
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

	/*
	 * public void startGame() { for (int i = 0; i < players.size(); ++i) {
	 * endOfGame.add(players.get(i).joinGame()); } }
	 */
	// Metoda, która odbiera dla gracza od Deck żądane karty do wymiany

	/* void startGame() {
	 * 
	 * StatusEnum status = StatusEnum.CLEAN;
	 * // int currentMax = minimum;
	 * // ustaw każdemu graczowi pole newToBet na minimum ! ! !
	 * void startRound() {
	 * void makeRequest(PlayerID) //TO BĘDZIE NASŁUCHIWANE ACTION LISTENEREM PRZEZ SERVER ADAPTER
	 * }
	 * 
	 * } */
	void check() {

	}
	// \/\/\/\/\/\/ bardzo WAŻNE ! ! ! !  ! przykłąd
	/* void bet(String PlayerID, int howMuch) throws Exception {
	 * Player player = findPlayer(PlayerID);
	 * if(howMuch < currentMax)
	 * throw new IllegalStateException("za mało obstawiasz !");
	 * changeStatus
	 * player.newToBet = howmuch;
	 * currentMax = newToBet
	 * 
	 * } */
	void raise() {

	}
	void call() {

	}
	void fold() {

	}
	void allin() {

	}

	public int getInitialChipsForPlayers() {
		return initialChipsForPlayers;
	}


	List<Card> giveCards(int numbOfCards) {
		return actualDeck.giveCards(numbOfCards);
	}

	List<Card> newSafeChangeCards(List<Integer> abandonedIndexes, String PlayerID) {
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

	List<Card> safeChangeCards(List<Integer> abandonedIndexes, Player player) {

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

	// Metoda, która sprawdza kto wygrywa



}
