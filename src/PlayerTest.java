import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {

	Player firstPlayer;
	ArrayList<Card> someCards;

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaWiecejNiz5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Player(someCards);
	}

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaMniejNiz5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		firstPlayer = new Player(someCards);
	}

	@Test
	public void testPoprawnosciKonstruktoraGdyPrzekazanaListaMa5Elementow() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		firstPlayer = new Player(someCards);
	}

	@Test
	public void testSpawdzaCzyKartySaPosortowane() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));

		Player checkSort = new Player(someCards);
		// checkSort.sort(someCards);
		assertEquals(someCards, checkSort);

		/*
		 * ArrayList<Card> cardsInOrder = new ArrayList<>();
		 * cardsInOrder.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		 * cardsInOrder.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		 * cardsInOrder.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		 * cardsInOrder.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		 * cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		 * cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		 * 
		 * assertEquals(someCards, cardsInOrder);
		 */
	}

	@Test
	public void checkStraightFlush() {

	}

}
