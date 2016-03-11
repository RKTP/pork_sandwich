package model;

import java.lang.Math;

import exception.OutOfRangeException;

public class Logarithm extends Expression {
	Expression base;
	Expression param;
	
	public Logarithm(Expression base, Expression param) {
		this.base = base;
		this.param = param;
		
		this.varList.addAll(this.base.getUsingVariables());
		this.varList.addAll(this.param.getUsingVariables());
	}
	
	public Logarithm(Expression base, Expression param, double co) {
		this.base = base;
		this.param = param;
		this.coefficient = co;
	
		this.varList.addAll(this.base.getUsingVariables());
		this.varList.addAll(this.param.getUsingVariables());
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		Expression divider = new Multiply(this.param, new Logarithm(new Euler(), this.base));
		return new Multiply(new Divide(new Constant(1.0), divider, this.coefficient), this.param.derivative(var));
	}

	@Override
	public Expression integrate(Variable var) {
		return null;
	}

	@Override
	public double calc() throws Exception {
		if(this.param.calc()<= 0 || this.base.calc() <= 0) {
			throw new OutOfRangeException();
		}
		return Math.log(this.param.calc()) / Math.log(this.base.calc()) * this.coefficient;
	}

	@Override
	public String stringify() {
		String coeff = "";
		if(this.coefficient == -1) {
			coeff = "-";
		} else if(this.coefficient != 1) {
			coeff = this.coeffToString();
		}
		
		return coeff + "log(" + base.stringify() + " | " + param.stringify() + ")";
	}

}
