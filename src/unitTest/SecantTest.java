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
		sec = new Secant(new Power(x, new Value(1.0)));
	}

	@Test
	public void testCalc() throws Exception {
		System.out.println(sec.calc());
		assertEquals(sec.calc() , 1.414213562373095048, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(sec.derivative(null).calc(), 1.414213562373095048, 0.0000001);
	}

}
