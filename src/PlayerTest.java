import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		assertEquals(5, firstPlayer.ownCards.size());
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

		ArrayList<Card> cardsInOrder = new ArrayList<>();
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		cardsInOrder.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		cardsInOrder.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		cardsInOrder.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));

		assertEquals(cardsInOrder, checkSort.getOwnCards());

	}

	@Test
	public void checkIfPlayerHaveStraightFlush() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkStraightFlush = new Player(someCards);

		assertTrue(checkStraightFlush.checkIfStraightFlash());
	}

	@Test
	public void checkIfPlayerHaveFourOfAKing() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFourOfAKing = new Player(someCards);

		assertTrue(checkFourOfAKing.checkIfFourOfAKing());
	}

	@Test
	public void checkIfPlayerHaveFullHouse() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFullHouse = new Player(someCards);

		assertTrue(checkFullHouse.checkIfFullHouse());
	}

	@Test
	public void checkIfPlayerHaveFlush() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));

		Player checkFlush = new Player(someCards);

		assertTrue(checkFlush.checkIfFlush());
	}

	@Test
	public void checkIfPlayerHaveStrit() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkStrit = new Player(someCards);

		assertTrue(checkStrit.checkIfStrit());
	}

	@Test
	public void checkIfPlayerHaveThreeOfAKind() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkThreeOfAKind = new Player(someCards);

		assertTrue(checkThreeOfAKind.checkIfThreeOfAKind());
	}

	@Test
	public void checkIfPlayerHaveTwoPair() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkTwoPair = new Player(someCards);

		assertTrue(checkTwoPair.checkIfTwoPair());
	}


}
