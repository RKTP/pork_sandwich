package model;

import java.lang.Math;

public class Cosine extends Trigonometric {
	
	public Cosine(Expression variable) {
		this.variable = variable;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	public Cosine(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
	
		this.varList.addAll(this.variable.getUsingVariables());
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		return new Multiply(new Sine(this.variable,-1.0*this.coefficient), this.variable.derivative(var) );
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
	public String stringify() throws Exception {
		String coeff = "";
		if(this.coefficient != 1) {
			coeff = this.coeffToString()  + "*";
		}
		
		return coeff + "cos(" + this.variable.stringify() + ")";
	}

}
