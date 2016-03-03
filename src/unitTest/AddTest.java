package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class AddTest {
	Expression add;
	
	@Before
	public void setUp() throws Exception {
		Variable variable = new Variable("x", 5.0);
		add = new Add(new Power(variable, new Value(1.0)), new Value(1.5));
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(add.calc() , 6.5, 0.0000001);
	}
	
	@Test
	public void testderiv() throws Exception {
		assertEquals(add.derivative(null).calc(), 1.0, 0.0000001);
	}

}
