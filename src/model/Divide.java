package model;

import exception.ValueException;

public class Divide extends Expression {
	private Expression left, right;
	
	public Divide(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		Expression dividee, divider;
		dividee = new Add(new Multiply(left, right.derivative(var)), new Multiply(left.derivative(var), right));
		divider = new Power(this.right, new Value(2.0));
		return new Divide(dividee, divider);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		if (this.right.calc() == 0) throw new ValueException();
		else return this.left.calc() / this.right.calc();
	}

	@Override
	public String stringify() {
		return this.left.stringify() + "/" + this.right.stringify();
	}

}
