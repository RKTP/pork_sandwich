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
		exp = new Exponential(new Euler(),new Power(x,new Constant(1.0)),1.5);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(148.41315910257660342, exp.calc()/1.5, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(148.41315910257660342, exp.derivative(x).calc()/1.5, 0.0000001);
	}

}
