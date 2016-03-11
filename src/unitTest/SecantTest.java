package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class SecantTest {
	Variable x;
	Expression sec;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x', Math.PI/4);
		sec = new Secant(new Power(x, 1.0),-2.7);
	}

	@Test
	public void testCalc() throws Exception {
		x.setValue(Math.PI/4);
		assertEquals(1.414213562373095048, sec.calc()/-2.7, 0.0000001);
		x.setValue(3.0);
		assertEquals(-1.01010866590799375, sec.calc()/-2.7, 0.0000001);
		
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(Math.PI/4);
		assertEquals(1.414213562373095048, sec.derivative(x).calc()/-2.7, 0.0000001);
		x.setValue(3.0);
		assertEquals(0.143987498454555120, sec.derivative(x).calc()/-2.7, 0.0000001);
	}

}
