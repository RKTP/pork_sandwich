package model;

import java.lang.Math;

public class Tangent extends Trigonometric {
	
	public Tangent(Expression variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression derivative() {
		return new Exponential(new Secant(this.variable),new Value(2.0));
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.tan(this.variable.calc());
	}

}
