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
		leftExp.add(new Power(x, new Constant(2.0),-1.0));
		leftExp.add(new Constant(1.0));
		ArrayList<Expression> rightExp = new ArrayList<Expression>();
		rightExp.add(new Constant(1.0));
		rightExp.add(new Power(x, new Constant(1.0)));
		
		right = new Add(rightExp);
		left = new Add(leftExp);
		div = new Divide(left, right);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(-1.0, div.calc(), 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(-1.0, div.derivative(x).calc(), 0.0000001);
	}

}
