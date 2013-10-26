import java.util.List;


public class Table {
	List<Player> players;
	List<List<Card>> endOfGame;

	int numHumans, numBots;
	Deck actualDeck;
	public Table(int numHumans, int numBots) {
		
		this.actualDeck = new Deck(); // Tutaj już mają być karty rozdane, znaczy się :-)
		// Dodawanie graczy przy starcie, trzeba dodać wyjątki
		for(int i = 0; i < numHumans; ++i) { players.add(new Human(actualDeck.giveCards(5), this)); }
		for(int i = numHumans; i < numHumans + numBots; ++i) { players.add(new Bot(actualDeck.giveCards(5), this)); }
		this.numHumans = numHumans;
		this.numBots = numBots;
	}

	
	// Metoda, która rozpoczyna grę

	public void startGame() {
		for (int i = 0; i < players.size(); ++i) {
			endOfGame.add((List<Card>) players.get(i).joinGame());
		}
	}
	// Metoda, która odbiera dla gracza od Deck żądane karty do wymiany

	List<Card> giveCards(int numbOfCards) {
		return actualDeck.giveCards(numbOfCards);
	}	
	
	
	
	// Metoda, która sprawdza kto wygrywa
	
	

	// dla Judge
	public List<Card> getPlayersCards(int nrPlayer) {
		if (nrPlayer > 4 || nrPlayer < 0) {
			throw new IllegalStateException("Niepoprawna ilosc graczy");
		}
		return players.get(nrPlayer).getOwnCards();
	}

}
