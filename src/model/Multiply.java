package model;

import java.util.ArrayList;

public class Multiply extends Expression {
	private Expression left, right;

	public Multiply(Expression left, Expression right) {
		this.left = left;
		this.right = right;	
	}
	
	public Multiply(Expression left, Expression right, double co) {
		this.left = left;
		this.right = right;
		this.coefficient = co;
	}


	@Override
	public Expression derivative(Variable var) throws Exception {
		Expression left = new Multiply(this.left.derivative(var), this.right);
		Expression right = new Multiply(this.left, this.right.derivative(var));
		
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(left);
		exp.add(right);
		
		Expression deriv = new Add(exp, this.coefficient);
		return deriv;
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		return this.left.calc() * this.right.calc() * this.coefficient;
	}

	@Override
	public String stringify() {
		return this.left.stringify() + " * " + this.right.stringify();
	}

}
