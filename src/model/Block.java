package model;

/*
 * Only exists for Building formula in String / LaTeX
 * If any other solution comes out, this will be removed immediately.
 */

public class Block extends Expression {
	Expression content;
	
	public Block(Expression content) {
		this.content = content;
	}
	
	@Override
	public Expression derivative() throws Exception {
		return this.content.derivative();
	}

	@Override
	public Expression integrate() throws Exception {
		return this.content.integrate();
	}

	@Override
	public double calc() throws Exception {
		return this.content.calc();
	}

}
