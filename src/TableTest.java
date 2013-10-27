import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TableTest {

/*	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}*/
	Table test;
	// Gdy niepoprawna ilość graczy
	@Test(expected = Exception.class)
	public void testTooManyPlayers() {
		test = new Table(3,4);
	}
	
	
	

}
