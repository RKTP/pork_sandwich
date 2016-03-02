//GREAT PROBLEM WITH THIS CLASS DESIGN
//MAY BE REMOVED

package model;

public class Composition implements Expression {
	Expression outer, inner;
	
	public Composition(Expression outer, Expression inner) {
		this.outer = outer;
		this.inner = inner;
	}
	
	@Override
	public Expression derivative() throws Exception {
		Expression left = new Composition(this.outer.derivative(), this.inner);
		Expression right = this.inner.derivative();
		return new Multiply(left, right);
	}

	@Override
	public Expression integrate() {
		/*
		 * to be implemented
		 */
		return null;
	}

	@Override
	public double calc() throws Exception {
		return this.outer.calc();
	}

}
