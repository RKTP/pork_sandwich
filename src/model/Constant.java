package model;

public class Constant extends Expression {
	private double value;
	
	public Constant(double value) {
		if(value < 0) {
			setNegativeCoeff();
			this.value = -value;
		} else this.value = value;
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
		return Double.toString(this.value);
	}

}
