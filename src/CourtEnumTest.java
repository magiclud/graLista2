import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CourtEnumTest {

	@Test
	public void sprawdzanieKolejnosciWszystkichFigurWEnumie() {
		CourtEnum array[] = { CourtEnum.DWOJKA, CourtEnum.TROJKA, CourtEnum.CZWORKA, CourtEnum.PIATKA, CourtEnum.SZOSTKA,
				CourtEnum.SIODEMKA, CourtEnum.OSEMKA, CourtEnum.DZIEWIATKA, CourtEnum.DZIESIATKA, CourtEnum.JOPEK, CourtEnum.DAMA,
				CourtEnum.KROL, CourtEnum.AS };
		assertArrayEquals(CourtEnum.values(), array);
	}

	@Test
	public void testujZeAsJestWiekszyOdKrola() {
		assertTrue(CourtEnum.AS.isBigger(CourtEnum.KROL));
	}

	@Test
	public void testujZeDziesiatkaJestMniejszaOdKrola() {
		assertFalse(CourtEnum.DZIESIATKA.isBigger(CourtEnum.KROL));
	}

	@Test
	public void testujZeJopekJestWiekszyOdJopka() {
		assertFalse(CourtEnum.JOPEK.isBigger(CourtEnum.JOPEK));
	}

}
