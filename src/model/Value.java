package model;

public class Value extends Expression {
	private double value;
	
	public Value(double value) {
		this.value = value;
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Value(0);
	}

	@Override
	public Expression integrate(Variable var) {
		return new Multiply(this, new Power(var, new Value(1.0)));
	}

	@Override
	public double calc() {
		return value;
	}

	@Override
	public String stringify() {
		return Double.toString(this.value);
	}

}
