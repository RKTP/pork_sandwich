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
		x = new Variable('x', Math.PI/8);
		csc = new Cosecant(new Power(x, new Constant(1.0)),2.0);
	}

	@Test
	public void testCalc() throws Exception {
		x.setValue(Math.PI/8);
		assertEquals(2.61312592975275305, csc.calc()/2,  0.0000001);
		x.setValue(Math.PI/2);
		assertEquals(1, csc.calc()/2,  0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(Math.PI/8);
		assertEquals(-6.308644059797900, csc.derivative(x).calc()/2, 0.0000001);
		x.setValue(Math.PI/4);
		assertEquals(-1.414213562373095, csc.derivative(x).calc()/2, 0.0000001);
	}

}
