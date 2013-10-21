import java.util.ArrayList;


public class Table {
	ArrayList<Player> players;
	int numHumans, numBots;
	Deck actualDeck;
	public Table(int numHumans, int numBots) {
		
		this.actualDeck = new Deck(); // Tutaj już mają być karty rozdane :-)
		// Dodawanie graczy przy starcie, trzeba dodać wyjątki
		for(int i = 0; i < numHumans; ++i) { players.add(new Human(actualDeck.giveCards(5))); }
		for(int i = numHumans; i < numHumans + numBots; ++i) { players.add(new Bot(actualDeck.giveCards(5))); }
		this.numHumans = numHumans;
		this.numBots = numBots;
	}

	
	// Metoda, która nadaje graczowi możliwość gry
	
	// Metoda, która odbiera od gracza request o wymianę kart
	
	// Metoda, która sprawdza kto wygrywa
	
	

}
