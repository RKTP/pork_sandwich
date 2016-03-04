package model;

import java.lang.Math;

public class Exponential extends Expression {
	private Expression base;
	private Expression variable;
	
	public Exponential(Expression base, Expression variable) {
		this.base = base;
		this.variable = variable;
	}
	
	public Exponential(Expression base, Expression variable, double co) {
		this.base = base;
		this.variable = variable;
		this.coefficient = co;
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Multiply(new Exponential(this.base, this.variable), new Logarithm(this.base, new Euler()), this.coefficient);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.pow(this.base.calc(), this.variable.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		return this.base.stringify() + "^" + this.variable.stringify();
	}

}
