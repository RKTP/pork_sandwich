package model;

public class Cosecant extends Trigonometric {	
	
	public Cosecant(Expression variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression derivative() {
		return new Multiply(new Value(-1.0), new Multiply(new Cosecant(this.variable), new Cotangent(this.variable)));
	}

	@Override
	public Expression integrate() {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return new Divide(new Value(1.0), new Sine(this.variable)).calc();
	}

}
