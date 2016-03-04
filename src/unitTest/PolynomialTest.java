package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class PolynomialTest {
	Expression term;
	Variable x;

	@Before
	public void setUp() throws Exception {
		Variable x = new Variable("x",-2);
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(new Power(x, new Constant(7.0),2.0));
		exp.add(new Power(x, new Constant(4.0),-5.0));
		exp.add(new Power(x, new Constant(3.0),3.0));
		exp.add(new Power(x, new Constant(2.0),10.0));
		exp.add(new Power(x, new Constant(1.0),-9.0));
		exp.add(new Constant(2));
		term = new Add(exp);
				
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
