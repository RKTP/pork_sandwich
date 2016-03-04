package model;

import java.lang.Math;

public class Tangent extends Trigonometric {
	
	public Tangent(Expression variable) {
		this.variable = variable;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	public Tangent(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Power(new Secant(this.variable),new Constant(2.0), this.coefficient);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return Math.tan(this.variable.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		return "tan(" + this.variable.stringify() + ")";
	}

}
