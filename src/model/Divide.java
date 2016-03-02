package model;

import exception.ValueException;

public class Divide implements Expression {
	private Expression left, right;

	@Override
	public Expression derivative() {
		return null;
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() throws Exception {
		if (this.right.calc() == 0) throw new ValueException();
		else return this.left.calc() / this.right.calc();
	}

}
