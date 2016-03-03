package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class MultiplyTest {
	Variable x;
	Expression mul, left, right;
	
	@Before
	public void setUp() throws Exception {
		x = new Variable("x",2.0);
		left = new Add(new Power(x, new Value(1.0)), new Value(2.0));
		right = new Subtract(new Value(1.0), new Power(x, new Value(2.0)));
		mul = new Multiply(left, right);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(mul.calc() , -12.0, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(mul.derivative(null).calc(), -19.0, 0.0000001);
	}

}
