import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class CardTest {

	@Test
	public void testAsDzwonekJestWiekszaOdKrolaPik() {
		Card asDzwonek = new Card(CourtEnum.AS, SuitEnum.DIAMOND);
		Card krolTrefl = new Card(CourtEnum.KROL, SuitEnum.CLUB);

		assertTrue(asDzwonek.isWieksza(krolTrefl));
	}

	@Test
	public void testAsDwojkaJestMniejszaOdCzworki() {
		Card dwojkaSerce = new Card(CourtEnum.DWOJKA, SuitEnum.HEART);
		Card czworkaTrefl = new Card(CourtEnum.CZWORKA, SuitEnum.CLUB);

		assertFalse(dwojkaSerce.isWieksza(czworkaTrefl));
	}

	@Test
	public void testPiatkaJestWiekszaOdPiatki() {
		Card piatkaSerce = new Card(CourtEnum.PIATKA, SuitEnum.HEART);
		Card piatkaPik = new Card(CourtEnum.PIATKA, SuitEnum.SPADE);

		assertTrue(piatkaSerce.isWieksza(piatkaPik));
	}


}
