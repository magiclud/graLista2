import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PlayerTest {

	Player firstPlayer;

	@Test(expected = IllegalStateException.class)
	public void testPoprawnosciWyjatkuGdyPrzekazanaListaMaWiecejNiz5Elementow() {
		Deck deckOfCards = new Deck();
		List<Card> someCards = new ArrayList<>(deckOfCards.getDeck());
		firstPlayer = new Player(someCards);

	}

	@Test
	public void checkStraightFlush() {

	}

}
