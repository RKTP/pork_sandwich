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
		x = new Variable("x", 5.0);
		exp = new Exponential(new Euler(),new Power(x,new Value(1.0)));
	}

	@Test
	public void testCalc() throws Exception {
		Value eu = new Euler();
		System.out.print(eu.calc());
		assertEquals(exp.calc() , 148.41315910257660342, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(exp.derivative(null).calc(), 148.41315910257660342, 0.0000001);
	}

}
