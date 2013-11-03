import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JudgeTest {

	private Table currentTable = new Table(3, 1);

	// //////////
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlushOneOnTheEnd() {
		// dwoch graczy ma pokera - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithStraightFlushStartingWith2Club(2));
		players.add(playerWithNothing2(3));
		players.add(playerWithNothing3(4));
		players.add(playerWithStraightFlushStartingWith10(5));
		List<Integer> expectedIdexes = new ArrayList<>();
		// expectedIdexes.add(1);
		expectedIdexes.add(4);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveStraightFlushEndDraw() {// remis
		// dwoch graczy ma pokera - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithStraightFlushStartingWith2Club(2));
		players.add(playerWithStarightFlushStartingWith2Spade(3));
		players.add(playerWithStraightFlushStartingWith2Heart(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		expectedIdexes.add(2);
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfFourPlayersHaveStraightFlushEndDraw() {// remis
		// dwoch graczy ma pokera - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithStraightFlushStartingWith2Club(1));
		players.add(playerWithStarightFlushStartingWith2Spade(2));
		players.add(playerWithStraightFlushStartingWith2Diamond(3));
		players.add(playerWithStraightFlushStartingWith2Heart(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		expectedIdexes.add(1);
		expectedIdexes.add(2);
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlush() {
		// dwoch graczy ma pokera - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithStraightFlushStartingWith2Club(2));
		players.add(playerWithStraightFlushStartingWith10(3));
		players.add(playerWithOnePairWith5(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		// expectedIdexes.add(1);
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightFlushEndDraw() {// remis
		// dwoch graczy ma pokera - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing2(1));
		players.add(playerWithStraightFlushStartingWith2Club(2));
		players.add(playerWithStarightFlushStartingWith2Spade(3));
		players.add(playerWithOnePairWith5(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfFirtFirstPlayerWithStraigthFlush() {

		List<Player> players = new ArrayList<>();
		players.add(playerWithStraightFlushStartingWith2Club(1));
		players.add(playerWithFullHouseWith6i2(2));
		players.add(playerWithFourOfAKindWith6(3));
		players.add(playerWithTwoPairWith65i2(4));
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
		// dwoch graczy ma 'czworke' - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithOnePairWith5(2));
		players.add(playerWithFourOfAKindWith6(3));
		players.add(playerWithFourOfAKindWith8(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveFullHouse() {
		// dwoch graczy ma '3 i 2' - jeden wygrywa//TODO
		List<Player> players = new ArrayList<>();
		players.add(playerWithFullHouseWith9i10(1));
		players.add(playerWithFullHouseWith6i2(2));
		players.add(playerWithOnePairWith5(3));
		players.add(playerWithTwoPairWith65i2(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoCardsTwoPlayersHaveFlush() {
		// dwoch graczy ma kolor - jeden wygrywa - z wyzsza karta
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPairWith65i2(1));
		players.add(playerWithFlushWithClub(2));
		players.add(playerWithTwoPairWith26i5(3));
		players.add(playerWithFlushWithSpade(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveFlushAndFourIdenticalCards() {
		// trzech graczy ma kolor - jeden wygrywa - ale nalezy sprawdzic cztery
		// karty
		List<Player> players = new ArrayList<>();
		players.add(playerWithFlushWithDiamond(1));
		players.add(playerWithFlushWithClub(2));
		players.add(playerWithNothing3(3));
		players.add(playerWithFlushWithSpade(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}
	@Test
	public final void testSelectWinnersIfTwoPlayersHaveFlushAndFiveIdenticalCardsEndDraw() {
		// dwoch graczy ma kolor i piec kart o tych samych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithFlushWithHeart(1));
		players.add(playerWithFlushWithClub(2));
		players.add(playerWithNothing3(3));
		players.add(playerWithNothing(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		expectedIdexes.add(1);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThirdPlayerWithStraight() {

		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPairWith65i2(1));
		players.add(playerWithThreeOfAKindWith5(2));
		players.add(playerWithStraightStartWith2(3));
		players.add(playerWithOnePairWith5(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveStraight() {
		// trzech graczy ma Strit - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPairWith65i2(1));
		players.add(playerWithStraightStartWith3(2));
		players.add(playerWithStraightStartWith2(3));
		players.add(playerWithStraightStartWith4(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveStraightEndDraw() {
		// dwoch graczy ma Strit i karty o jednakowych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPairWith65i2(1));
		players.add(playerWithStraight2StartWith4(2));
		players.add(playerWithTwoPairWith26i5(3));
		players.add(playerWithStraightStartWith4(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveThreeOfAKind() {
		// trzech graczy ma 'trojke' - jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithThreeOfAKindWith6(1));
		players.add(playerWithThreeOfAKindWith5(2));
		players.add(playerWithNothing(3));
		players.add(playerWithThreeOfAKindWith7(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThreePlayersHaveTwoPair() {
		// trzech graczy maja uklad dwoch par - wygrywa ten o najsilniejszym
		// ukldzie
		List<Player> players = new ArrayList<>();
		players.add(playerWithTwoPairWithA7iK(1));
		players.add(playerWithNothing2(2));
		players.add(playerWithTwoPairWith26i5(3));
		players.add(playerWithTwoPairWith65i2(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	// TODO program zle wyznacza najwyzsza pare
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairAndIdenticalFirstPair() {
		// dwoch graczy maja 'dwojke' i jednakowa mocniejesza pare- wygrywa ten
		// o najsilniejszej drugiej parze
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithNothing2(2));
		players.add(playerWithTwoPairWith26i5(3));
		players.add(playerWithTwoPairWith65i2(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairIdenticalTwoPairs() {
		// dwoch graczy maja 'dwojke' i dwie jednakowe pary- wygrywa ten
		// o najsilniejszej piatej karcie
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithNothing3(2));
		players.add(playerWithTwoPairWithA7iK(3));
		players.add(playerWithTwoPair2WithA7iQ(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveTwoPairAndIdenticalCardsEndDraw() {
		// dwoch graczy maja 'dwojke' i karty jednakowych figurach - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing2(1));
		players.add(playerWithNothing3(2));
		players.add(playerWithTwoPairWithA7iQ(3));
		players.add(playerWithTwoPair2WithA7iQ(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfThirdPlayerWithOnePair() {
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3(1));
		players.add(playerWithNothing(2));
		players.add(playerWithNothing2(3));
		players.add(playerWithOnePairWith5(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayersHaveOnePair() {
		// dwoch graczy ma 'One Pair' - wygrywa gracz z najsilniejsza para
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3(1));
		players.add(playerWithNothing2(2));
		players.add(playerWithOnePair3WithQueen(3));
		players.add(playerWithOnePairWith5(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	// @Test//TODO remis?
	public final void testSelectWinnersIfTwoPlayersHaveOneIdenticalPair() {
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3(1));
		players.add(playerWithNothing2(2));
		players.add(playerWithOnePairWithQueen(3));
		players.add(playerWithOnePair2WithQueen(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(2);
		expectedIdexes.add(3);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfPlayerHaveOnlyHighCard() {
		// wygrywa jeden gracz
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing3(1));
		players.add(playerWithNothing2(2));
		players.add(playerWithNothing5(3));
		players.add(playerWithNothing4(4));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayerHaveOnlyHighCardAndCardsAreIdenticalEndDraw() {
		// dwoch graczy ma identyczne karty bez ukladu - remis
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing(1));
		players.add(playerWithNothing2(2));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(0);
		expectedIdexes.add(1);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	@Test
	public final void testSelectWinnersIfTwoPlayerHaveOnlyHighCardAndSomeAreIdentical() {
		// dwoch graczy maja podobne karty- jeden wygrywa
		List<Player> players = new ArrayList<>();
		players.add(playerWithNothing5(1));
		players.add(playerWithNothing4(2));
		List<Integer> expectedIdexes = new ArrayList<>();
		expectedIdexes.add(1);
		assertEquals(expectedIdexes, Judge.selectWinners(players));
	}

	// @Test
	// public final void testSortListOfPlayers() {
	//
	// List<Player> players = new ArrayList<>();
	//
	// players.add(playerWithFourOfAKind());
	// players.add(playerWithStraightFlush());
	// players.add(playerWithTwoPair());
	// players.add(playerWithFullHouse());
	//
	// List<Player> expectedOrder = new ArrayList<>();
	// expectedOrder.add(playerWithTwoPair());
	// expectedOrder.add(playerWithFullHouse());
	// expectedOrder.add(playerWithFourOfAKind());
	// expectedOrder.add(playerWithStraightFlush());
	//
	// assertEquals(Judge.sortListOfPlayers(players), expectedOrder);
	// }

	private Player playerWithNothing(int playerId) {
		List<Card> nothing2 = new ArrayList<>();
		nothing2.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		nothing2.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing2.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));
		nothing2.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		return new Human(nothing2, currentTable, playerId);
	}

	private Player playerWithNothing2(int playerId) {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		return new Human(nothing1, currentTable, playerId);
	}

	private Player playerWithNothing3(int playerId) {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
		return new Human(nothing1, currentTable, playerId);
	}

	private Player playerWithNothing4(int playerId) {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.THREE, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.JACK, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		nothing1.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		return new Human(nothing1, currentTable, playerId);
	}

	private Player playerWithNothing5(int playerId) {
		List<Card> nothing1 = new ArrayList<>();
		nothing1.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.FOUR, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.JACK, SuitEnum.SPADE));
		nothing1.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		nothing1.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		return new Human(nothing1, currentTable, playerId);
	}

	private Player playerWithOnePairWith5(int playerId) {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(onePair, currentTable, playerId);
	}

	private Player playerWithOnePair3WithQueen(int playerId) {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.FOUR, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		return new Human(onePair, currentTable, playerId);
	}

	private Player playerWithOnePair2WithQueen(int playerId) {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.CLUB));
		return new Human(onePair, currentTable, playerId);
	}

	private Player playerWithOnePairWithQueen(int playerId) {
		List<Card> onePair = new ArrayList<>();
		onePair.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		onePair.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		onePair.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		return new Human(onePair, currentTable, playerId);
	}

	private Player playerWithTwoPairWith65i2(int playerId) {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(twoPair, currentTable, playerId);
	}

	private Player playerWithTwoPairWith26i5(int playerId) {
		List<Card> twoPair2 = new ArrayList<>();
		twoPair2.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		twoPair2.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		twoPair2.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		twoPair2.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		twoPair2.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		return new Human(twoPair2, currentTable, playerId);
	}

	private Player playerWithTwoPairWithA7iK(int playerId) {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.KING, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		return new Human(twoPair, currentTable, playerId);
	}

	private Player playerWithTwoPair2WithA7iQ(int playerId) {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.QUEEN, SuitEnum.CLUB));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		return new Human(twoPair, currentTable, playerId);
	}

	private Player playerWithTwoPairWithA7iQ(int playerId) {
		List<Card> twoPair = new ArrayList<>();
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		twoPair.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		return new Human(twoPair, currentTable, playerId);
	}

	private Player playerWithThreeOfAKindWith5(int playerId) {
		List<Card> threeOfAKind = new ArrayList<>();
		threeOfAKind.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		threeOfAKind.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		threeOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(threeOfAKind, currentTable, playerId);
	}

	private Player playerWithThreeOfAKindWith6(int playerId) {
		List<Card> threeOfAKind2 = new ArrayList<>();
		threeOfAKind2.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		threeOfAKind2.add(new Card(CourtEnum.EIGHT, SuitEnum.HEART));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		threeOfAKind2.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		return new Human(threeOfAKind2, currentTable, playerId);
	}

	private Player playerWithThreeOfAKindWith7(int playerId) {
		List<Card> threeOfAKind3 = new ArrayList<>();
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.HEART));
		threeOfAKind3.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		threeOfAKind3.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		threeOfAKind3.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		return new Human(threeOfAKind3, currentTable, playerId);
	}

	private Player playerWithStraightStartWith2(int playerId) {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straight, currentTable, playerId);
	}

	private Player playerWithStraightStartWith3(int playerId) {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		return new Human(straight, currentTable, playerId);
	}

	private Player playerWithStraightStartWith4(int playerId) {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straight, currentTable, playerId);
	}

	private Player playerWithStraight2StartWith4(int playerId) {
		List<Card> straight = new ArrayList<>();
		straight.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		straight.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.EIGHT, SuitEnum.DIAMOND));
		straight.add(new Card(CourtEnum.SEVEN, SuitEnum.SPADE));
		straight.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(straight, currentTable, playerId);
	}

	private Player playerWithFlushWithClub(int playerId) {
		List<Card> flush = new ArrayList<>();
		flush.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		flush.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(flush, currentTable, 2);
	}

	private Player playerWithFlushWithSpade(int playerId) {
		List<Card> flush2 = new ArrayList<>();
		flush2.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.JACK, SuitEnum.SPADE));
		flush2.add(new Card(CourtEnum.TEN, SuitEnum.SPADE));
		return new Human(flush2, currentTable, playerId);
	}

	private Player playerWithFlushWithDiamond(int playerId) {
		List<Card> flush3 = new ArrayList<>();
		flush3.add(new Card(CourtEnum.TEN, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.JACK, SuitEnum.DIAMOND));
		flush3.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		return new Human(flush3, currentTable, playerId);
	}

	private Player playerWithFlushWithHeart(int playerId) {
		List<Card> flush4 = new ArrayList<>();
		flush4.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		flush4.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		return new Human(flush4, currentTable, playerId);
	}

	private Player playerWithFullHouseWith6i2(int playerId) {
		List<Card> fullHouseHand = new ArrayList<>();
		fullHouseHand.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fullHouseHand.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fullHouseHand.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(fullHouseHand, currentTable, playerId);
	}

	private Player playerWithFullHouseWith9i10(int playerId) {
		List<Card> fullHouseHand = new ArrayList<>();
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.CLUB));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.SPADE));
		fullHouseHand.add(new Card(CourtEnum.NINE, SuitEnum.DIAMOND));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		fullHouseHand.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));
		return new Human(fullHouseHand, currentTable, playerId);
	}

	private Player playerWithFourOfAKindWith6(int playerId) {
		List<Card> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fourOfAKind.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(fourOfAKind, currentTable, playerId);
	}

	private Player playerWithFourOfAKindWith8(int playerId) {
		List<Card> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.SPADE));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.DIAMOND));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.HEART));
		fourOfAKind.add(new Card(CourtEnum.EIGHT, SuitEnum.CLUB));
		return new Human(fourOfAKind, currentTable, playerId);
	}

	private Player playerWithStraightFlushStartingWith2Club(int playerId) {
		List<Card> straightFlush = new ArrayList<>();
		straightFlush.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		straightFlush.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		return new Human(straightFlush, currentTable, playerId);
	}

	private Player playerWithStraightFlushStartingWith10(int playerId) {
		List<Card> straightFlush = new ArrayList<>();
		straightFlush.add(new Card(CourtEnum.TEN, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.JACK, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.KING, SuitEnum.HEART));
		straightFlush.add(new Card(CourtEnum.ACE, SuitEnum.HEART));
		return new Human(straightFlush, currentTable,2);
	}

	private Player playerWithStarightFlushStartingWith2Spade(int playerId) {
		List<Card> straightFlush3 = new ArrayList<>();
		straightFlush3.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.THREE, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.FOUR, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.FIVE, SuitEnum.SPADE));
		straightFlush3.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		return new Human(straightFlush3, currentTable, playerId);
	}

	public Player playerWithStraightFlushStartingWith2Heart(int playerId) {
		List<Card> straightFlush4 = new ArrayList<>();
		straightFlush4.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		straightFlush4.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		straightFlush4.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		straightFlush4.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		straightFlush4.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		return new Human(straightFlush4, currentTable, playerId);
	}

	public Player playerWithStraightFlushStartingWith2Diamond(int playerId) {
		List<Card> straightFlush5 = new ArrayList<>();
		straightFlush5.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		straightFlush5.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		straightFlush5.add(new Card(CourtEnum.FOUR, SuitEnum.DIAMOND));
		straightFlush5.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		straightFlush5.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		return new Human(straightFlush5, currentTable, playerId);
	}
}
