package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Expression implements Term {
	protected HashSet<Variable> varList;
	protected double coefficient = 1.0;
	
	public abstract Expression derivative(Variable var) throws Exception;
	public abstract Expression integrate(Variable var) throws Exception;
	public abstract double calc() throws Exception;
	
	public Expression() {
		varList = new HashSet<Variable>();
	}
	
	public Set<Variable> getUsingVariables() {
		return this.varList;
	}
	
	public abstract String stringify();
}
