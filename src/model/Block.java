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
	public Expression derivative(Variable var) throws Exception {
		return this.content.derivative(var);
	}

	@Override
	public Expression integrate(Variable var) throws Exception {
		return this.content.integrate(var);
	}

	@Override
	public double calc() throws Exception {
		return this.content.calc();
	}

	@Override
	public String stringify() {
		return "("+this.content.stringify()+")";
	}

}
