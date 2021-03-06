package model;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SelectorTest {

	List<Card> someCards;
	Table someTable;
	Player firstPlayer;

	@Test
	public void testPoprawnosciWybraniaNajwyzszejKartyZFlushaOrStraight() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromFlushOrStraight(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.SIX, SuitEnum.CLUB)));
	}

	@Test
	public void testPoprawnosciWybranaiNajwyzszejKartyZCzworki() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromFourOfAKind(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.SIX, SuitEnum.HEART)));
	}

	@Test
	public void testPoprawnosciWybraniaNajwyzszejKartyZCzworkiFromBeggining() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromFourOfAKind(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.TWO, SuitEnum.HEART)));
	}

	@Test
	public void testPickHighestFromThree() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromThree(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.FIVE, SuitEnum.HEART)));
	}

	@Test
	public void testPickHighestFromTwo() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromTwoPair(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.SIX, SuitEnum.DIAMOND)));
	}

	@Test
	public void testPickSecondHighestFromTwo() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectSecondHighestFromTwoPair(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.FIVE, SuitEnum.HEART)));
	}

	@Test
	public void testPickHighestFromOne() throws ExceptionsInGame {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Selector.selectHighestFromOnePair(firstPlayer.getOwnCards()).equals(new Card(CourtEnum.FIVE, SuitEnum.HEART)));
	}

}
