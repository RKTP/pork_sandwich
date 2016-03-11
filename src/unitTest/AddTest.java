package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class AddTest {
	Expression add;
	Variable variable;
	
	@Before
	public void setUp() throws Exception {
		variable = new Variable('x', 5.0);
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(new Power(variable, 1.0));
		exp.add(new Constant(1.5));
		add = new Add(exp);
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(6.5, add.calc(), 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(1.0, add.derivative(variable).calc(), 0.0000001);
		variable.setValue(3.0);
		assertEquals(1.0, add.derivative(variable).calc(), 0.0000001);
	}

}
