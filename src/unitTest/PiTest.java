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
		assertEquals(pi.calc() , Math.PI, 0.0000001);
	}

	@Test
	public void testderiv() throws Exception {
		assertEquals(pi.derivative(null).calc(), 0.0, 0.0000001);
	}

}
