package model;

import java.util.ArrayList;

public class Constant extends Expression {
	private final double value;
	
	public Constant(double value) {
		if(value < 0) {
			setNegativeCoeff();
		}
		this.value = Math.abs(value);
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Constant(0);
	}

	@Override
	public Expression integrate(Variable var) throws Exception {
		return new Multiply(this, new Power(var, new Constant(1.0)));
	}

	@Override
	public double calc() {
		return this.value * this.coefficient;
	}

	@Override
	public String stringify() {
		String str = "";
		if(!this.isCoeffPositive()) str += "-";
		
		if(this.value == Math.floor(this.value)) {
			return str + Integer.toString((int)this.value);
		} else return str + Double.toString(this.value);
	}

}
