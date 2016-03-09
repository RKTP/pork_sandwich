package model;

import java.util.ArrayList;

public class Add extends Expression {
	private ArrayList<Expression> exp;
	
	public Add(ArrayList<Expression> exp) {
		this.exp = exp;
		
		for(Expression e : exp) {
			this.varList.addAll(e.getUsingVariables());
		}
	}
	
	public Add(ArrayList<Expression> exp, double co) {
		this.coefficient = co;
		this.exp = exp;
		
		for(Expression e : exp) {
			this.varList.addAll(e.getUsingVariables());
		}
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		ArrayList<Expression> dexp = new ArrayList<Expression>();
		
		for(Expression e : this.exp) {
			dexp.add(e.derivative(var));
		}
		
		Expression deriv = new Add(dexp, this.coefficient);
		
		return deriv;
	}

	@Override
	public Expression integrate(Variable var) throws Exception {
		ArrayList<Expression> iexp = new ArrayList<Expression>();
		
		for(Expression e : this.exp) {
			iexp.add(e.integrate(var));
		}
		
		Expression integ = new Add(iexp, this.coefficient);
		
		return integ;
	}

	@Override
	public double calc() throws Exception {
		double sum = 0;
		
		for(Expression e : this.exp) {
			sum += e.calc();
		}
		
		return sum * this.coefficient;
	}

	@Override
	public String stringify() {
		String expression = "";
		
		try {
			for(int i = 0; i < this.exp.size(); i++) {
				Expression elem = this.exp.get(i);
				if(elem instanceof Constant && elem.calc() == 0) {
					continue;
				}
				if(i > 0) {
					if(!elem.isCoeffPositive()) {
						elem.setNegativeCoeff();
						expression += " - " + elem.stringify();
						elem.setNegativeCoeff();
					} else {
						expression += " + " + elem.stringify();
					}
				} else {
					expression += elem.stringify();
				}
			}
		} catch(Exception e) {
			
		}
		
		return expression;
	}
	
}
