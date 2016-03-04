package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class CotangentTest {
	Variable x;
	Expression cot;

	@Before
	public void setUp() throws Exception {
		x = new Variable("x", Math.PI/8);
		cot = new Cotangent(new Power(x, new Constant(1.0)),3.0);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(cot.calc()/3 , 2.414213562373095048, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(cot.derivative(null).calc()/3, -6.8284271247461900976, 0.0000001);
	}

}
