package model;

public interface Equation {
	public abstract Expression derivative(Variable var) throws Exception;
	public abstract Expression integrate(Variable var) throws Exception;
}
