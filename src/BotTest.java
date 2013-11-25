import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class BotTest {


	@Test
	public void testZwracamOdpowiedniaIloscKartPrzyLosowaniu () {
		Deck exampleDeck = new Deck();
		Table exampleTable = new Table(2, 2); // <<<< tylko z powodów
													// formalnych
		Bot exampleBot = new Bot(exampleDeck.giveCards(5), exampleTable, 2);
		assertEquals(exampleBot.randomDifferentNumbers(4).size(), 4);
	}
	@Test
	public void testRozneLiczbyPrzyLosowaniu () {
		Deck exampleDeck0 = new Deck();
		Table exampleTable0 = new Table(2, 2); // <<<< tylko z powodów
													// formalnych
		Bot exampleBot0 = new Bot(exampleDeck0.giveCards(5), exampleTable0, 2);
		List<Integer> exampleNumbers0 = exampleBot0.randomDifferentNumbers(4);
		for(int i = 0; i < 4; ++i) {
			for(int j = i + 1; j < 4; ++j) {
				assertFalse(exampleNumbers0.get(i)==exampleNumbers0.get(j));
			}
		}

	}
	@Test(expected = Exception.class)
	public void testJuzWymienialKartyINieMozeWiecej () {
		Deck exampleDeck = new Deck();
		Table exampleTable = new Table(2, 2); // <<<< tylko z powodów
													// formalnych
		Bot exampleBot = new Bot(exampleDeck.giveCards(5), exampleTable, 2);
		exampleBot.wybierzLosowo();
		exampleBot.wybierzLosowo();
	}
	@Test
	public void testOrderCourtDowolneRozstawienie() {
		List<Card> someCards = new ArrayList<Card>();
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.DIAMOND));
		someCards.add(new Card(CourtEnum.QUEEN, SuitEnum.HEART));
		someCards.add(new Card(CourtEnum.FIVE, SuitEnum.CLUB));

		someCards.add(new Card(CourtEnum.KING, SuitEnum.SPADE));
		someCards.add(new Card(CourtEnum.KING, SuitEnum.DIAMOND));

		MakeOrder example = new MakeOrder(someCards);
		example.doTheJob();
		assertTrue(example.orderByCourtQuality.get(0).getCourt().equals(CourtEnum.KING));
		assertTrue(example.orderBySuitQuality.get(0).getSuit().equals(SuitEnum.DIAMOND));
	}
	@Test
	public void testMyStrategy() {
		Deck exampleDeck1 = new Deck();
		Table exampleTable1 = new Table(2, 2);
		List<Card> cards12 = new ArrayList<Card>();
		cards12.add(new Card(CourtEnum.TWO, SuitEnum.DIAMOND));
		cards12.add(new Card(CourtEnum.THREE, SuitEnum.DIAMOND));
		cards12.add(new Card(CourtEnum.SIX, SuitEnum.DIAMOND));
		cards12.add(new Card(CourtEnum.TEN, SuitEnum.DIAMOND));
		cards12.add(new Card(CourtEnum.ACE, SuitEnum.CLUB));


		Bot exampleBot1 = new Bot(exampleDeck1.giveCards(5), exampleTable1, 2);
		exampleBot1.changeCardsUsingStrategy();

	}

	@Test
	public void testWymieńKarty() {
		System.out.println("___");
		Table exampleTable11 = new Table(2, 2);
		Player exampleBot11 = exampleTable11.players.get(3);
		exampleBot11.getOwnCards();
		List<Integer> abandonedIndexes = new ArrayList<Integer>();
		System.out.println("___");

		abandonedIndexes.add(3);
		abandonedIndexes.add(2);
		abandonedIndexes.add(1);
		exampleBot11.changeCards(abandonedIndexes);
		System.out.println("Sol");
		exampleBot11.getOwnCards();

	}


}
