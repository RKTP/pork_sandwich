package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class SubtractTest {
	Expression sub;
	
	@Before
	public void setUp() throws Exception {
		Variable variable = new Variable("x", 5.0);
		sub = new Subtract(new Value(6.5), new Power(variable, new Value(1.0)));
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(sub.calc() , 1.5, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(sub.derivative(null).calc(), -1.0, 0.0000001);
	}

}
