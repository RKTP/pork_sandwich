package model;

public interface Expression {
	public abstract Expression derivative();
	public abstract Expression integrate();
	public abstract double calc();
}
