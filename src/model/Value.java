package model;

public class Value extends Expression {
	private double value;
	
	public Value(double value) {
		this.value = value;
	}
	
	@Override
	public Expression derivative() {
		return new Value(0);
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() {
		return value;
	}

}
