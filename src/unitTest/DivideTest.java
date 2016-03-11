package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class DivideTest {
	Variable x;
	Expression div, left, right;
	
	@Before
	public void setUp() throws Exception {
		x = new Variable('x',2.0);

		ArrayList<Expression> leftExp = new ArrayList<Expression>();
		leftExp.add(new Power(x, 2.0,-1.0));
		leftExp.add(new Constant(1.0));
		ArrayList<Expression> rightExp = new ArrayList<Expression>();
		rightExp.add(new Constant(1.0));
		rightExp.add(new Power(x, 1.0));
		
		right = new Add(rightExp);	//-x^2+1
		left = new Add(leftExp);	//x+1
		div = new Divide(left, right); // left/right
	}

	@Test(expected = ArithmeticException.class)
	public void testCalc() throws Exception {
		x.setValue(2.0);
		assertEquals(-1.0, div.calc(), 0.0000001);
		x.setValue(-1.0);
		assertEquals(2.0, div.calc(), 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		x.setValue(2.0);
		assertEquals(-1.0, div.derivative(x).calc(), 0.0000001);
		x.setValue(4.0);
		assertEquals(-1.0, div.derivative(x).calc(), 0.0000001);
	}

}
