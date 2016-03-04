package model;

public class Variable implements Term {
	private double value;
	private String name;
	
	public Variable(String term) {
		this.name = term;
		this.value = 0;
	}
	
	public Variable(String term, double value) {
		this.name = term;
		this.value = value;
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
		return name;
	}
	
}
