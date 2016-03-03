package model;

public class Euler extends Value {
	private final double value = 2.71828182845904523536;
	
	public Euler() {
		super(0);
	}
	
	public Euler(double value) {
		super(0);
	}
	
	@Override
	public double calc() {
		return this.value;
	}
	
	@Override
	public String stringify() {
		return "e";
	}
}
