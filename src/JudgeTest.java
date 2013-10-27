import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JudgeTest {

	private Table currentTable = new Table(3, 2);

	Player playerWithStraightFlush = playerWithStraightFlush();
	Player playerWithFourOfAKind = playerWithFourOfAKind();
	Player playerWithFullHouse = pleyerWithFullHouse();
	Player playerWithFlush = playerWithFlush();
	Player playerWithStraight = playerWithStraight();
	Player playerWithThreeOfAKind = playerWithThreeOfAKind();
	Player playerWithTwoPair = playerWithTwoPair();
	Player playerWithOnePair = playerWithOnePair();

	@Test
	public final void testSelectWinnersIfFirtFirstPlayerWithStraigthFlush() {

		List<Player> players = new ArrayList<>();

		players.add(playerWithStraightFlush);
		players.add(playerWithFullHouse);
		players.add(playerWithFourOfAKind);
		players.add(playerWithTwoPair);

		List<Integer> expectedIdexes = new ArrayList<>();
		// index 1 gracza
		expectedIdexes.add(0);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	// @Test
	// public final void testSortListOfPlayers() {
	//
	// List<Player> players = new ArrayList<>();
	//
	// players.add(playerWithFourOfAKind);
	// players.add(playerWithStraightFlush);
	// players.add(playerWithTwoPair);
	// players.add(playerWithFullHouse);
	//
	// List<Player> expectedOrder = new ArrayList<>();
	// expectedOrder.add(playerWithTwoPair);
	// expectedOrder.add(playerWithFullHouse);
	// expectedOrder.add(playerWithFourOfAKind);
	// expectedOrder.add(playerWithStraightFlush);
	//
	// assertEquals(Judge.sortListOfPlayers(players), expectedOrder);
	// }

	private Player playerWithOnePair() {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(onePair, currentTable);
	}

	private Player playerWithTwoPair() {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(twoPair, currentTable);
	}

	private Player playerWithThreeOfAKind() {
		List<Card> threeOfAKind = new ArrayList<>();
		threeOfAKind.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		threeOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(threeOfAKind, currentTable);
	}

	private Player playerWithStraight() {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straight, currentTable);
	}

	private Player playerWithFlush() {
		List<Card> flush = new ArrayList<>();
		flush.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(flush, currentTable);
	}

	private Player playerWithFourOfAKind() {
		List<Card> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(fourOfAKind, currentTable);
	}

	private Player playerWithStraightFlush() {
		List<Card> straightFlush = new ArrayList<>();
		straightFlush.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(straightFlush, currentTable);
	}

	private Player pleyerWithFullHouse() {
		List<Card> fullHouseHand = new ArrayList<>();
		fullHouseHand.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fullHouseHand.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		return new Human(fullHouseHand, currentTable);
	}

}
