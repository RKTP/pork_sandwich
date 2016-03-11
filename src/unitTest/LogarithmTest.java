package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.OutOfRangeException;
import model.*;

public class LogarithmTest {
	Variable x;
	Expression log;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x', 12.0);
		log = new Logarithm(new Constant(5.0),new Power(x,new Constant(1.0)),1.2);
	}

	@Test (expected = OutOfRangeException.class)
	public void testCalc() throws Exception {
		x.setValue(12.0);
		assertEquals(1.5439593106327713964, log.calc()/1.2, 0.0000001);
		x.setValue(-1.2);
		assertEquals(0.0, log.calc()/1.2, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(12.0);
		assertEquals(0.0517779112133009, log.derivative(x).calc()/1.2, 0.0000001);
		x.setValue(-1.2);
		assertEquals(-0.517779, log.derivative(x).calc()/1.2, 0.000001);
	}

}
