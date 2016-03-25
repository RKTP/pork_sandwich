package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Expression implements MathTerm {
	protected HashSet<Variable> varList;
	protected double coefficient = 1.0;
	
	public abstract Expression derivative(Variable var) throws Exception;
	public abstract Expression integrate(Variable var) throws Exception;
	public abstract double calc() throws Exception;
	public abstract String stringify() throws Exception;
	
	
	public Expression() {
		varList = new HashSet<Variable>();
	}
	
	public Set<Variable> getUsingVariables() {
		return this.varList;
	}
	
	public void setNegativeCoeff() {
		this.coefficient = this.coefficient * -1;
	}
	
	public boolean isCoeffPositive() {
		return this.coefficient >= 0;
	}
	
	protected String coeffToString() {
		if(Math.floor(this.coefficient) == this.coefficient) {
			return Integer.toString((int)this.coefficient);
		} else return Double.toString(this.coefficient);
	}
	
	public void setCoeff(double newCoeff) {
		this.coefficient = newCoeff;
	}
	
	public double getCoeff() {
		return this.coefficient;
	}
	
	public ArrayList<Expression> canonicalize() {
		ArrayList<Expression> exprs = new ArrayList<>();
		exprs.add(this);
		return exprs;
	}
}
