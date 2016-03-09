package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class TangentTest {
	Variable x;
	Expression tan;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x', Math.PI/4);
		tan = new Tangent(new Power(x, new Constant(1.0)),9);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(1.0, tan.calc()/9, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(2.0, tan.derivative(x).calc()/9, 0.0000001);
	}

}
