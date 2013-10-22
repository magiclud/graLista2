import java.util.ArrayList;


public class Table {
	ArrayList<Player> players;
	ArrayList<ArrayList<Card>> endOfGame;

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
		for(int i = 0; i < players.size(); ++i) {endOfGame.add(players.get(i).joinGame());}
	}
	// Metoda, która odbiera dla gracza od Deck żądane karty do wymiany

	ArrayList<Card> giveCards(int numbOfCards) {
		return actualDeck.giveCards(numbOfCards);
	}	
	
	
	
	// Metoda, która sprawdza kto wygrywa
	
	

}
