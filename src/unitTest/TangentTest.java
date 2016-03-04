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
		x = new Variable("x", Math.PI/4);
		tan = new Tangent(new Power(x, new Constant(1.0)),9);
	}

	@Test
	public void testCalc() throws Exception {
		System.out.println(tan.calc());
		assertEquals(tan.calc()/9 , 1.0, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(tan.derivative(null).calc()/9, 2.0, 0.0000001);
	}

}
