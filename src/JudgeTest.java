import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class JudgeTest {

	private Table currentTable = new Table(3, 2);

	@Test
	public final void testSelectWinnersIfFirtFirstPlayerWithStraigthFlush() {
		// firstCards = new ArrayList<>();
		// firstCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		// firstCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		// firstCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		// firstCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		// firstCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		//
		// secondCards = new ArrayList<>();
		// secondCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		// secondCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		// secondCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		// secondCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		// secondCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		//
		// thirdCards = new ArrayList<>();
		// thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		// thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		// thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		// thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		// thirdCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));
		//
		//
		//
		// // players.add(new Human(firstCards, someTable));
		// // players.add(new Human(secondCards, someTable));
		// // players.add(new Human(thirdCards, someTable));
		// // players.add(new Human(fourthCards, someTable));
		//
		// playersCards.add(firstCards);
		// playersCards.add(secondCards);
		// playersCards.add(thirdCards);

		List<Player> players = new ArrayList<>();

		Player playerWithFullHouse = pleyerWithFullHouse();
		Player playerWithPoker = null;
		// itd dorobi metody i doda
		players.add(playerWithPoker);
		players.add(playerWithFullHouse);

		List<Integer> expectedIdexes = new ArrayList<>();
		// index 1 gracza
		expectedIdexes.add(0);

		assertEquals(Judge.selectWinners(players), expectedIdexes);
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
