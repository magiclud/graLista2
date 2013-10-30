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
	Player playerWithFlush2 = playerWithFlush2();
	Player playerWithFlush3 = playerWithFlush3();
	Player playerWithFlush4 = playerWithFlush4();
	Player playerWithStraight = playerWithStraight();
	Player playerWithStraight2 = playerWithStraight2();
	Player playerWithStraight3 = playerWithStraight3();
	Player playerWithStraight4 = playerWithStraight4();
	Player playerWithThreeOfAKind = playerWithThreeOfAKind();
	Player playerWithThreeOfAKind2 = playerWithThreeOfAKind2();
	Player playerWithThreeOfAKind3 = playerWithThreeOfAKind3();
	Player playerWithTwoPair = playerWithTwoPair();
	Player playerWithTwoPair2 = playerWithTwoPair2();
	Player playerWithTwoPair3 = playerWithTwoPair3();
	Player playerWithTwoPair4 = playerWithTwoPair4();
	Player playerWithTwoPair5 = playerWithTwoPair5();
	Player playerWithOnePair = playerWithOnePair();
	Player playerWithOnePair2 = playerWithOnePair2();
	Player playerWithOnePair3 = playerWithOnePair3();
	Player playerWithOnePair4 = playerWithOnePair4();
	Player playerWithNothing = playerWithNothing();
	Player playerWithNothing1 = playerWithNothing2();
	Player playerWithNothing2 = playerWithNothing();
	Player playerWithNothing3 = playerWithNothing3();
	Player playerWithNothing4 = playerWithNothing4();
	Player palyerWithNothing5 = plyerWithNothing5();

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlush() {
		// dwoch graczy ma pokera - jeden wygrywa
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
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlushEndDraw() {// remis
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
		expectedIdexes.add(0);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoCardsTwoPlayersHaveFlush() {
		// dwoch graczy ma kolor - jeden wygrywa - z wyzsza karta
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPair);
		players.add(playerWithFlush);
		players.add(playerWithTwoPair2);
		players.add(playerWithFlush2);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfThreePlayersHaveFlushAndFourIdenticalCards() {
		// trzech graczy ma kolor - jeden wygrywa - ale nalezy sprawdzic cztery
		// karty
		List<Player> players = new ArrayList<>();
		players.add(playerWithFlush3);
		players.add(playerWithFlush);
		players.add(playerWithNothing3);
		players.add(playerWithFlush2);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveFlushAndFiveIdenticalCardsEndDraw() {
		// dwoch graczy ma kolor i piec kart o tych samych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithFlush4);
		players.add(playerWithFlush);
		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		expectedIdexes.add(1);
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
	public final void testSelectWinnersIfThreePlayersHaveStraight() {
		// trzech graczy ma Strit - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPair);
		players.add(playerWithStraight2);
		players.add(playerWithStraight);
		players.add(playerWithStraight3);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightEndDraw() {
		// dwoch graczy ma Strit i karty o jednakowych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPair);
		players.add(playerWithStraight4);
		players.add(playerWithTwoPair2);
		players.add(playerWithStraight3);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveThreeOfAKind() {
		// trzech graczy ma 'trojke' - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithThreeOfAKind2);
		players.add(playerWithThreeOfAKind);
		players.add(playerWithNothing1);
		players.add(playerWithThreeOfAKind3);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveTwoPair() {
		// trzech graczy maja uklad dwoch par - wygrywa ten o najsilniejszym
		// ukldzie
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPair3);
		players.add(playerWithNothing2);
		players.add(playerWithTwoPair2);
		players.add(playerWithTwoPair);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairAndIdenticalFirstPair() {
		// dwoch graczy maja 'dwojke' i jednakowa mocniejesza pare- wygrywa ten
		// o najsilniejszej drugiej parze
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithNothing2);
		players.add(playerWithTwoPair2);
		players.add(playerWithTwoPair);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairIdenticalTwoPairs() {
		// dwoch graczy maja 'dwojke' i dwie jednakowe pary- wygrywa ten
		// o najsilniejszej piatej karcie
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithNothing3);
		players.add(playerWithTwoPair3);
		players.add(playerWithTwoPair4);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairAndIdenticalCardsEndDraw() {
		// dwoch graczy maja 'dwojke' i karty jednakowych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing1);
		players.add(playerWithNothing3);
		players.add(playerWithTwoPair5);
		players.add(playerWithTwoPair4);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		expectedIdexes.add(3);
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
	public final void testSelectWinnersIfTwoPlayersHaveOnePair() {
		// dwoch graczy ma 'One Pair' - wygrywa gracz z najsilniejsza para
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		players.add(playerWithOnePair2);
		players.add(playerWithOnePair);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveOneIdenticalPair() {
		// dwoch graczy ma identyczna 'One Pair' - wygrywa gracz z najsilniejsza
		// kolejna karta - w tym przypadku nalezy sprawdzic trzecia kolejna
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		players.add(playerWithOnePair4);
		players.add(playerWithOnePair3);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfPlayerHaveOnlyHighCard() {
		// wygrywa jeden gracz
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3);
		players.add(playerWithNothing2);
		players.add(playerWithNothing1);
		players.add(playerWithNothing4);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayerHaveOnlyHighCardAndCardsAreIdenticalEndDraw() {
		// dwoch graczy ma identyczne karty bez ukladu - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing);
		players.add(playerWithNothing1);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		expectedIdexes.add(1);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayerHaveOnlyHighCardAndSomeAreIdentical() {
		// dwoch graczy maja podobne karty- jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(palyerWithNothing5);
		players.add(playerWithNothing4);
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
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

	private Player playerWithNothing() {
		List<Card> nothing2 = new ArrayList<>();
		nothing2.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		nothing2.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));
		nothing2.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		return new Human(nothing2, currentTable);
	}

	private Player playerWithNothing2() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
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

	private Player playerWithNothing4() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.THREE, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.JACK, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		return new Human(nothing1, currentTable);
	}
	private Player plyerWithNothing5() {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.JACK, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
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
	private Player playerWithOnePair2() {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.FOUR, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		return new Human(onePair, currentTable);
	}
	private Player playerWithOnePair3() {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.CLUB));
		return new Human(onePair, currentTable);
	}
	private Player playerWithOnePair4() {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
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
	private Player playerWithTwoPair2() {
		List<Card> twoPair2 = new ArrayList<>();
		twoPair2.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		twoPair2.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		twoPair2.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		twoPair2.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		twoPair2.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		return new Human(twoPair2, currentTable);
	}
	private Player playerWithTwoPair3() {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		return new Human(twoPair, currentTable);
	}
	private Player playerWithTwoPair4() {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.QUEEN, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		return new Human(twoPair, currentTable);
	}
	private Player playerWithTwoPair5() {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
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
	private Player playerWithThreeOfAKind2() {
		List<Card> threeOfAKind2 = new ArrayList<>();
		threeOfAKind2.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		threeOfAKind2.add(new Card(CourtEnum.EIGHT, SuitEnum.HEART));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		return new Human(threeOfAKind2, currentTable);
	}
	private Player playerWithThreeOfAKind3() {
		List<Card> threeOfAKind3 = new ArrayList<>();
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.HEART));
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		threeOfAKind3.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		threeOfAKind3.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		return new Human(threeOfAKind3, currentTable);
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
	private Player playerWithStraight2() {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		return new Human(straight, currentTable);
	}
	private Player playerWithStraight3() {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straight, currentTable);
	}
	private Player playerWithStraight4() {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.EIGHT, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
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
	private Player playerWithFlush2() {
		List<Card> flush2 = new ArrayList<>();
		flush2.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.JACK, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.TEN, SuitEnum.SPADE));
		return new Human(flush2, currentTable);
	}
	private Player playerWithFlush3() {
		List<Card> flush3 = new ArrayList<>();
		flush3.add(new Card(CourtEnum.TEN, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.JACK, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		return new Human(flush3, currentTable);
	}
	private Player playerWithFlush4() {
		List<Card> flush4 = new ArrayList<>();
		flush4.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		return new Human(flush4, currentTable);
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
	private Player pleyerWithFullHouse2() {
		List<Card> fullHouseHand = new ArrayList<>();
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));
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
