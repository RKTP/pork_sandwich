import java.util.ArrayList;

import model.*;

public class Application {

	public static void main(String[] args) throws Exception {
		Function func;
		Variable x = new Variable('x',8.0);
		Expression x_var = new Power(x,new Constant(1.0));
		ArrayList<Expression> add = new ArrayList<>();
		add.add(new Power(x,new Constant(3.0),2));
		add.add(new Constant(-5.0));
		Expression log = new Logarithm(new Euler(),x_var);
		Expression exp = new Multiply(new Cosine(x_var), new Sine(new Multiply(log,new Add(add))));
		
		func = new Function(exp);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
		
		exp = new Power(new Add(add),new Constant(3.0));
		func = new Function(exp);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
		
		exp = new Exponential(new Constant(3.0), new Add(add));
		func = new Function(exp);
		x.setValue(2.0);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
	}

}
