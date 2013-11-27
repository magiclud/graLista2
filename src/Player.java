import java.util.Iterator;
import java.util.List;

public abstract class Player {

	protected List<Card> ownCards;
	protected Table currentTable;
	private Sorter ownSorter = new Sorter();
	private int ownChips;
	private int score;
	// private Boolean alreadyChangedCards = false;

	private int playerID;
	int obecniePostawioneZetony;

	public int getObecniePostawioneZetony() {
		return obecniePostawioneZetony;
	}

	private String newPlayerID;
	// public int newToBet;
	private boolean newAlreadyChangedCards = false;

	private StatusEnum playerStatus;

	public Player(List<Card> givenCards, Table currentTable, int playerID) throws ExceptionsInGame {
		if (givenCards.size() != 5) {
			throw new ExceptionsInGame("Niepoprawna ilosc kart dla gracza");
		}
		this.playerID = playerID;
		this.ownCards = givenCards;
		this.currentTable = currentTable;
		// tu ma byc pierwsze sortowanie kart - kolejne po wymianie
		ownSorter.sort(ownCards);
		this.ownChips = currentTable.getInitialChipsForPlayers();
	}

	public boolean isNewAlreadyChangedCards() {
		return newAlreadyChangedCards;
	}

	public String getNewPlayerID() {
		return newPlayerID;
	}

	public void setNewAlreadyChangedCards(boolean newAlreadyChangedCards) {
		this.newAlreadyChangedCards = newAlreadyChangedCards;
	}

	public StatusEnum getPlayerStatus() {
		return playerStatus;
	}

	public int getPlayerID() {
		return playerID;
	}

	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + ownCards.get(i).getCourt() + ownCards.get(i).getSuit() + " ");
		}
	}

	void changeCardsStrategy() {
	}

	public int getOwnChips() {
		return ownChips;
	}

	public void joinGame() {
		// wykorzystanie metody toString2
		currentTable.addPlayerToGame(this);
		System.out.println("Dolaczam do gry playera o id:- " + getPlayerID());

	}

	public SequenceEnum checkScore() {
		return Checker.checkScore(ownCards);
	}

	public void changeCards(List<Integer> cardIndexesToChange) {
		removerCards(cardIndexesToChange);
		addNewCards(cardIndexesToChange.size());
	}

	private void addNewCards(int howManyToAdd) {
		List<Card> newCards = currentTable.giveCards(howManyToAdd);
		ownCards.addAll(newCards);
		ownSorter.sort(ownCards);
	}

	private void removerCards(List<Integer> cardIndexesToChange) {
		Iterator<Card> iterator = ownCards.iterator();
		int index = 0;
		// do usuwania wygodniej uzyc iteratora
		while (iterator.hasNext()) {
			iterator.next();
			if (cardIndexesToChange.contains(index)) {
				iterator.remove();
			}
			index++;
		}
	}

	abstract void gameStrategy();

	abstract int zacznijLicytacje(int currentMax, List<ActionsEnum> possibleMoves, List<StatusEnum> playersMoves);

	public List<Card> getOwnCards() {
		return ownCards;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore() {
		this.score = score++;
	}

	public abstract boolean isHuman();

	@Override
	public String toString() {
		return "Player id: " + playerID + ", karty: " + ownCards + ", wynik: " + checkScore();
	}

	public void check() throws ExceptionsInGame {
		// w ifie sprawdzam czy ktos z graczy dal all-in, raise lub bet - gdyz
		// wowczs nie moge CHECK
		if (currentTable.getStatusPlayersInGame().contains(StatusEnum.ALL_IN)
				|| currentTable.getStatusPlayersInGame().contains(StatusEnum.RAISE)
				|| currentTable.getStatusPlayersInGame().contains(StatusEnum.BET)) {
			throw new ExceptionsInGame("Wczesniejszy gracz postawil stawke.\n Niemozlwy jest ruch CHECK.");
		}
		System.out.println("Player: Czekam");
		playerStatus = StatusEnum.CHECK;
	}

	public void bet(int chipsForBidding) {
		// if (chipsForBidding < currentTable.getCurrentMax()) {
		// usunela if bo bet ma byc tylko przy pierwszej licytacji
		payChipsToPool(chipsForBidding);
		currentTable.setCurrentMax(chipsForBidding);
		System.out.println("Player: BET -stawiam pierwsza stawke w danej rundzie");
		playerStatus = StatusEnum.BET;
	}

	public void raise(int chipsForBidding) throws ExceptionsInGame {
		if (chipsForBidding <= currentTable.getCurrentMax()) {
			throw new ExceptionsInGame("Za mało obstawiasz !");
		}
		payChipsToPool(chipsForBidding);
		currentTable.setCurrentMax(chipsForBidding);
		playerStatus = StatusEnum.RAISE;
		System.out.println("Player: RAISE -przebijam najwyzszy do tej pory zaklad innego gracza");
	}

	public void allin() {
		// sprawdzam czy obecny najwyzsze zagranie jest mniejsze niz ilosc
		// zetonow jaka chce teraz zagrac gracz - jest tak wowczas ustawiam nowe
		// najwyzsze zagranie
		// TODO a jesli ten if nie jest speniony ?
		if (currentTable.getCurrentMax() < ownChips) {
			currentTable.setCurrentMax(ownChips);
		}
		payChipsToPool(ownChips);
		playerStatus = StatusEnum.ALL_IN;
		System.out.println("Player: ALL_IN -stawiam wszystko");

	}

	public void call() throws ExceptionsInGame {
		// sprawdzam czy zetony ktore posiada gracz ownChips sa
		// wystarczajace
		if (ownChips < currentTable.getCurrentMax()) {
			throw new ExceptionsInGame("Za mało masz coins !");
		}

		payChipsToPool(currentTable.getCurrentMax());
		playerStatus = StatusEnum.CALL;
		System.out.println("Player: CALL -wyrównuje");
	}

	public void payChipsToPool(int chipsToPool) {
		obecniePostawioneZetony = chipsToPool;
		ownChips = ownChips - chipsToPool;
		currentTable.addToPool(chipsToPool);
	}

	public void fold() {
		playerStatus = StatusEnum.FOLD;
		currentTable.getPlayersInGame().remove(playerID);
		System.out.println("Player: FOLD -pasuje");
	}

	public void winChips(int chips) {
		ownChips = ownChips + chips;
	}
}
