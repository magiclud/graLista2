import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {

	Player firstPlayer;

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaWiecejNiz5Elementow() {
		Deck deckOfCards = new Deck();
		ArrayList<Card> someCards = new ArrayList<>(deckOfCards.getDeck());
		firstPlayer = new Player(someCards);

	}

	@Test
	public void checkStraightFlush() {

	}

}
