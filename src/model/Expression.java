package model;

public abstract class Expression implements Term {
	public abstract Expression derivative(Variable var) throws Exception;
	public abstract Expression integrate(Variable var) throws Exception;
	public abstract double calc() throws Exception;
	
	public abstract String stringify();
}
