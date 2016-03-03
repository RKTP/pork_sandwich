package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class DivideTest {
	Variable x;
	Expression div, left, right;
	
	@Before
	public void setUp() throws Exception {
		x = new Variable("x",2.0);
		right = new Add(new Power(x, new Value(1.0)), new Value(1.0));
		left = new Subtract(new Value(1.0), new Power(x, new Value(2.0)));
		div = new Divide(left, right);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(div.calc() , -1.0, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(div.derivative(null).calc(), -1.0, 0.0000001);
	}

}
