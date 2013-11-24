import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SuitEnumTest {

	@Test
	public void testujZeSerceJestWiekszeOdDzwonka() {
		assertTrue(SuitEnum.HEART.isGreater(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeSerceJestWiekszeOdZoledzia() {
		assertTrue(SuitEnum.HEART.isGreater(SuitEnum.CLUB));
	}

	@Test
	public void testujZeSerceJestWiekszeOdWina() {
		assertTrue(SuitEnum.HEART.isGreater(SuitEnum.SPADE));
	}

	@Test
	public void testujZeSerceNieJestWiekszeOdSerca() {
		assertFalse(SuitEnum.HEART.isGreater(SuitEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestMniejszyOdSerca() {
		assertFalse(SuitEnum.DIAMOND.isGreater(SuitEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestWiekszyOdZoledzia() {
		assertTrue(SuitEnum.DIAMOND.isGreater(SuitEnum.CLUB));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdWina() {
		assertTrue(SuitEnum.DIAMOND.isGreater(SuitEnum.SPADE));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdDzwonka() {
		assertFalse(SuitEnum.DIAMOND.isGreater(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdSerca() {
		assertFalse(SuitEnum.CLUB.isGreater(SuitEnum.HEART));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdDzwonka() {
		assertFalse(SuitEnum.CLUB.isGreater(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdWina() {
		assertTrue(SuitEnum.CLUB.isGreater(SuitEnum.SPADE));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdZoledzia() {
		assertFalse(SuitEnum.CLUB.isGreater(SuitEnum.CLUB));
	}

	@Test
	public void testujZeWinoJestMniejszeOdSerca() {
		assertFalse(SuitEnum.SPADE.isGreater(SuitEnum.HEART));
	}

	@Test
	public void testujZeWinoJestMniejszeOdDzwonka() {
		assertFalse(SuitEnum.SPADE.isGreater(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeWinoJestMniejszeOdZoledzia() {
		assertFalse(SuitEnum.SPADE.isGreater(SuitEnum.CLUB));
	}

	@Test
	public void testujZeWInoJestWiekszeOdWina() {
		assertFalse(SuitEnum.SPADE.isGreater(SuitEnum.SPADE));
	}

}
