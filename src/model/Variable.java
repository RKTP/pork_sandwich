package model;

public class Variable implements Term {
	private double value;
	private char name;
	
	public Variable(char term) {
		this.name = term;
		this.value = 0;
	}
	
	public Variable(char term, double value) {
		this.name = term;
		this.value = value;
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
	
}
