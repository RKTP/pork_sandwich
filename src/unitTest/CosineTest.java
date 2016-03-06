package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class CosineTest {
	Variable x;
	Expression cos;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x', Math.PI/4);
		cos = new Cosine(new Power(x, new Constant(1.0)),2.0);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(cos.calc()/2, 0.707106781186547524, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(cos.derivative(x).calc()/2 , -0.7071067811865475, 0.0000001);
	}

}
