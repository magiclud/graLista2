package model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CardTest {

	@Test
	public void testAsDzwonekJestWiekszaOdKrolaPik() {
		Card asDzwonek = new Card(CourtEnum.ACE, SuitEnum.DIAMOND);
		Card krolTrefl = new Card(CourtEnum.KING, SuitEnum.CLUB);

		assertTrue(asDzwonek.isGreater(krolTrefl));
	}

	@Test
	public void testAsDwojkaJestMniejszaOdCzworki() {
		Card dwojkaSerce = new Card(CourtEnum.TWO, SuitEnum.HEART);
		Card czworkaTrefl = new Card(CourtEnum.FOUR, SuitEnum.CLUB);

		assertFalse(dwojkaSerce.isGreater(czworkaTrefl));
	}

	@Test
	public void testPiatkaJestWiekszaOdPiatki() {
		Card piatkaSerce = new Card(CourtEnum.FIVE, SuitEnum.HEART);
		Card piatkaPik = new Card(CourtEnum.FIVE, SuitEnum.SPADE);

		assertTrue(piatkaSerce.isGreater(piatkaPik));
	}

}
