package model;

public class Variable implements Expression {
	private double value;
	
	@Override
	public Expression derivative() {
		return new Value(1.0);
	}
	@Override
	public Expression integrate() {
		return new Multiply(new Value(0.5), new Exponential(this, new Value(2.0)));
	}
	@Override
	public double calc() {
		return value;
	}
	
	

}
