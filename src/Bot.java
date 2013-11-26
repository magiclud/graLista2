import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
	private Random botCards = new Random();
	private Random botsCoins = new Random();
	private MakeOrder order;
	private Sorter s;

	public Bot(List<Card> givenCards, Table currentTable, int playerID) {
		super(givenCards, currentTable, playerID);
		s = new Sorter();
		s.sort(ownCards);
		order = new MakeOrder(this.ownCards);
		order.doTheJob();
	}

	@Override
	public boolean isHuman() {
		return false;
	};

	// Losowanie różnych liczb ze zbioru 0. .5
	public List<Integer> randomDifferentNumbers(int howMany) {
		if (howMany > 5 || howMany < 0)
			throw new IllegalStateException("Niepoprawna ilość kart jakie chcesz wylosować !");
		List<Integer> differentNumbers = new ArrayList<Integer>();
		int[] differentIndexes = new int[5];
		for (int i = 0; i < 5; ++i) {
			differentIndexes[i] = i;
		}
		for (int j = 0; j < howMany; ++j) {
			int temp = botCards.nextInt(5 - j);
			differentNumbers.add(differentIndexes[temp]);

			int temp2 = differentIndexes[5 - j - 1];
			differentIndexes[5 - j - 1] = differentIndexes[temp];
			differentIndexes[temp] = temp2;
		}
		return differentNumbers;
	}

	public void wybierzLosowo() {
		List<Integer> abandonedIndexes = new ArrayList<Integer>();
		int howMany;

		howMany = botCards.nextInt(5);

		abandonedIndexes = randomDifferentNumbers(howMany);
		changeCards(abandonedIndexes);
	}

	// Rozgrywka z prywatną strategią
	public void changeCardsUsingStrategy() {
		if (order.maxRowsSizes.get(0) == 5 && order.orderBySuitQuantity.get(0) == 5)
			return;
		// Co się dzieje jak jest prawie poker ?
		if (order.maxRowsSizes.get(0) == 4 && order.orderBySuitQuantity.get(0) == 4) {
			if (order.maxRowsEndCard.get(0).getSuit().equals(order.orderBySuitQuality.get(0).getSuit())) {
				List<Integer> abandonedIndexes = new ArrayList<Integer>();
				for (int i = 0; i < 5; ++i) {
					if (!ownCards.get(i).getSuit().equals(order.orderBySuitQuality.get(0).getSuit()))
						abandonedIndexes.add(i);
				}
				changeCards(abandonedIndexes);
				for (int i = 0; i < abandonedIndexes.size(); ++i) {
					System.out.println(abandonedIndexes.get(i));
				}
				return;
			}
		}
		if (order.orderByCourtQuantity.get(0) == 4)
			return;
		if (order.orderByCourtQuantity.get(0) == 3 && order.orderByCourtQuantity.get(1) == 2)
			return;
		// Co się dzieje jak jest prawie ful i prawie kareta ?
		// Patrz jak są 3 takie same karty, wymien pozostale 2
		if (order.orderBySuitQuantity.get(0) == 5)
			return;
		if (order.orderBySuitQuantity.get(0) == 4) {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 5; ++i) {
				if (!ownCards.get(i).getSuit().equals(order.orderBySuitQuality.get(0).getSuit()))
					abandonedIndexes.add(i);
			}
			return;
		}
		if (order.maxRowsSizes.get(0) == 5)
			return;
		if (order.maxRowsSizes.get(0) == 4) {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 5; ++i) {
				if (ownCards.get(i).getCourt().ordinal() > (order.maxRowsEndCard.get(0).getCourt().ordinal())
						|| ownCards.get(i).getCourt().ordinal() < (order.maxRowsEndCard.get(0).getCourt().ordinal() - 4))
					abandonedIndexes.add(i);
			}
			changeCards(abandonedIndexes);

			return;
		}
		// Jak są 3 takie same karty, wymień pozostałe 2
		if (order.orderByCourtQuantity.get(0) == 3) {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 5; ++i) {
				if (!ownCards.get(i).getCourt().equals(order.orderByCourtQuality.get(0).getCourt()))
					abandonedIndexes.add(i);
			}
			changeCards(abandonedIndexes);

			return;
		}
		// Jak jest układ 2 pary, wymień 1 kartę
		if (order.orderByCourtQuantity.get(0) == 2 && order.orderByCourtQuantity.get(1) == 2) {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 5; ++i) {
				if (!ownCards.get(i).getCourt().equals(order.orderByCourtQuality.get(0).getCourt())
						&& !ownCards.get(i).getCourt().equals(order.orderByCourtQuality.get(1).getCourt()))
					abandonedIndexes.add(i);
			}
			changeCards(abandonedIndexes);

			return;
		}
		// Jak jest układ 1 para, wymień 3 karty
		if (order.orderByCourtQuantity.get(0) == 2) {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 5; ++i) {
				if (!ownCards.get(i).getCourt().equals(order.orderByCourtQuality.get(0).getCourt()))
					abandonedIndexes.add(i);

			}
			changeCards(abandonedIndexes);

			return;
		}
		// W przeciwnym razie wymień 4 karty :D
		else {
			List<Integer> abandonedIndexes = new ArrayList<Integer>();
			for (int i = 0; i < 4; ++i) {
				abandonedIndexes.add(i);
			}
			changeCards(abandonedIndexes);
			return;
		}

	}

	/**
	 * losuje czy bot doacza do gry
	 * 
	 * @return
	 */
	public boolean randomIfJoinTogame() {
		Random random = new Random();
		return random.nextBoolean();
	}

	@Override
	public void gameStrategy() {

	}

	@Override
	int zacznijLicytacje(int currentMax, List<ActionsEnum> possibleMoves, List<StatusEnum> playersMoves) {
		// wstępne obliczenia
		order = new MakeOrder(this.ownCards);
		order.doTheJob();
		Random generator = new Random();

		if (order.maxRowsSizes.get(0) == 5 && order.orderBySuitQuantity.get(0) == 5) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
				return chipsForBidding;
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
				return chipsForBidding;
			}
			if (possibleMoves.contains(ActionsEnum.ALL_IN)) {
				allIn();
				return chipsForBidding;
			}
		}
		// Co się dzieje jak jest prawie poker ?
		if (order.maxRowsSizes.get(0) == 4 && order.orderBySuitQuantity.get(0) == 4) {
			if (order.maxRowsEndCard.get(0).getSuit().equals(order.orderBySuitQuality.get(0).getSuit())) {
				if (possibleMoves.contains(ActionsEnum.BET)) {
					if (generator.nextDouble() < 0.3) {
						bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
						return chipsForBidding;
					}
				}
				if (possibleMoves.contains(ActionsEnum.RAISE)) {
					if (generator.nextDouble() < 0.3) {
						raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
						return chipsForBidding;
					}
				}
				if (possibleMoves.contains(ActionsEnum.CHECK)) {
					check(currentMax);
					return chipsForBidding;
				}
			}
		}
		if (order.orderByCourtQuantity.get(0) == 4) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.8) {
					bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.8) {
					raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		if (order.orderByCourtQuantity.get(0) == 3 && order.orderByCourtQuantity.get(1) == 2) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.6) {
					bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.6) {
					raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		// Co się dzieje jak jest prawie ful i prawie kareta ?
		// Patrz jak są 3 takie same karty, wymien pozostale 2
		if (order.orderBySuitQuantity.get(0) == 5) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.5) {
					bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.5) {
					raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		if (order.orderBySuitQuantity.get(0) == 4) {

			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.25) {
					bet(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.25) {
					raise(currentMax + (int) (generator.nextDouble() * 0.6 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		if (order.maxRowsSizes.get(0) == 5) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.4) {
					bet(currentMax + (int) (generator.nextDouble() * 0.4 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.4) {
					raise(currentMax + (int) (generator.nextDouble() * 0.4 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		if (order.maxRowsSizes.get(0) == 4) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.15) {
					bet(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.15) {
					raise(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		// Jak są 3 takie same karty, wymień pozostałe 2
		if (order.orderByCourtQuantity.get(0) == 3) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.15) {
					bet(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.15) {
					raise(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		// Jak jest układ 2 pary, wymień 1 kartę
		if (order.orderByCourtQuantity.get(0) == 2 && order.orderByCourtQuantity.get(1) == 2) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.15) {
					bet(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.15) {
					raise(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		// Jak jest układ 1 para, wymień 3 karty
		if (order.orderByCourtQuantity.get(0) == 2) {
			if (possibleMoves.contains(ActionsEnum.BET)) {
				if (generator.nextDouble() < 0.15) {
					bet(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.RAISE)) {
				if (generator.nextDouble() < 0.15) {
					raise(currentMax + (int) (generator.nextDouble() * 0.3 * currentMax), currentMax);
					return chipsForBidding;
				}
			}
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
		}
		// W przeciwnym razie wymień 4 karty :D
		else {
			if (possibleMoves.contains(ActionsEnum.CHECK)) {
				check(currentMax);
				return chipsForBidding;
			}
			if (possibleMoves.contains(ActionsEnum.CALL)) {
				if (generator.nextDouble() < 0.1) {
					call(currentMax);
					return chipsForBidding;
				}
			}
			fold(currentMax);
			return currentMax;
		}

		return 0;
	}

	public int chipsToRaise() {
		Random generator = new Random();
		if (order.maxRowsSizes.get(0) == 5 && order.orderBySuitQuantity.get(0) == 5) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.6 * currentTable.getCurrentMax());
		}
		// jest prawie poker
		if (order.maxRowsSizes.get(0) == 4 && order.orderBySuitQuantity.get(0) == 4
				&& order.maxRowsEndCard.get(0).getSuit().equals(order.orderBySuitQuality.get(0).getSuit())) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.6 * currentTable.getCurrentMax());
		}
		if ((order.orderByCourtQuantity.get(0) == 4) || (order.orderByCourtQuantity.get(0) == 3 && order.orderByCourtQuantity.get(1) == 2)) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.6 * currentTable.getCurrentMax());
		}
		// jest prawie ful i prawie kareta ?
		if (order.orderBySuitQuantity.get(0) == 5 || (order.orderBySuitQuantity.get(0) == 4)) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.5 * currentTable.getCurrentMax());
		}
		if (order.maxRowsSizes.get(0) == 5) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.4 * currentTable.getCurrentMax());
		}
		// Jak są 3 takie same karty
		if ((order.maxRowsSizes.get(0) == 4 || order.orderByCourtQuantity.get(0) == 3) && generator.nextDouble() < 0.15) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.3 * currentTable.getCurrentMax());
		}
		// Jak jest układ 2 pary Jak jest układ 1 para
		if ((order.orderByCourtQuantity.get(0) == 2 && order.orderByCourtQuantity.get(1) == 2 && generator.nextDouble() < 0.15)
				|| order.orderByCourtQuantity.get(0) == 2) {
			return currentTable.getCurrentMax() + (int) (generator.nextDouble() * 0.3 * currentTable.getCurrentMax());
		}
		// w przeciwnym razie zdaj sie na los ;)
		return botsCoins.nextInt(getOwnChips() + currentTable.getCurrentMax());
	}

	public StatusEnum makeMove() {
		Random botsMove = new Random();

		if (currentTable.getStatusPlayersInGame().equals(StatusEnum.BET) || currentTable.getStatusPlayersInGame().equals(StatusEnum.RAISE)
				|| currentTable.getStatusPlayersInGame().equals(StatusEnum.CALL)) {
			// moze wylosowac call(3), raise(5), all-i(4)
			return StatusEnum.values()[botsMove.nextInt(3) + 3];
		}
		if (currentTable.getStatusPlayersInGame().size() < 1) {
			return StatusEnum.values()[botsMove.nextInt(2) + 1];
		}
		if (currentTable.getStatusPlayersInGame().equals(StatusEnum.ALL_IN)) {
			return StatusEnum.values()[botsMove.nextInt(2) + 2];
		}
		return StatusEnum.values()[botsMove.nextInt(5) + 2];
	}
}

class MakeOrder {
	List<Card> orderByCourtQuality;
	List<Integer> orderByCourtQuantity;

	List<Card> orderBySuitQuality;
	List<Integer> orderBySuitQuantity;

	List<Card> maxRowsEndCard;
	List<Integer> maxRowsSizes;

	List<Card> inspectedCards;

	MakeOrder(List<Card> inspectedCards) {
		this.inspectedCards = inspectedCards;

		orderByCourtQuality = null;
		orderByCourtQuantity = null;

		orderBySuitQuality = null;
		orderBySuitQuantity = null;

		maxRowsEndCard = null;
		maxRowsSizes = null;

	}

	void doTheJob() {

		// Posortuj według ilości karty tej samej figury
		Sorter s = new Sorter();
		s.sort(inspectedCards);

		orderByCourtQuality = new ArrayList<Card>();
		orderByCourtQuantity = new ArrayList<Integer>();
		int i = 0;
		while (i < 5) {
			int j = 1;
			while (!(i == 4) && inspectedCards.get(i).getCourt().equals(inspectedCards.get(i + 1).getCourt())) {
				++i;
				++j;
			}

			if (orderByCourtQuality.size() == 0) {
				orderByCourtQuality.add(inspectedCards.get(i));
				orderByCourtQuantity.add(j);
			} else {
				int k = 0;
				while (k < orderByCourtQuantity.size() && j < orderByCourtQuantity.get(k)) {
					++k;
				}
				if (k < orderByCourtQuantity.size()) {
					orderByCourtQuality.add(k, inspectedCards.get(i));
					orderByCourtQuantity.add(k, j);
				} else {
					orderByCourtQuality.add(inspectedCards.get(i));
					orderByCourtQuantity.add(j);
				}

			}
			++i;
		}
		// Posortuj według ilości kart tego samego koloru

		List<Card> colors = new ArrayList<Card>();
		colors.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		colors.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		colors.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		colors.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));

		orderBySuitQuality = new ArrayList<Card>();
		orderBySuitQuantity = new ArrayList<Integer>();

		for (i = 0; i < colors.size(); ++i) {
			int counter = 0;
			for (int j = 0; j < 5; ++j) {
				if (inspectedCards.get(j).getSuit().equals(colors.get(i).getSuit()))
					++counter;
			}

			if (orderBySuitQuality.size() == 0) {
				orderBySuitQuality.add(colors.get(i));
				orderBySuitQuantity.add(counter);
			} else {
				int k = 0;
				while (k < orderBySuitQuantity.size() && counter < orderBySuitQuantity.get(k)) {
					++k;
				}
				if (k < orderBySuitQuantity.size()) {
					orderBySuitQuality.add(k, colors.get(i));
					orderBySuitQuantity.add(k, counter);
				} else {
					orderBySuitQuality.add(colors.get(i));
					orderBySuitQuantity.add(counter);
				}
			}
		}

		// Posortuj według najdłuższego znalezionego streeta :D
		maxRowsEndCard = new ArrayList<Card>();
		maxRowsSizes = new ArrayList<Integer>();
		i = 0;
		while (i < 5) {
			int j = 1;
			while ((i != 4) && (inspectedCards.get(i).getCourt().ordinal() + 1 == inspectedCards.get(i + 1).getCourt().ordinal())) {
				++i;
				++j;

			}
			if (maxRowsEndCard.size() == 0) {
				maxRowsEndCard.add(inspectedCards.get(i));
				maxRowsSizes.add(j);
			} else {
				int k = 0;
				while (k < maxRowsSizes.size() && j < maxRowsSizes.get(k)) {
					++k;
				}
				if (k < maxRowsSizes.size()) {
					maxRowsEndCard.add(k, inspectedCards.get(i));
					maxRowsSizes.add(k, j);
				} else {
					maxRowsEndCard.add(inspectedCards.get(i));
					maxRowsSizes.add(j);
				}

			}
			++i;
		}

	}
}
