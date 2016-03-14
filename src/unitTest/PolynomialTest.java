package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class PolynomialTest {
	Expression term;
	Expression pTerm;
	Variable x;
	Variable y;
	Variable z;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x',-2);
		y = new Variable('y',1);
		z = new Variable('z',3);

		ArrayList<Expression> exp = new ArrayList<>();
		exp.add(new Power(x, 7.0,2.0));
		exp.add(new Power(x, 4.0,-5.0));
		exp.add(new Power(x, 3.0, 3.0));
		exp.add(new Power(x, 2.0,10.0));
		exp.add(new Power(x, 1.0,-9.0));
		exp.add(new Constant(2));
		term = new AddSub(exp);

		ArrayList<Expression> pExp = new ArrayList<>();
		pExp.add(new Power(x, 3.0));
		pExp.add(new Power(y, 3.0));
		pExp.add(new Power(z, 3.0));
		pExp.add(new Multiply(new Power(x, 1.0), new Multiply(new Power(y, 1.0), new Power(z, 1.0)),-3.0));
		pTerm = new AddSub(pExp);

	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(-300, term.calc(), 0.0000001);
		assertEquals(38, pTerm.calc(), 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(1043.0, term.derivative(x).calc(), 0.0000001);
		assertEquals(3, pTerm.derivative(x).calc(), 0.0000001);
	}
}
