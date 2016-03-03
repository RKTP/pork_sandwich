package model;

import java.lang.Math;

public class Sine extends Trigonometric {
	
	public Sine(Expression variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Cosine(this.variable);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		Math.sin(this.variable.calc());
		return 0;
	}

	@Override
	public String stringify() {
		return "sin(" + this.variable.stringify() + ")";
	}

}
