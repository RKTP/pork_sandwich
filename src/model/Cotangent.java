package model;

public class Cotangent extends Trigonometric {
	
	public Cotangent(Expression variable) {
		this.variable = variable;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	public Cotangent(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		return new Multiply(new Power(new Cosecant(this.variable), new Constant(2.0),this.coefficient*-1.0), this.variable.derivative(var));
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
		String coeff = "";
		if(this.coefficient == -1) {
			coeff = "-";
		} else if(this.coefficient != 1) {
			coeff = this.coeffToString();
		}
		return coeff + "cot(" + this.variable.stringify() + ")";
	}

}
