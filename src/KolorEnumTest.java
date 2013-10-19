import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class KolorEnumTest {


	@Test
	public void testujZeSerceJestWiekszeOdDzwonka() {
		assertTrue(KolorEnum.HEART.isWieksza(KolorEnum.DIAMOND));
	}

	@Test
	public void testujZeSerceJestWiekszeOdZoledzia() {
		assertTrue(KolorEnum.HEART.isWieksza(KolorEnum.CLUB));
	}

	@Test
	public void testujZeSerceJestWiekszeOdWina() {
		assertTrue(KolorEnum.HEART.isWieksza(KolorEnum.SPADE));
	}

	@Test
	public void testujZeSerceNieJestWiekszeOdSerca() {
		assertFalse(KolorEnum.HEART.isWieksza(KolorEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestMniejszyOdSerca() {
		assertFalse(KolorEnum.DIAMOND.isWieksza(KolorEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestWiekszyOdZoledzia() {
		assertTrue(KolorEnum.DIAMOND.isWieksza(KolorEnum.CLUB));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdWina() {
		assertTrue(KolorEnum.DIAMOND.isWieksza(KolorEnum.SPADE));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdDzwonka() {
		assertFalse(KolorEnum.DIAMOND.isWieksza(KolorEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdSerca() {
		assertFalse(KolorEnum.CLUB.isWieksza(KolorEnum.HEART));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdDzwonka() {
		assertFalse(KolorEnum.CLUB.isWieksza(KolorEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdWina() {
		assertTrue(KolorEnum.CLUB.isWieksza(KolorEnum.SPADE));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdZoledzia() {
		assertFalse(KolorEnum.CLUB.isWieksza(KolorEnum.CLUB));
	}

	@Test
	public void testujZeWinoJestMniejszeOdSerca() {
		assertFalse(KolorEnum.SPADE.isWieksza(KolorEnum.HEART));
	}

	@Test
	public void testujZeWinoJestMniejszeOdDzwonka() {
		assertFalse(KolorEnum.SPADE.isWieksza(KolorEnum.DIAMOND));
	}

	@Test
	public void testujZeWinoJestMniejszeOdZoledzia() {
		assertFalse(KolorEnum.SPADE.isWieksza(KolorEnum.CLUB));
	}

	@Test
	public void testujZeWInoJestWiekszeOdWina() {
		assertFalse(KolorEnum.SPADE.isWieksza(KolorEnum.SPADE));
	}

}
