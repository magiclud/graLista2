import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class CheckerTest {

	// TODO brakuje testow
	List<Card> someCards;
	Table someTable;
	Player firstPlayer;

	// Uwaga {metody check... i inne operuja na kartach playera ktore sa
	// posortowane w konstruktorze, dlatego ponizsze testy sprawdzaja poprawnosc
	// metod dla kart otrzymanych od playera}
	@Test
	public void checkIfPlayerHaveTwoPair() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.TWO_PAIR));
	}

	@Test
	public void checkIfPlayerHaveStraightFlush() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.STRAIGHT_FLUSH));
	}

	@Test
	public void checkIfPlayerHaveFourOfAKingFromBeggining() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.FOUR_OF_A_KIND));
	}

	@Test
	public void checkIfPlayerHaveFourOfAKing() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.FOUR_OF_A_KIND));
	}

	@Test
	public void checkIfPlayerHaveFullHouse() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.FULL_HOUSE));
	}

	@Test
	public void checkIfPlayerHaveFullHouseSecondOption() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.FULL_HOUSE));
	}

	@Test
	public void checkIfPlayerHaveFlush() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SEVEN, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.TEN, SuitEnum.CLUB));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.FLUSH));
	}

	@Test
	public void checkIfPlayerHaveStrit() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.STRAIGHT));
	}

	@Test
	public void checkIfPlayerHaveStritWithAce() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.THREE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.STRAIGHT));
	}

	@Test
	public void checkIfPlayerHaveThreeOfAKind() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		firstPlayer = new Human(someCards, someTable, 2);

		assertTrue(Checker.checkScore(firstPlayer.getOwnCards()).equals(SequenceEnum.TREE_OF_A_KIND));
	}




}
