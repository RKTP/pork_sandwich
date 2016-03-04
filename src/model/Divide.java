package model;

import java.util.ArrayList;

import exception.ValueException;

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
		if (this.right.calc() == 0) throw new ValueException();
		else return this.left.calc() / this.right.calc() * this.coefficient;
	}

	@Override
	public String stringify() {
		return this.left.stringify() + "/" + this.right.stringify();
	}

}
