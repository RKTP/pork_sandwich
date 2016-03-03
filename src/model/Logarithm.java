package model;

import java.lang.Math;

public class Logarithm extends Expression {
	Expression base;
	Expression param;
	
	public Logarithm(Expression base, Expression param) {
		this.base = base;
		this.param = param;
		
	}

	@Override
	public Expression derivative(Variable var) {
		Expression divider = new Multiply(this.param, new Logarithm(new Euler(), this.base));
		return new Divide(new Value(1.0), divider);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.log(this.param.calc()) / Math.log(this.base.calc());
	}

	@Override
	public String stringify() {
		return "log(" + base.stringify() + " | " + param.stringify() + ")";
	}

}
