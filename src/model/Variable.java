package model;

public class Variable extends Expression {
	private double value;
	private char name;
	
	public Variable(char term) {
		this.name = term;
		this.value = 0;
		this.varList.add(this);
	}
	
	public Variable(char term, double value) {
		this.name = term;
		this.value = value;
		this.varList.add(this);
	}

	public char getName() {
		return this.name;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public double calc() {
		return value;
	}

	@Override
	public String stringify() {
		return Character.toString(this.name);
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(var == this) return new Constant(1);
		return new Constant(0);
	}

	@Override
	public Expression integrate(Variable var) throws Exception {
		return new Divide(new Power(this, 2.0), new Constant(2.0));
	}
	
}
