import java.util.List;

public abstract class Player {

	protected List<Card> ownCards;
	protected Table currentTable;
	private Sorter ownSorter = new Sorter();
	public int ownChips;
	// private Boolean alreadyChangedCards = false;

	public int chipsForBidding;
	public int playerID;

	public String newPlayerID;
	public int newToBet;
	boolean newAlreadyChangedCards = false;

	StatusEnum playerStatus;

	public Player(List<Card> givenCards, Table currentTable, int playerID) { // Player
																				// ma
		// przecież
		// dostawać
		// karty od stołu !
		if (givenCards.size() != 5) {
			throw new IllegalStateException("Niepoprawna ilosc kart dla gracza");
		}
		this.playerID = playerID;
		this.ownCards = givenCards;
		this.currentTable = currentTable;
		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		ownSorter.sort(ownCards);
		this.ownChips = currentTable.getInitialChipsForPlayers();
	}

	public int getPlayerID() {
		return playerID;
	}

	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + ownCards.get(i).getCourt() + ownCards.get(i).getSuit() + " ");
		}
	}
	
	void changeCardsStrategy(){}

	public int getOwnChips() {
		return ownChips;
	}

	public void setOwnChips(int ownChips) {
		this.ownChips = ownChips;
	}

	public int chipsForBidding(){
		return chipsForBidding;
	}

	public void joinGame() {
		// wykorzystanie metody toString2
		System.out.println("Dolaczam do gry - " + this);
		currentTable.addPlayerToGame(this);
	}

	public SequenceEnum checkScore() {
		return Checker.checkScore(ownCards);
	}

	// // żądanie kart od stołu, tutaj kontrolujemy wyjątkami liczbę arraylist
	// itemków i numbOfCards żądanych !
	public void requestCards(List<Integer> abandonedIndexes) {

		List<Card> tempCards = currentTable.safeChangeCards(abandonedIndexes, this);

		// Sortuje karty
		this.ownCards = tempCards;
		ownSorter.sort(ownCards);

	}
	abstract List<Card> playGame(); // metoda która zmienia karty playera, 
									// zwraca jakie ma po wymianie
	abstract void gameStrategy();
	abstract int zacznijLicytacje();

	public List<Card> getOwnCards() {
		return ownCards;
	}

	public abstract boolean isHuman();

	public void payChips(int howMany) {
		ownChips = ownChips - howMany;
	}

	@Override
	public String toString() {
		return "Player id: " + playerID + ", karty: " + ownCards + ", wynik: " + checkScore();
	}

}
