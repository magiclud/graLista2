import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class HumanTest {

	List someCards;
	Table someTable;
	@Test
	public void sprawdzamCzyHumanWybralKartyDoWymiany() {
		someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		Human cardsToExchange = new Human(someCards, someTable);

		cardsToExchange.setCardsToReturn(someCards);

		List selectedCards = new ArrayList();
		selectedCards.add(new Card(CourtEnum.FIVE, SuitEnum.HEART));
		selectedCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));
		selectedCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		assertEquals(cardsToExchange.getCardsToReturn(), selectedCards);
	}

}
