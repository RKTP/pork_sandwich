package model;

public class Cotangent extends Trigonometric {
	
	public Cotangent(Expression variable) {
		this.variable = variable;
	}

	@Override
	public Expression derivative(Variable var) {
		return new Multiply(new Value(-1.0), new Exponential(new Cosecant(this.variable), new Value(2.0)));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return new Divide(new Value(1.0), new Tangent(this.variable)).calc();
	}

	@Override
	public String stringify() {
		return "cot(" + this.variable.stringify() + ")";
	}

}
