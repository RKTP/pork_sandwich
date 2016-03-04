package model;

public class Cotangent extends Trigonometric {
	
	public Cotangent(Expression variable) {
		this.variable = variable;
	}
	
	public Cotangent(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
	}

	@Override
	public Expression derivative(Variable var) {
		return new Power(new Cosecant(this.variable), new Constant(2.0),this.coefficient*-1.0);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return new Divide(new Constant(1.0), new Tangent(this.variable)).calc() * this.coefficient;
	}

	@Override
	public String stringify() {
		return "cot(" + this.variable.stringify() + ")";
	}

}
