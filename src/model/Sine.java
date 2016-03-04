package model;

import java.lang.Math;

public class Sine extends Trigonometric {
	
	public Sine(Expression variable) {
		this.variable = variable;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	public Sine(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}

	@Override
	public Expression derivative(Variable var) {
		return new Cosine(this.variable, this.coefficient);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.sin(this.variable.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		return "sin(" + this.variable.stringify() + ")";
	}

}
