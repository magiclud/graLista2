import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class DeckTest {

	@Test
	public void testSprawdzaCzyTaliaMa52Karty() {
		Deck deck = new Deck();
		assertEquals(52, deck.getSize());

	}
	
	@Test
	public void testSprawdzaCzyPoDrugimSortowaniuOtrzymamInnyUkladKart(){
		Deck secondDeck = new Deck();
		List<Card> someCards = new ArrayList<>(secondDeck.getDeck());
		Deck afterShuffle = new Deck();
		List<Card> anotherSomeCards = new ArrayList<>(afterShuffle.getDeck());
		assertFalse(someCards.equals(anotherSomeCards));
	}

	@Test
	public void testSprawdzaCzyWTaliiJestOkarteMniej() {
		Deck createDeck = new Deck();
		int beforeToDeal = createDeck.getSize();
		createDeck.getCard();
		int afterToDeal = createDeck.getSize();
		assertEquals(beforeToDeal, afterToDeal + 1);
	}

	@Test
	public void testSpawdzaCzyKartySaPosortowane() {
		ArrayList<Card> someCards = new ArrayList<>();
		someCards.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));
		someCards.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));

		Deck checkSort = new Deck();
		checkSort.sort(someCards);
		ArrayList<Card> cardsInOrder = new ArrayList<>();
		cardsInOrder.add(new Card(CourtEnum.ACE, SuitEnum.DIAMOND));
		cardsInOrder.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		cardsInOrder.add(new Card(CourtEnum.SIX, SuitEnum.SPADE));
		cardsInOrder.add(new Card(CourtEnum.FOUR, SuitEnum.HEART));
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		cardsInOrder.add(new Card(CourtEnum.TWO, SuitEnum.CLUB));

		assertEquals(someCards, cardsInOrder);

	}
}
