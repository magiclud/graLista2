package model;
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
	public void testSprawdzaCzyPoDrugimSortowaniuOtrzymamInnyUkladKart() {
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

}
