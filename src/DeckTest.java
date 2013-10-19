import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
		Deck afterShuffle = new Deck();
		assertFalse(secondDeck.equals(afterShuffle));
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
