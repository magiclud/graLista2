import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JudgeTest {

	private Table currentTable = new Table(3, 1);

	Player playerWithStraightFlush = playerWithStraightFlush();
	Player playerWithStraightFlush2 = playerWithStraightFlush2();
	Player playerWithStarightFlush3 = playerWithStarightFlush3();
	Player playerWithFourOfAKind = playerWithFourOfAKind();
	Player playerWithFourOfAKind2 = playerWithFourOfAKind2();
	Player playerWithFullHouse = pleyerWithFullHouse();
	Player playerWithFullHouse2 = pleyerWithFullHouse2();
	Player playerWithFlush = playerWithFlush();
	Player playerWithStraight = playerWithStraight();
	Player playerWithThreeOfAKind = playerWithThreeOfAKind();
	Player playerWithTwoPair = playerWithTwoPair();
	Player playerWithOnePair = playerWithOnePair();
	Player playerWithNothing1 = plyerWithNothing1();
	Player playerWithNothing2 = playerWithNothing();
	Player playerWithNothing3 = playerWithNothing3();
	Player playerWithNothing4 = playerWithNothing4();

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlush() {
		// dwoch graczy ma pokera - jeden wygrywa//TODO
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithStraightFlush);
		players.add(playerWithStraightFlush2);
		players.add(playerWithOnePair);

		List<Integer> expectedIdexes = new ArrayList<>();
		// expectedIdexes.add(1);
		expectedIdexes.add(2);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlushEndDraw() {
		// dwoch graczy ma pokera - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithStraightFlush);
		players.add(playerWithStarightFlush3);
		players.add(playerWithOnePair);

		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		expectedIdexes.add(2);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfFirtFirstPlayerWithStraigthFlush() {

		List<Player> players = new ArrayList<>();

		players.add(playerWithStraightFlush);
		players.add(playerWithFullHouse);
		players.add(playerWithFourOfAKind);
		players.add(playerWithTwoPair);

		// players.add(playerWithTwoPair);
		// players.add(playerWithFourOfAKind);
		// players.add(playerWithFullHouse);
		// players.add(playerWithStraightFlush);
		List<Integer> expectedIdexes = new ArrayList<>();
		// index 1 gracza
		expectedIdexes.add(0);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveFourOfAKind() {
		// dwoch graczy ma 'czworke' - jeden wygrywa//TODO
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithOnePair);
		players.add(playerWithFourOfAKind);
		players.add(playerWithFourOfAKind2);

		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		expectedIdexes.add(3);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveFullHouse() {
		// dwoch graczy ma '3 i 2' - jeden wygrywa//TODO
		List<Player> players = new ArrayList<>();
		players.add(playerWithFullHouse2);
		players.add(playerWithFullHouse);
		players.add(playerWithOnePair);
		players.add(playerWithTwoPair);

		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		expectedIdexes.add(3);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoCardsTwoPlayersHaveFlush() {

		List<Player> players = new ArrayList<>();

		/***** drugi gracz z para ***/
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		twoPair.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		Player twoPair2 = new Human(twoPair, currentTable);
		/**** drugi gracz z flush ******/
		List<Card> flush = new ArrayList<>();
		flush.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));
		flush.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		flush.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		flush.add(new Card(CourtEnum.JACK, SuitEnum.SPADE));
		flush.add(new Card(CourtEnum.TEN, SuitEnum.SPADE));
		Player flush2 = new Human(flush, currentTable);

		players.add(playerWithTwoPair);
		players.add(playerWithFlush);
		players.add(twoPair2);
		players.add(flush2);

		List<Integer> expectedIdexes = new ArrayList<>();
		// index 1 gracza
		expectedIdexes.add(1);
		expectedIdexes.add(3);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThirdPlayerWithStraight() {

		List<Player> players = new ArrayList<>();

		players.add(playerWithTwoPair);
		players.add(playerWithThreeOfAKind);
		players.add(playerWithStraight);
		players.add(playerWithOnePair);
		List<Integer> expectedIdexes = new ArrayList<>();

		expectedIdexes.add(2);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThirdPlayerWithOnePair() {

		List<Player> players = new ArrayList<>();

		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		players.add(playerWithNothing1);
		players.add(playerWithOnePair);
		List<Integer> expectedIdexes = new ArrayList<>();

		expectedIdexes.add(3);

		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfPlayerHaveOnlyHighCard() {
		List<Player> players = new ArrayList<>();

		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		players.add(playerWithNothing1);
		players.add(playerWithNothing4);

		List<Integer> expectedIdexes = new ArrayList<>();

		expectedIdexes.add(0);
		expectedIdexes.add(1);
		expectedIdexes.add(2);
		expectedIdexes.add(3);

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

	private Player playerWithNothing4() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.THREE, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.JACK, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		return new Human(nothing1, currentTable);
	}

	private Player playerWithNothing3() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
		return new Human(nothing1, currentTable);
	}

	private Player playerWithNothing() {
		List<Card> nothing2 = new ArrayList<>();
		nothing2.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		nothing2.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));
		nothing2.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		return new Human(nothing2, currentTable);
	}

	private Player plyerWithNothing1() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		return new Human(nothing1, currentTable);
	}

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
		flush.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(flush, currentTable);
	}

	private Player pleyerWithFullHouse2() {
		List<Card> fullHouseHand = new ArrayList<>();
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));

		return new Human(fullHouseHand, currentTable);
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

	private Player playerWithFourOfAKind() {
		List<Card> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(fourOfAKind, currentTable);
	}
	private Player playerWithFourOfAKind2() {
		List<Card> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.DIAMOND));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.HEART));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
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

	private Player playerWithStraightFlush2() {
		List<Card> straightFlush = new ArrayList<>();
		straightFlush.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.JACK, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.KING, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		return new Human(straightFlush, currentTable);
	}

	private Player playerWithStarightFlush3() {
		List<Card> straightFlush3 = new ArrayList<>();
		straightFlush3.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.THREE, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.FOUR, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.FIVE, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straightFlush3, currentTable);
	}
}
