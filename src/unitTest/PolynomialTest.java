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
		x = new Variable("x",-2);
		y = new Variable("y",1);
		z = new Variable("z",3);

		ArrayList<Expression> exp = new ArrayList<>();
		exp.add(new Power(x, new Constant(7.0),2.0));
		exp.add(new Power(x, new Constant(4.0),-5.0));
		exp.add(new Power(x, new Constant(3.0), 3.0));
		exp.add(new Power(x, new Constant(2.0),10.0));
		exp.add(new Power(x, new Constant(1.0),-9.0));
		exp.add(new Constant(2));
		term = new Add(exp);

		ArrayList<Expression> pExp = new ArrayList<>();
		pExp.add(new Power(x, new Constant(3.0)));
		pExp.add(new Power(y, new Constant(3.0)));
		pExp.add(new Power(z, new Constant(3.0)));
		pExp.add(new Multiply(new Power(x, new Constant(1.0)), new Multiply(new Power(y, new Constant(1.0)), new Power(z, new Constant(1.0))),-3.0));
		pTerm = new Add(pExp);

	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(term.calc() , -300, 0.0000001);
		assertEquals(pTerm.calc(), 38, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(term.derivative(x).calc(), 1043.0, 0.0000001);
		assertEquals(pTerm.derivative(x).calc(), 3, 0.0000001);
	}
}
