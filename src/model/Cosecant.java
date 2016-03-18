package model;

public class Cosecant extends Trigonometric {	
	
	public Cosecant(Expression variable) {
		this.variable = variable;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	public Cosecant(Expression variable, double co) {
		this.variable = variable;
		this.coefficient = co;
		
		this.varList.addAll(this.variable.getUsingVariables());
	}
	
	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		return new Multiply(new  Multiply(new Cosecant(this.variable), new Cotangent(this.variable),this.coefficient*-1.0), this.variable.derivative(var));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return new Divide(new Constant(1.0), new Sine(this.variable)).calc() * this.coefficient;
	}

	@Override
	public String stringify() throws Exception {
		String coeff = "";
		if(this.coefficient != 1) {
			coeff = this.coeffToString() + "*";
		}
		
		return coeff + "csc(" + this.variable.stringify() + ")";
	}

}
