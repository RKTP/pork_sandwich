package model;

import java.lang.Math;

public class Cosine extends Trigonometric {
	
	public Cosine(Expression variable) {
		this.variable = variable;
	}
	
	public Cosine(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
	}

	@Override
	public Expression derivative(Variable var) {
		return new Sine(this.variable,-1.0*this.coefficient);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.cos(this.variable.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		return "cos(" + this.variable.stringify() + ")";
	}

}
