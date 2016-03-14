package model;

import java.util.ArrayList;

public class Multiply extends Expression {
	private Expression left, right;

	public Multiply(Expression left, Expression right) {
		this.left = left;
		this.right = right;	
		
		this.varList.addAll(this.left.getUsingVariables());
		this.varList.addAll(this.right.getUsingVariables());
	}
	
	public Multiply(Expression left, Expression right, double co) {
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

		Expression left = new Multiply(this.left.derivative(var), this.right);
		Expression right = new Multiply(this.left, this.right.derivative(var));
		
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(left);
		exp.add(right);
		
		Expression deriv = new AddSub(exp, this.coefficient);
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
		String leftString, rightString;
		try {
			if(this.left instanceof Constant) {
				double cRight = this.right.getCoeff();
				this.right.setCoeff(this.right.getCoeff() * this.left.calc());
				String str = this.right.stringify();
				this.right.setCoeff(cRight);
				return str;
			} else if(this.right instanceof Constant) {
				double cLeft = this.left.getCoeff();
				this.left.setCoeff(this.left.getCoeff() * this.right.calc());
				String str = this.left.stringify();
				this.left.setCoeff(cLeft);
				return str;
			} else if(this.left instanceof Constant && this.right instanceof Constant) {
				return new Constant(this.left.calc() * this.right.calc()).stringify();
			}
		} catch (Exception e) {
			//pass
		}
		
		if(this.left instanceof AddSub) {
			leftString = "("+this.left.stringify()+")";
		} else leftString = this.left.stringify();
		
		if(this.right instanceof AddSub) {
			rightString = "("+this.right.stringify()+")";
		} else rightString = this.right.stringify();
		
		return leftString + " * " + rightString;
	}

}
