import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PlayerTest {

	Player firstPlayer;
	List<Card> someCards;
	Table someTable;

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

	@Test
	public void testSpawdzaCzyKartySaPosortowane() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));

		Player checkSort = new Human(someCards, someTable);

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

		Player checkStraightFlush = new Human(someCards, someTable);

		assertTrue(checkStraightFlush.checkScore().equals(SequenceEnum.STRAIGHT_TFLUSH));
	}

	@Test
	public void checkIfPlayerHaveFourOfAKingFromBeggining() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFourOfAKing = new Human(someCards, someTable);

		assertTrue(checkFourOfAKing.checkScore().equals(SequenceEnum.FOUR_OF_A_KIND));
	}

	@Test
	public void checkIfPlayerHaveFourOfAKing() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFourOfAKing = new Human(someCards, someTable);

		assertTrue(checkFourOfAKing.checkScore().equals(SequenceEnum.FOUR_OF_A_KIND));
	}

	@Test
	public void checkIfPlayerHaveFullHouse() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFullHouse = new Human(someCards, someTable);

		assertTrue(checkFullHouse.checkScore().equals(SequenceEnum.FULL_HOUSE));
	}

	@Test
	public void checkIfPlayerHaveFullHouseSecondOption() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		Player checkFullHouse = new Human(someCards, someTable);

		assertTrue(checkFullHouse.checkScore().equals(SequenceEnum.FULL_HOUSE));
	}

	@Test
	public void checkIfPlayerHaveFlush() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));

		Player checkFlush = new Human(someCards, someTable);

		assertTrue(checkFlush.checkScore().equals(SequenceEnum.FLUSH));
	}

	@Test
	public void checkIfPlayerHaveStrit() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkStrit = new Human(someCards, someTable);

		assertTrue(checkStrit.checkScore().equals(SequenceEnum.STRAIGHT));
	}

	@Test
	public void checkIfPlayerHaveStritWithAce() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));

		Player checkStrit = new Human(someCards, someTable);

		assertTrue(checkStrit.checkScore().equals(SequenceEnum.STRAIGHT));
	}

	@Test
	public void checkIfPlayerHaveThreeOfAKind() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkThreeOfAKind = new Human(someCards, someTable);

		assertTrue(checkThreeOfAKind.checkScore().equals(SequenceEnum.TREE_OF_A_KIND));
	}

	@Test
	public void checkIfPlayerHaveTwoPair() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Player checkTwoPair = new Human(someCards, someTable);

		assertTrue(checkTwoPair.checkScore().equals(SequenceEnum.TWO_PAIR));
	}


}
