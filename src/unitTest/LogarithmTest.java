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
		x = new Variable('x', 12.0);
		log = new Logarithm(new Constant(5.0),new Power(x,new Constant(1.0)),1.2);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(1.5439593106327713964, log.calc()/1.2, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(0.0517779112133009, log.derivative(x).calc()/1.2, 0.0000001);
	}

}
