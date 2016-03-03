package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class PolynomialTest {
	Expression term;
	Variable x;

	@Before
	public void setUp() throws Exception {
		Variable x = new Variable("x",-2);
		term = new Add(new Multiply(new Value(2.0), new Power(x, new Value(7.0))), 
				new Add(new Multiply(new Value(-5.0), new Power(x, new Value(4.0))),
				new Add(new Multiply(new Value(3.0), new Power(x, new Value(3.0))),
				new Add(new Multiply(new Value(10.0), new Power(x, new Value(2.0))),
				new Add(new Multiply(new Value(-9.0), new Power(x, new Value(1.0))),new Value(2))))));
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(term.calc() , -300, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(term.derivative(null).calc(), 1043, 0.0000001);
	}
}
