import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
	Random botCards = new Random();
	int choosenCart;

	public Bot(List<Card> givenCards, Table currentTable) {
		super(givenCards, currentTable);
	}

	List<Card> joinGame() {
		wybierzLosowo();
		return ownCards;
	}
	// Losowanie różnych liczb ze zbioru 0. .5
	List<Integer> randomDifferentNumbers(int howMany) {
		if(howMany > 5 || howMany < 0)
			throw new IllegalStateException("Niepoprawna ilość kart jakie chcesz wylosować !");
		List<Integer> differentNumbers = new ArrayList<Integer>();
		int[] differentIndexes = new int[5];
		for(int i = 0; i < 5; ++i) {
			differentIndexes[i] = i;
		}
		for(int j = 0; j < howMany; ++j) {
			int temp = botCards.nextInt(5-j);
			differentNumbers.add(differentIndexes[temp]);
			
			int temp2 = differentIndexes[5-j-1];
			differentIndexes[5-j-1] = differentIndexes[temp];
			differentIndexes[temp] = temp2;
		}
		return differentNumbers;
	}

	void wybierzLosowo() {
		List<Integer> abandonedIndexes = new ArrayList<Integer>();
		int howMany;

		howMany = botCards.nextInt(5);
		
		abandonedIndexes = randomDifferentNumbers(howMany);
		requestCards(abandonedIndexes);
	}
/*
	void showCards() {
		for (int i = 0; i < ownCards.size(); ++i) {
			System.out.print("[" + i + "]" + " " + ownCards.get(i) + " ");
		}
	}
*/
}
