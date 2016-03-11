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
		cos = new Cosine(new Power(x, 1.0),2.0);
	}

	@Test
	public void testCalc() throws Exception {
		x.setValue(Math.PI/4);
		assertEquals(0.707106781186547524, cos.calc()/2, 0.0000001);
		x.setValue(1.0);
		assertEquals(0.540302305868139717, cos.calc()/2, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(Math.PI/4);
		assertEquals(-0.7071067811865475, cos.derivative(x).calc()/2, 0.0000001);
		x.setValue(1.0);
		assertEquals(-0.8414709848078965, cos.derivative(x).calc()/2, 0.0000001);
	}

}
