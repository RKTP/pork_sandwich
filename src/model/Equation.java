package model;

public interface Equation {
	public abstract Expression derivative() throws Exception;
	public abstract Expression integrate() throws Exception;
}
