package model;

import java.util.ArrayList;

public class AddSub extends Expression {
	private ArrayList<Expression> exp;
	
	public AddSub(ArrayList<Expression> exps) {
		//this.exp = exp;
		this.exp = new ArrayList<>();
		double constantSum = 0;
		
		for(Expression e : exps) {
			if(e instanceof Constant) {
				try {
					constantSum += e.calc();
					continue;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			this.exp.add(e);
			this.varList.addAll(e.getUsingVariables());
		}
		if(constantSum != 0 || exp.isEmpty()) {
			exp.add(new Constant(constantSum));
		}
	}
	
	public AddSub(ArrayList<Expression> exp, double co) {
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
		
		Expression deriv = new AddSub(dexp, this.coefficient);
		
		return deriv;
	}

	@Override
	public Expression integrate(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}
		
		ArrayList<Expression> iexp = new ArrayList<Expression>();
		
		for(Expression e : this.exp) {
			iexp.add(e.integrate(var));
		}
		
		Expression integ = new AddSub(iexp, this.coefficient);
		
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
				if(elem instanceof Constant && elem.stringify().equals("0") && this.exp.size()>1) {
					continue;
				}
				if(!expression.equals("")) {
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
