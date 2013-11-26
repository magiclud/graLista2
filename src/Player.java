import java.util.Iterator;
import java.util.List;

public abstract class Player {

	protected List<Card> ownCards;
	protected Table currentTable;
	private Sorter ownSorter = new Sorter();
	private int ownChips;
	private int score;
	// private Boolean alreadyChangedCards = false;

	private int chipsForBidding = 20;// minimalna stawka?
	private int playerID;

	private String newPlayerID;
	// public int newToBet;
	private boolean newAlreadyChangedCards = false;

	private StatusEnum playerStatus;

	public Player(List<Card> givenCards, Table currentTable, int playerID) {
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

	public int getChipsForBidding() {
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

	abstract int zacznijLicytacje();

	public List<Card> getOwnCards() {
		return ownCards;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public abstract boolean isHuman();

	public void payChips(int howMany) {
		ownChips = ownChips - howMany;
	}

	@Override
	public String toString() {
		return "Player id: " + playerID + ", karty: " + ownCards + ", wynik: " + checkScore();
	}

	public void check() {
		if (currentTable.getStatusPlayersInGame().equals(StatusEnum.ALL_IN)
				|| currentTable.getStatusPlayersInGame().equals(StatusEnum.RAISE)
				|| currentTable.getStatusPlayersInGame().equals(StatusEnum.BET)) {
			throw new IllegalStateException("Wczesniejszy gracz postawil stawke.\n Niemozlwy jest ruch CHECK.");
		}
		System.out.println("Player: Czekam");
		playerStatus = StatusEnum.CHECK;
	}

	public void bet(int chipsForBidding) {
		if (chipsForBidding < currentTable.getCurrentMax()) {
			throw new IllegalStateException("Za mało obstawiasz !");
		}
		payChips(chipsForBidding);
		currentTable.addToPool(chipsForBidding);
		// currentTable.setRoundStatus(StatusEnum.BET);
		currentTable.setCurrentMax(chipsForBidding);
		System.out.println("Player: BET -stawiam pierwsza stawke w danej rundzie");
		playerStatus = StatusEnum.BET;
	}

	public void raise(int chipsForBidding) {// throws Exception {//TODO
		if (chipsForBidding <= currentTable.getCurrentMax()) {
			throw new IllegalStateException("Za mało obstawiasz !");
		}
		payChips(chipsForBidding);
		currentTable.addToPool(chipsForBidding);
		currentTable.setCurrentMax(chipsForBidding);
		// /currentTable.setRoundStatus(StatusEnum.RAISE);
		playerStatus = StatusEnum.RAISE;
		System.out.println("Player: RAISE -przebijam najwyzszy do tej pory zaklad innego gracza");
	}

	public void allin() {
		// TODO co to niby sprawdza, bo nie rozumiem po co to jest? co by sie stao jakby tego nie bylo?
		if (currentTable.getCurrentMax() < chipsForBidding) {
			currentTable.setCurrentMax(chipsForBidding);
		}

		// TODO allIn bierze wszytskie zetony gracza (ownChips) i obstawia je do puli, sprawdz wszystkie wykorzystania
		// chipsForBidding
		// bo mam wrazenie, ze ty tego żle używasz!!!! Dajcie sobie spokuj z tym agielskim, bo sami potem nie rozumiecie
		// co piszecie!!!
		payChipsToPool(chipsForBidding);
		// currentTable.setRoundStatus(StatusEnum.ALL_IN);
		playerStatus = StatusEnum.ALL_IN;
		System.out.println("Player: ALL_IN -stawiam wszystko");

	}

	public void call() {
		// TODO chipsForBidding - co wg ciebie przechodwuje ta zmienna? bo wg mnie to ile w danej licytacji postawi
		// player zetonow,
		// tu wg mnie powinno sie sprawdzac, czy zetony ktore posiada gracz ownChips sa wystarczajace?
		if (chipsForBidding < currentTable.getCurrentMax()) {
			throw new IllegalStateException("Za mało masz coins !");
		}

		payChipsToPool(chipsForBidding);
		// currentTable.setRoundStatus(StatusEnum.CALL);
		playerStatus = StatusEnum.CALL;
		System.out.println("Player: CALL -wyrównuje");
	}

	public void payChipsToPool(int chipsToPool) {
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
