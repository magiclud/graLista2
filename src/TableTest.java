import org.junit.Test;

public class TableTest {

	/*
	 * @Before public void setUp() throws Exception { }
	 * 
	 * @After public void tearDown() throws Exception { }
	 */
	Table test;

	// Gdy niepoprawna ilość graczy
	@Test(expected = Exception.class)
	public void testTooManyPlayers() throws ExceptionsInGame {
		test = new Table(3, 4);
	}

}
