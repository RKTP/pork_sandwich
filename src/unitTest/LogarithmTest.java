package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class LogarithmTest {
	Variable x;
	Expression log;

	@Before
	public void setUp() throws Exception {
		x = new Variable("x", 12.0);
		log = new Logarithm(new Value(5.0),new Power(x,new Value(1.0)));
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(log.calc() , 1.5439593106327713964, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(log.derivative(null).calc(), 0.0517779112133009, 0.0000001);
	}

}
