package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class CosecantTest {
	Variable x;
	Expression csc;

	@Before
	public void setUp() throws Exception {
		x = new Variable("x", Math.PI/8);
		csc = new Cosecant(new Power(x, new Value(1.0)));
	}

	@Test
	public void testCalc() throws Exception {
		System.out.println(csc.calc());
		assertEquals(csc.calc() , 2.61312592975275305, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(csc.derivative(null).calc(), -6.308644059797900080, 0.0000001);
	}

}
