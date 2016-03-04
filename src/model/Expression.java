package model;

import java.util.Set;

public abstract class Expression implements Term {
	protected Set<Variable> varList;
	protected double coefficient=1.0;
	
	public abstract Expression derivative(Variable var) throws Exception;
	public abstract Expression integrate(Variable var) throws Exception;
	public abstract double calc() throws Exception;
	
	public void setCoefficient(double co) {
		this.coefficient = co;
	}
	
	public double getCoefficient() {
		return this.coefficient;
	}
	
	public Set<Variable> usingVariables() {
		return this.varList;
	}
	
	public abstract String stringify();
}
