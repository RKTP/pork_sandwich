package model;

import java.lang.Math;

public class Logarithm implements Expression {
	Expression param;
	Expression base;
	
	public Logarithm(Expression param, Expression base) {
		this.param = param;
		this.base = base;
	}

	@Override
	public Expression derivative() {
		Expression divider = new Multiply(this.param, new Logarithm(this.base, new Euler()));
		return new Divide(new Value(1.0), divider);
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.log(this.param.calc()) / Math.log(this.base.calc());
	}

}
