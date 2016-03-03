package model;

public class Multiply extends Expression {
	private Expression left, right;

	public Multiply(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Expression derivative() throws Exception {
		Expression left = new Multiply(this.left.derivative(), this.right);
		Expression right = new Multiply(this.left, this.right.derivative());
		Expression deriv = new Add(left, right);
		return deriv;
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return this.left.calc() * this.right.calc();
	}

}
