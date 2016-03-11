package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import exception.OutOfRangeException;
import model.*;

public class FunctionTest {
	Function func;
	Function pfunc;
	Function sfunc;
	Variable x,y,z;

	@Before
	public void setUp() throws Exception {
		x = new Variable('x',-2);
		y = new Variable('y',1);
		z = new Variable('z',3);
		
		ArrayList<Expression> pExp = new ArrayList<>();
		pExp.add(new Power(x, 3.0));
		pExp.add(new Power(y, 3.0));
		pExp.add(new Power(z, 3.0));
		pExp.add(new Multiply(new Power(x, 1.0), new Multiply(new Power(y, 1.0), new Power(z, 1.0)),-3.0));
		pfunc = new Function(new Add(pExp));	//x^3 + y^3 + z^3 - 3xyz
		
		pExp.removeAll(pExp);
		pExp.add(new Logarithm(new Constant(5.0), new Power(x,1.0)));
		pExp.add(new Constant(0.5));
		func = new Function(new Add(pExp));	//log_5(x) + 0.5
		
		sfunc = new Function(new Power(x, 3.0));

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
	public void testIntegra() throws Exception {
		assertEquals(4.0,sfunc.getIntegra('x').calc(),0.0000001);
	}
	
	@Test
	public void testIntegraInRange() throws Exception {
		assertEquals(0.0, sfunc.getIntegraInRange('x', -1, 1), 0.0000001);
	}
	
	
	@Test
	public void testIsContinuous() throws Exception {
		assertEquals(true, func.isContinuous(12));
		assertEquals(true, func.isContinuous(2));
		assertEquals(false, func.isContinuous(0));
	}
	
	@Test
	public void testIsDerivativable() throws Exception {
		assertEquals(false, func.isDerivativable(-1));
		assertEquals(true, func.isDerivativable(10));
	}

}
