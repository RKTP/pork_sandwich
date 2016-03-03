package model;

public class Secant extends Trigonometric {
	
	public Secant(Expression variable) {
		this.variable = variable;
		
	}
	
	@Override
	public Expression derivative(Variable var) {
		return new Multiply(new Secant(variable), new Tangent(variable));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return new Divide(new Value(1.0), new Cosine(this.variable)).calc();
	}

	@Override
	public String stringify() {
		return "sec(" + this.variable.stringify() + ")";
	}

}
