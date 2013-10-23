import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PlayerTest {

	Player firstPlayer;
	List<Card> someCards;
	Table someTable;

/*	@Test
=======
	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyGraczWymieniaWiecejNiz4Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Human(someCards, someTable);

		List<Card> selectedCards = new ArrayList<Card>();
		selectedCards.add(someCards.get(1));
		selectedCards.add(someCards.get(2));
		selectedCards.add(someCards.get(4));

		firstPlayer.changeCards(selectedCards);
	}
	@Test
>>>>>>> .r25
	public void testPoprawnosciWYmianyKart() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Human(someCards, someTable);

		List<Card> selectedCards = new ArrayList<Card>();
		selectedCards.add(someCards.get(1));
		selectedCards.add(someCards.get(2));
		selectedCards.add(someCards.get(4));

		firstPlayer.requestCards(selectedCards);

		assertEquals(5, firstPlayer.ownCards.size());
	}*/

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaWiecejNiz5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Human(someCards, someTable);
	}

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaMniejNiz5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		firstPlayer = new Human(someCards, someTable);
	}

	@Test
	public void testPoprawnosciKonstruktoraGdyPrzekazanaListaMa5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Human(someCards, someTable);
		assertEquals(5, firstPlayer.ownCards.size());
	}

}
