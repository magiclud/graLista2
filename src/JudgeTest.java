import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class JudgeTest {
	List<Card> firstCards;
	List<Card> secondCards;
	List<Card> thirdCards;
	List<Card> fourthCards;

	// List<Player> players;
	List<List<Card>> playersCards;
	Table someTable;

	@Test
	public final void testSelectWinnersIfFirtPlayerWin() {
		firstCards = new ArrayList<>();
		firstCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		firstCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		firstCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		firstCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		firstCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		secondCards = new ArrayList<>();
		secondCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		secondCards.add(new Card(CourtEnum.THREE, SuitEnum.CLUB));
		secondCards.add(new Card(CourtEnum.FOUR, SuitEnum.CLUB));
		secondCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		secondCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		thirdCards = new ArrayList<>();
		thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		thirdCards.add(new Card(CourtEnum.TWO, SuitEnum.HEART));
		thirdCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		fourthCards = new ArrayList<>();
		fourthCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		fourthCards.add(new Card(CourtEnum.TWO, SuitEnum.SPADE));
		fourthCards.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		fourthCards.add(new Card(CourtEnum.SIX, SuitEnum.HEART));
		fourthCards.add(new Card(CourtEnum.SIX, SuitEnum.CLUB));

		// players.add(new Human(firstCards, someTable));
		// players.add(new Human(secondCards, someTable));
		// players.add(new Human(thirdCards, someTable));
		// players.add(new Human(fourthCards, someTable));

		playersCards.add(firstCards);
		playersCards.add(secondCards);
		playersCards.add(thirdCards);
		playersCards.add(fourthCards);
		Judge firstWin = new Judge(playersCards);

		assertNotNull(firstWin.selectWinners().get(0));
	}

}
