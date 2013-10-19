import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class SuitEnumTest {


	@Test
	public void testujZeSerceJestWiekszeOdDzwonka() {
		assertTrue(SuitEnum.HEART.isBigger(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeSerceJestWiekszeOdZoledzia() {
		assertTrue(SuitEnum.HEART.isBigger(SuitEnum.CLUB));
	}

	@Test
	public void testujZeSerceJestWiekszeOdWina() {
		assertTrue(SuitEnum.HEART.isBigger(SuitEnum.SPADE));
	}

	@Test
	public void testujZeSerceNieJestWiekszeOdSerca() {
		assertFalse(SuitEnum.HEART.isBigger(SuitEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestMniejszyOdSerca() {
		assertFalse(SuitEnum.DIAMOND.isBigger(SuitEnum.HEART));
	}

	@Test
	public void testujZeDzwonekJestWiekszyOdZoledzia() {
		assertTrue(SuitEnum.DIAMOND.isBigger(SuitEnum.CLUB));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdWina() {
		assertTrue(SuitEnum.DIAMOND.isBigger(SuitEnum.SPADE));
	}

	@Test
	public void testujZeDzwonekJestWiekszeOdDzwonka() {
		assertFalse(SuitEnum.DIAMOND.isBigger(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdSerca() {
		assertFalse(SuitEnum.CLUB.isBigger(SuitEnum.HEART));
	}

	@Test
	public void testujZeZoledzJestMniejszyOdDzwonka() {
		assertFalse(SuitEnum.CLUB.isBigger(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdWina() {
		assertTrue(SuitEnum.CLUB.isBigger(SuitEnum.SPADE));
	}

	@Test
	public void testujZeZoledzJestWiekszyOdZoledzia() {
		assertFalse(SuitEnum.CLUB.isBigger(SuitEnum.CLUB));
	}

	@Test
	public void testujZeWinoJestMniejszeOdSerca() {
		assertFalse(SuitEnum.SPADE.isBigger(SuitEnum.HEART));
	}

	@Test
	public void testujZeWinoJestMniejszeOdDzwonka() {
		assertFalse(SuitEnum.SPADE.isBigger(SuitEnum.DIAMOND));
	}

	@Test
	public void testujZeWinoJestMniejszeOdZoledzia() {
		assertFalse(SuitEnum.SPADE.isBigger(SuitEnum.CLUB));
	}

	@Test
	public void testujZeWInoJestWiekszeOdWina() {
		assertFalse(SuitEnum.SPADE.isBigger(SuitEnum.SPADE));
	}

}
