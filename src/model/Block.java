package model;

/*
 * This may not be needed
 */

public class Block implements Expression {
	Expression content;
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
