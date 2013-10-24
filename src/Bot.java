import java.util.List;
import java.util.Random;

public class Bot extends Player {
	Random botCards = new Random();
	int howMany;
	int choosenCart;
	List<Integer> abandonedIndexes;

	public Bot(List<Card> givenCards, Table currentTable) {
		super(givenCards, currentTable);
	}

	List<Card> joinGame() {
		Boolean isDone = false;
		do {
			isDone = selectCards();
		} while (!isDone);

		showCards();
		return ownCards;
	}

	Boolean selectCards() {

		howMany = botCards.nextInt(5);
		for (int i = 0; i <= howMany; i++) {
			// losuje imdeksy kart do usuniecia
			abandonedIndexes.add(botCards.nextInt(5));
		}
		requestCards(abandonedIndexes);
		return true;
	}

	// ta sama metoda w humanie
	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + " " + ownCards.get(i) + " ");
		}
	}

}
