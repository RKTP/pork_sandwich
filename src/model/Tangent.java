package model;

import java.lang.Math;

public class Tangent extends Trigonometric {
	
	public Tangent(Expression variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Power(new Secant(this.variable),new Value(2.0));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.tan(this.variable.calc());
	}

	@Override
	public String stringify() {
		return "tan(" + this.variable.stringify() + ")";
	}

}
