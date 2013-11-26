import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SorterTest {

	List<Card> someCards;
	Table someTable;

	@Test
	public void testSpawdzaCzyKartySaPosortowane() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));

		Player checkSort = new Human(someCards, someTable, 2);

		ArrayList<Card> cardsInOrder = new ArrayList<>();
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		cardsInOrder.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		cardsInOrder.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		cardsInOrder.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));

		assertEquals(cardsInOrder, checkSort.ownCards);
	}
}
