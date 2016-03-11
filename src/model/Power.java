package model;

import java.lang.Math;
import java.util.ArrayList;

import exception.CannotIntegrateException;
import exception.NoSuchSyntaxExistsException;

public class Power extends Expression {
	Term variable;
	Expression power;
	
	public Power(Term variable, Expression power) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = power;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Term variable, double power) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = new Constant(power);
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Term variable, double power, double co) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = new Constant(power);
		this.coefficient = co;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Term variable, Expression power, double co) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = power;
		this.coefficient = co;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(this.power);
		exp.add(new Constant(-1.0));
		
		Expression newPow = new Add(exp);
		double newCoeff = this.coefficient * this.power.calc();

		if(!(this.variable instanceof Expression)) {
			return new Power(this.variable, newPow, newCoeff);
		} else {
			return new Multiply(new Multiply(this.power, new Power(this.variable, new Add(exp)), this.coefficient), ((Expression) this.variable).derivative(var));
		}
	}

	@Override
	public Expression integrate(Variable var) throws NoSuchSyntaxExistsException, CannotIntegrateException {
		if(!this.varList.contains(var)) {
			throw new CannotIntegrateException();
		}
		
		if(!(this.variable instanceof Expression)) {
			ArrayList<Expression> exp = new ArrayList<Expression>();
			exp.add(this.power);
			exp.add(new Constant(1.0));
			
			return new Divide(new Power(this.variable, new Add(exp)), new Add(exp), this.coefficient);
		} else {
			return null;//function integration
		}
	}

	@Override
	public double calc() throws Exception {
		return Math.pow(this.variable.calc(), this.power.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		String coeff = "";
		String str = "";
		if(this.coefficient == -1) {
			coeff = "-";
		} else if(this.coefficient != 1) {
			coeff = this.coeffToString();
		}
		try {
			if(this.power.calc() == 1.0) return coeff + this.variable.stringify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(this.variable instanceof Expression && !(this.variable instanceof Constant)) {
			str = coeff + "(" + this.variable.stringify() + ")" + "^";
		} else str = coeff + this.variable.stringify() + "^";
		
		if(this.power instanceof Expression && !(this.power instanceof Constant)) {
			str += "(" + this.power.stringify() + ")";
		} else str+= this.power.stringify();
		return str;
		
	}

}
