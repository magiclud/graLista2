import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FiguraEnumTest {

	@Test
	public void sprawdzanieKolejnosciWszystkichFigurWEnumie() {
		FiguraEnum tablica[] = { FiguraEnum.DWOJKA, FiguraEnum.TROJKA, FiguraEnum.CZWORKA, FiguraEnum.PIATKA, FiguraEnum.SZOSTKA,
				FiguraEnum.SIODEMKA, FiguraEnum.OSEMKA, FiguraEnum.DZIEWIATKA, FiguraEnum.DZIESIATKA, FiguraEnum.JOPEK, FiguraEnum.DAMA,
				FiguraEnum.KROL, FiguraEnum.AS };
		assertArrayEquals(FiguraEnum.values(), tablica);
	}

	@Test
	public void testujZeAsJestWiekszyOdKrola() {
		assertTrue(FiguraEnum.AS.isWieksza(FiguraEnum.KROL));
	}

	@Test
	public void testujZeDziesiatkaJestMniejszaOdKrola() {
		assertFalse(FiguraEnum.DZIESIATKA.isWieksza(FiguraEnum.KROL));
	}

	@Test
	public void testujZeJopekJestWiekszyOdJopka() {
		assertFalse(FiguraEnum.JOPEK.isWieksza(FiguraEnum.JOPEK));
	}

}
