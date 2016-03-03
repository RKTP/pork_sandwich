package model;

public class Pi extends Value {
	private final double value = 3.14159265358979323846;
	
	public Pi() {
		super(0);
	}
	
	public Pi(double value) {
		super(0);
	}
	
	@Override
	public double calc() {
		return this.value;
	}
	
	@Override
	public String stringify() {
		return "¥ð";
	}
}
