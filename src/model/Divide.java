package model;

import java.util.ArrayList;

import exception.NoSuchSyntaxExistsException;
import exception.NumberOfVariableMismatchException;

public class Divide extends Expression {
	private Expression left, right;
	
	public Divide(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		
		this.varList.addAll(this.left.getUsingVariables());
		this.varList.addAll(this.right.getUsingVariables());
	}
	
	public Divide(Expression left, Expression right, double co) {
		this.left = left;
		this.right = right;
		this.coefficient = co;
		
		this.varList.addAll(this.left.getUsingVariables());
		this.varList.addAll(this.right.getUsingVariables());
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		Expression dividee, divider;
		
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(new Multiply(left.derivative(var), right));
		exp.add(new Multiply(left, right.derivative(var),-1.0));
		
		dividee = new Add(exp);
		divider = new Power(this.right, new Constant(2.0));
		return new Divide(dividee, divider, this.coefficient);
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		if (this.right.calc() == 0) throw new ArithmeticException();
		else return this.left.calc() / this.right.calc() * this.coefficient;
	}

	@Override
	public String stringify() {
		String coeff = "";
		if(this.coefficient == -1) {
			coeff = "-";
		} else if(this.coefficient != 1) {
			coeff = this.coeffToString();
		}
		
		return coeff + "(" + this.left.stringify() + ")" + " / " + "(" + this.right.stringify() + ")";
	}

}
