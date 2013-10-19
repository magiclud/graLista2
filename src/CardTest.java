import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class CardTest {

	@Test
	public void testAsDzwonekJestWiekszaOdKrolaPik() {
		Card asDzwonek = new Card(FiguraEnum.AS, KolorEnum.DIAMOND);
		Card krolTrefl = new Card(FiguraEnum.KROL, KolorEnum.CLUB);

		assertTrue(asDzwonek.isWieksza(krolTrefl));
	}

	@Test
	public void testAsDwojkaJestMniejszaOdCzworki() {
		Card dwojkaSerce = new Card(FiguraEnum.DWOJKA, KolorEnum.HEART);
		Card czworkaTrefl = new Card(FiguraEnum.CZWORKA, KolorEnum.CLUB);

		assertFalse(dwojkaSerce.isWieksza(czworkaTrefl));
	}

	@Test
	public void testPiatkaJestWiekszaOdPiatki() {
		Card piatkaSerce = new Card(FiguraEnum.PIATKA, KolorEnum.HEART);
		Card piatkaPik = new Card(FiguraEnum.PIATKA, KolorEnum.SPADE);

		assertTrue(piatkaSerce.isWieksza(piatkaPik));
	}


}
