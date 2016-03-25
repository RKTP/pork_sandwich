package model;

import java.lang.Math;
import java.util.ArrayList;

import exception.CannotIntegrateException;
import exception.NoSuchSyntaxExistsException;

public class Power extends Expression {
	Expression variable;
	Expression power;
	
	public Power(Expression variable, Expression power) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = power;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Expression variable, double power) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = new Constant(power);
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Expression variable, double power, double co) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = new Constant(power);
		this.coefficient = co;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Power(Expression variable, Expression power, double co) throws NoSuchSyntaxExistsException {
		this.variable = variable;
		this.power = power;
		this.coefficient = co;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else throw new NoSuchSyntaxExistsException();
	}
	
	public Expression getVarEx() {
		return this.variable;
	}
	
	public double getPow() throws Exception {
		return this.power.calc();
	}
	
	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(this.power);
		exp.add(new Constant(-1.0));
		
		Expression newPow = new AddSub(exp);

		if(this.variable instanceof Variable) {
			return new Multiply(this.power, new Power(this.variable, newPow, this.coefficient));
		} else {
			return new Multiply(new Multiply(this.power, new Power(this.variable, new AddSub(exp)), this.coefficient), ((Expression) this.variable).derivative(var));
		}
	}

	@Override
	public Expression integrate(Variable var) throws NoSuchSyntaxExistsException, CannotIntegrateException {
		if(!this.varList.contains(var)) {
			throw new CannotIntegrateException();
		}
		
		if(this.variable instanceof Expression) {
			ArrayList<Expression> exp = new ArrayList<Expression>();
			exp.add(this.power);
			exp.add(new Constant(1.0));
			
			return new Divide(new Power(this.variable, new AddSub(exp)), new AddSub(exp), this.coefficient);
		} else throw new CannotIntegrateException();
	}

	@Override
	public double calc() throws Exception {
		return Math.pow(this.variable.calc(), this.power.calc()) * this.coefficient;
	}

	@Override
	public String stringify() throws Exception {
		String coeff = "";
		String str = "";
		if(this.coefficient != 1) {
			coeff = this.coeffToString() + "*";
		}
		if(this.power.calc() == 1.0) return coeff + this.variable.stringify();
		
		if(!(this.variable instanceof Variable)) {
			str = coeff + "pow(" + "(" + this.variable.stringify() + ")" + ",";
		} else str = coeff + "pow(" +  this.variable.stringify() + ",";
		
		if(!(this.power instanceof Constant)) {
			str += "(" + this.power.stringify() + "))";
		} else str+= this.power.stringify() + ")";
		return str;
		
	}
	
	public ArrayList<Expression> canonicalize() {
		ArrayList<Expression> exprs = new ArrayList<>();
		try {
			if(this.power.calc() == 1) {
				exprs.add(this.variable);
			} else {
				exprs.add(this);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exprs;
	}

}
