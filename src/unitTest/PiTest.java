package unitTest;

import static org.junit.Assert.*;

import model.Expression;
import model.Pi;
import org.junit.Before;
import org.junit.Test;

public class PiTest {
	Expression pi;

	@Before
	public void setUp() throws Exception {
		pi = new Pi();
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(Math.PI, pi.calc(), 0.0000001);
	}

	@Test
	public void testderiv() throws Exception {
		assertEquals(0.0, pi.derivative(null).calc(), 0.0000001);
	}

}
