package model;

import java.lang.Math;

public class Power extends Expression {
	Term variable;
	Expression power;
	
	public Power(Term variable, Expression power) {
		this.variable = variable;
		this.power = power;
		
	}
	
	@Override
	public Expression derivative(Variable var) {
		if(!(this.variable instanceof Expression)) {
			return new Multiply(this.power, new Power(this.variable, new Subtract(this.power, new Value(1.0))));
		} else {
			return null;//function derivative
		}
	}

	@Override
	public Expression integrate(Variable var) {
		if(!(this.variable instanceof Expression)) {
			return new Divide(new Power(this.variable, new Add(this.power, new Value(1.0))), new Add(this.power, new Value(1.0)));
		} else {
			return null;//function integration
		}
	}

	@Override
	public double calc() throws Exception {
		return Math.pow(this.variable.calc(), this.power.calc());
	}

	@Override
	public String stringify() {
		return this.variable.stringify() + "^" + this.power.stringify();
	}

}
