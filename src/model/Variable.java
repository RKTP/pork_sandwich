package model;

public class Variable implements Calculatable {
	private double value;

	@Override
	public double calc() {
		return value;
	}
	
}
