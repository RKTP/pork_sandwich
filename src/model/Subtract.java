package model;

public class Subtract extends Expression {
	private Expression left, right;
	
	public Subtract(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Expression derivative() throws Exception {
		Expression deriv = new Subtract(this.left.derivative(), this.right.derivative());
		return deriv;
	}

	@Override
	public Expression integrate() throws Exception {
		Expression integration = new Subtract(this.left.integrate(), this.right.integrate());
		return integration;
	}

	@Override
	public double calc() throws Exception {
		return this.left.calc() - this.right.calc();
	}

}
