package model;

public class Variable implements Term {
	private double value;
	private String term;
	
	public Variable(String term) {
		this.term = term;
		this.value = 0;
	}
	
	public Variable(String term, double value) {
		this.term = term;
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
		return term;
	}
	
}
