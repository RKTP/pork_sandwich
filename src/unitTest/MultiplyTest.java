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
		
		left = new Add(leftExp);
		right = new Add(rightExp);
		mul = new Multiply(left, right);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(mul.calc() , -12.0, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(mul.derivative(x).calc(), -19.0, 0.0000001);
	}

}
