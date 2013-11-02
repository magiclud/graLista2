import static org.junit.Assert.*;

import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BotTest {


	@Test
	public void testZwracamOdpowiedniaIloscKartPrzyLosowaniu () {
		Deck exampleDeck = new Deck();
		Table exampleTable = new Table(2,2); // <<<< tylko z powodów formalnych
		Bot exampleBot = new Bot(exampleDeck.giveCards(5), exampleTable);
		assertEquals(exampleBot.randomDifferentNumbers(4).size(), 4);
	}
	@Test
	public void testRozneLiczbyPrzyLosowaniu () {
		Deck exampleDeck = new Deck();
		Table exampleTable = new Table(2,2); // <<<< tylko z powodów formalnych
		Bot exampleBot = new Bot(exampleDeck.giveCards(5), exampleTable);
		List<Integer> exampleNumbers = exampleBot.randomDifferentNumbers(4);
		for(int i = 0; i < 4; ++i) {
			for(int j = i + 1; j < 4; ++j) {
				assertFalse(exampleNumbers.get(i)==exampleNumbers.get(j));
			}
		}
		
	}
	@Test(expected = Exception.class)
	public void testJuzWymienialKartyINieMozeWiecej () {
		Deck exampleDeck = new Deck();
		Table exampleTable = new Table(2,2); // <<<< tylko z powodów formalnych
		Bot exampleBot = new Bot(exampleDeck.giveCards(5), exampleTable);
		exampleBot.wybierzLosowo();
		exampleBot.wybierzLosowo();
	}
	

}