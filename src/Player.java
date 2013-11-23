import java.util.List;

public abstract class Player {

	protected List<Card> ownCards;
	private Table currentTable;
	private Sorter ownSorter = new Sorter();
	private int ownChips;
	// private Boolean alreadyChangedCards = false;

	public int chipsForBidding;
	public int playerID;

	public String newPlayerID;
	public int newToBet;
	boolean newAlreadyChangedCards = false;
	
	StatusEnum playerStatus = null;

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
		this.ownChips = currentTable.getNumChips();
	}

	public int getPlayerID() {
		return playerID;
	}

	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + ownCards.get(i).getCourt() + ownCards.get(i).getSuit() + " ");
		}
	}

	public int getOwnChips() {
		return ownChips;
	}

	public void setOwnChips(int ownChips) {
		this.ownChips = ownChips;
	}

	public int chipsForBidding(){
		return chipsForBidding;
	}

	abstract List<Card> joinGame(); // Jak human albo bot będzie sobie grał

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

	public List<Card> getOwnCards() {
		return ownCards;
	}

}
