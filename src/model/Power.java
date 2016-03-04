package model;

import java.lang.Math;
import java.util.ArrayList;

import exception.NotImplementedException;

public class Power extends Expression {
	Term variable;
	Expression power;
	
	public Power(Term variable, Expression power) {
		this.variable = variable;
		this.power = power;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else {
			try {
				throw new NotImplementedException();
			} catch (NotImplementedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Power(Term variable, Expression power, double co) {
		this.variable = variable;
		this.power = power;
		this.coefficient = co;
		
		if(variable instanceof Variable) this.varList.add((Variable)variable);
		else if(variable instanceof Expression) this.varList.addAll(((Expression)variable).getUsingVariables());
		else {
			try {
				throw new NotImplementedException();
			} catch (NotImplementedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Expression derivative(Variable var) {
		if(!(this.variable instanceof Expression)) {
			ArrayList<Expression> exp = new ArrayList<Expression>();
			exp.add(this.power);
			exp.add(new Constant(-1.0));
			
			return new Multiply(this.power, new Power(this.variable, new Add(exp)), this.coefficient);
		} else {
			return null;//function derivative
		}
	}

	@Override
	public Expression integrate(Variable var) {
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
		return this.variable.stringify() + "^" + this.power.stringify();
	}

}
