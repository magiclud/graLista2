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

	// @Test
	// public void testPoprawnosciWybraniaNajwyzszejKartyZFlushaOrStraight() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromFlushOrStraight().equals(new
	// Card(CourtEnum.SIX, SuitEnum.CLUB)));
	// }
	//
	// @Test
	// public void testPoprawnosciWybranaiNajwyzszejKartyZCzworki() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromFourOfAKind().equals(new
	// Card(CourtEnum.SIX, SuitEnum.HEART)));
	// }
	//
	// @Test
	// public void testPoprawnosciWybraniaNajwyzszejKartyZCzworkiFromBeggining()
	// {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromFourOfAKind().equals(new
	// Card(CourtEnum.TWO, SuitEnum.HEART)));
	// }
	//
	// @Test
	// public void checkPickHighestFromThree() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromThree().equals(new
	// Card(CourtEnum.FIVE, SuitEnum.HEART)));
	// }
	//
	// @Test
	// public void checkPickHighestFromTwo() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromTwoPair().equals(new
	// Card(CourtEnum.SIX, SuitEnum.DIAMOND)));
	// }
	//
	// @Test
	// public void checkPickSecondHighestFromTwo() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.slectSecondHighestFromTwoPair().equals(new
	// Card(CourtEnum.SIX, SuitEnum.DIAMOND)));
	// }
	//
	// @Test
	// public void checkPickHighestFromOne() {
	// someCards = new ArrayList<>();
	// someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
	// someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
	// someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
	// someCards.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
	//
	// firstPlayer = new Human(someCards, someTable);
	//
	// assertTrue(firstPlayer.selectHighestFromOnePair().equals(new
	// Card(CourtEnum.FIVE, SuitEnum.HEART)));
	// }

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
