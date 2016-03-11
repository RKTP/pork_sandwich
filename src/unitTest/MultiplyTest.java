package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class MultiplyTest {
	Variable x;
	Expression mul, left, right;
	
	@Before
	public void setUp() throws Exception {
		x = new Variable('x',2.0);
		ArrayList<Expression> leftExp = new ArrayList<Expression>();
		leftExp.add(new Power(x, new Constant(1.0)));
		leftExp.add(new Constant(2.0));
		ArrayList<Expression> rightExp = new ArrayList<Expression>();
		rightExp.add(new Constant(1.0));
		rightExp.add(new Power(x, new Constant(2.0), -1.0));
		
		left = new Add(leftExp);			//x+2
		right = new Add(rightExp);			//1-x^2
		mul = new Multiply(left, right);	//(x+2) * (1-x^2)
	}

	@Test
	public void testCalc() throws Exception {
		x.setValue(2.0);
		assertEquals(-12.0, mul.calc(), 0.0000001);
		x.setValue(-4.0);
		assertEquals(30.0, mul.calc(), 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(2.0);
		assertEquals(-19.0, mul.derivative(x).calc(), 0.0000001);
		x.setValue(-4.0);
		assertEquals(-31.0, mul.derivative(x).calc(), 0.0000001);
	}

}
