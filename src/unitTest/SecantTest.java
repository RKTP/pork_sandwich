package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class SecantTest {
	Variable x;
	Expression sec;

	@Before
	public void setUp() throws Exception {
		x = new Variable("x", Math.PI/4);
		sec = new Secant(new Power(x, new Constant(1.0)),-2.7);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(sec.calc()/-2.7 , 1.414213562373095048, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(sec.derivative(null).calc()/-2.7, 1.414213562373095048, 0.0000001);
	}

}
