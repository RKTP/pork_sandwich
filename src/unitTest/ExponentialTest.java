package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class ExponentialTest {
	Variable x;
	Expression exp;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x', 5.0);
		exp = new Exponential(new Euler(),new Power(x,1.0),1.5);
	}

	@Test
	public void testCalc() throws Exception {
		x.setValue(5.0);
		assertEquals(148.41315910257660342, exp.calc()/1.5, 0.0000001);
		x.setValue(0.0);
		assertEquals(1.0, exp.calc()/1.5, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(5.0);
		assertEquals(148.41315910257660342, exp.derivative(x).calc()/1.5, 0.0000001);
		x.setValue(0.0);
		assertEquals(1.0, exp.calc()/1.5, 0.0000001);
	}

}
