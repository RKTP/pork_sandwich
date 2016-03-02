package model;

public interface Expression {
	public abstract Expression derivative() throws Exception;
	public abstract Expression integrate() throws Exception;
	public abstract double calc() throws Exception;
}
