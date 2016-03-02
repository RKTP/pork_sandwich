package model;

public class Polynomial implements Expression {
	Expression variable;
	Expression power;
	
	public Polynomial(Expression variable, Expression power) {
		this.variable = variable;
		this.power = power;
	}
	
	@Override
	public Expression derivative() {
		return null;
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() {
		return 0;
	}

}
