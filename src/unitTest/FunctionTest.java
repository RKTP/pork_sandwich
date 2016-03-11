package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class FunctionTest {
	Function func;
	Function pfunc;
	Variable x,y,z;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x',-2);
		y = new Variable('y',1);
		z = new Variable('z',3);
		
		ArrayList<Expression> pExp = new ArrayList<>();
		pExp.add(new Power(x, new Constant(3.0)));
		pExp.add(new Power(y, new Constant(3.0)));
		pExp.add(new Power(z, new Constant(3.0)));
		pExp.add(new Multiply(new Power(x, 1.0), new Multiply(new Power(y, 1.0), new Power(z, 1.0)),-3.0));
		
		func = new Function(new Sine(new Power(x,new Constant(1.0))));	//sin(x)
		pfunc = new Function(new Add(pExp));	//x^3 + y^3 + z^3 - 3xyz
	}

	@Test
	public void testCalc() throws Exception {
		assertEquals(38.0 , pfunc.calc(), 0.0000001);
	}
	
	@Test
	public void testDeriv() throws Exception {
		assertEquals(33.0, pfunc.getDerivative('z').calc(), 0.0000001);
	}
	
	@Test
	public void testSetVariableValue() {
		pfunc.setVariableValue('x', 5.0);
		assertEquals(5.0,x.calc(),0.0000001);
	}
	
	@Test
	public void testIntegra() {
		
	}
	
	public void testIntegraInRange() {
		
	}
	
	
	@Test
	public void testIsContinuous() throws Exception {
		assertEquals(true, func.isContinuous(-2));
	}
	
	@Test
	public void testIsDerivativable() throws Exception {
		assertEquals(true, func.isDerivativable(-1));
	}

}
