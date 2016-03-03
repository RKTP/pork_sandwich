package model;

import java.lang.Math;

public class Cosine extends Trigonometric {
	
	public Cosine(Expression variable) {
		this.variable = variable;
	}

	@Override
	public Expression derivative(Variable var) {
		return new Multiply(new Value(-1.0), new Sine(this.variable));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.cos(this.variable.calc());
	}

	@Override
	public String stringify() {
		return "cos(" + this.variable.stringify() + ")";
	}

}
