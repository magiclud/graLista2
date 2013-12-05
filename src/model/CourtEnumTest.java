package model;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CourtEnumTest {

	@Test
	public void sprawdzanieKolejnosciWszystkichFigurWEnumie() {
		CourtEnum array[] = { CourtEnum.TWO, CourtEnum.THREE, CourtEnum.FOUR, CourtEnum.FIVE, CourtEnum.SIX, CourtEnum.SEVEN,
				CourtEnum.EIGHT, CourtEnum.NINE, CourtEnum.TEN, CourtEnum.JACK, CourtEnum.QUEEN, CourtEnum.KING, CourtEnum.ACE };
		assertArrayEquals(CourtEnum.values(), array);
	}

	@Test
	public void testujZeAsJestWiekszyOdKrola() {
		assertTrue(CourtEnum.ACE.isGreater(CourtEnum.KING));
	}

	@Test
	public void testujZeDziesiatkaJestMniejszaOdKrola() {
		assertFalse(CourtEnum.TEN.isGreater(CourtEnum.KING));
	}

	@Test
	public void testujZeJopekJestWiekszyOdJopka() {
		assertFalse(CourtEnum.JACK.isGreater(CourtEnum.JACK));
	}

}
