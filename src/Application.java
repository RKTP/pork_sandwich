import java.util.ArrayList;

import model.*;
import userInterface.GraphInterface;
import userInterface.MainGraphicInterface;

public class Application {

	public static void main(String[] args) throws Exception {
		Function func;
		Variable x = new Variable('x',8.0);
		Variable y = new Variable('y',2.0);
		Variable z = new Variable('z',-3.0);
		Expression x_var = new Power(x,new Constant(1.0));
		ArrayList<Expression> add = new ArrayList<>();
		add.add(new Power(x,new Constant(3.0),2));
		add.add(new Constant(-5.0));
		Expression log = new Logarithm(new Euler(),x_var);
		Expression exp = new Multiply(new Cosine(x_var), new Sine(new Multiply(log,new AddSub(add))));
		
		func = new Function(exp);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
		
		exp = new Power(new AddSub(add),new Constant(3.0));
		func = new Function(exp);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
		
		exp = new Exponential(new Constant(3.0), new AddSub(add));
		func = new Function(exp);
		x.setValue(2.0);
		
		System.out.println(func.stringify());
		System.out.println(func.calc());
		System.out.println(func.getDerivative('x').stringify());
		System.out.println(func.getDerivative('x').calc());
		
		add.removeAll(add);
		
		add.add(new Power(x,new Constant(3.0)));
		add.add(new Power(x,new Constant(2.0),-9));
		add.add(new Power(x,new Constant(1.0),6));
		add.add(new Constant(2.0));
		exp = new AddSub(add);
		func = new Function(exp, "testFunc");
		new GraphInterface(func, -5, 5).setVisible(true);
		
		Function iFunc = func.getIntegra('x');
		new GraphInterface(iFunc, -5,5).setVisible(true);
		
		Function idFunc = iFunc.getDerivative('x');
		new GraphInterface(idFunc,-5,5).setVisible(true);
		
		add.removeAll(add);
		
		add.add(new Power(x, new Constant(3.0)));
		add.add(new Power(y, new Constant(2.0),-2.0));
		add.add(new Power(z, new Constant(1.0),1.5));
		exp = new AddSub(add);
		func = new Function(exp);
		
		ArrayList<Function> funcList = func.getDerivative();
		
		for(Function e : funcList) {
			System.out.println(e.stringify());
		}
		
		//new MainGraphicInterface(new Model()).setVisible(true);
		
	}
	
	private void showDemo(Function func) {
		if(func.getVarNames().size()==1) {
			new GraphInterface(func, -10,10).setVisible(true);
		} else {
		}
	}

}
