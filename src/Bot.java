import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
	Random botCards = new Random();

	public Bot(List<Card> givenCards, Table currentTable) {
		super(givenCards, currentTable);
	}

	List<Card> joinGame() {
		wybierzLosowo();
		return ownCards;
	}

	// Losowanie różnych liczb ze zbioru 0. .5
	List<Integer> randomDifferentNumbers(int howMany) {
		if (howMany > 5 || howMany < 0)
			throw new IllegalStateException(
					"Niepoprawna ilość kart jakie chcesz wylosować !");
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

	void wybierzLosowo() {
		List<Integer> abandonedIndexes = new ArrayList<Integer>();
		int howMany;

		howMany = botCards.nextInt(5);

		abandonedIndexes = randomDifferentNumbers(howMany);
		requestCards(abandonedIndexes);
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
			while (!(i == 4)
					&& inspectedCards.get(i).getCourt()
							.equals(inspectedCards.get(i + 1).getCourt())) {
				++i;
				++j;
			}

			if (orderByCourtQuality.size() == 0) {
				orderByCourtQuality.add(inspectedCards.get(i));
				orderByCourtQuantity.add(j);
			} else {
				int k = 0;
				while (k < orderByCourtQuantity.size()
						&& j < orderByCourtQuantity.get(k)) {
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

		for(i = 0; i < colors.size(); ++i) {
			int counter = 0;
			for(int j = 0; j < 5; ++j ) {
				if(inspectedCards.get(j).getSuit().equals(colors.get(i).getSuit()))
				++counter;
			}

			if(orderBySuitQuality.size() == 0 ) {
				orderBySuitQuality.add(colors.get(i));
				orderBySuitQuantity.add(counter);
			}
			else {
				int k = 0;
				while (k < orderBySuitQuantity.size()
						&& counter < orderBySuitQuantity.get(k)) {
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
					while ((i != 4)
							&& (inspectedCards.get(i).getCourt().ordinal() + 1
									 == inspectedCards.get(i + 1).getCourt().ordinal())) {
						++i;
						++j;
						
					}
					if (maxRowsEndCard.size() == 0) {
						maxRowsEndCard.add(inspectedCards.get(i));
						maxRowsSizes.add(j);
					} else {
						int k = 0;
						while (k < orderByCourtQuantity.size()
								&& j < maxRowsSizes.get(k)) {
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
