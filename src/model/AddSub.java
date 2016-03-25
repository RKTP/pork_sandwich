package model;

import java.util.ArrayList;

import exception.CannotIntegrateException;

public class AddSub extends Expression {
	private ArrayList<Expression> exp;
	
	public AddSub(ArrayList<Expression> exps) {
		this.exp = new ArrayList<>();
		double constantSum = 0;
		
		for(Expression e : exps) {
			if(e instanceof Constant && !(e instanceof Promise)) {
				try {
					constantSum += e.calc();
					continue;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if(e instanceof AddSub) {
				this.exp.addAll(((AddSub) e).getExprList());
				this.varList.addAll(e.getUsingVariables());
			} else {
				this.exp.add(e);
				this.varList.addAll(e.getUsingVariables());
			}
		}
		if(constantSum != 0 || exp.isEmpty()) {
			exp.add(new Constant(constantSum));
		}
		
		exp.sort(new util.ExprComparator());
	}
	
	public AddSub(ArrayList<Expression> exps, double co) {
		this.coefficient = co;
		
		this.exp = new ArrayList<>();
		double constantSum = 0;
		
		for(Expression e : exps) {
			if(e instanceof Constant && !(e instanceof Promise)) {
				try {
					constantSum += e.calc();
					continue;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if(e instanceof AddSub) {
				this.exp.addAll(((AddSub) e).getExprList());
				this.varList.addAll(e.getUsingVariables());
			} else {
				this.exp.add(e);
				this.varList.addAll(e.getUsingVariables());
			}
		}
		if(constantSum != 0 || exp.isEmpty()) {
			exp.add(new Constant(constantSum));
		}
		
		exp.sort(new util.ExprComparator());
	}

	
	public ArrayList<Expression> getExprList() {
		return this.exp;
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
			return new Multiply(this,var);
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
					if(elem.isCoeffPositive()) {
						expression += elem.stringify();
					} else {
						expression += "-" + elem.stringify();
					}
				}
			}
		} catch(Exception e) {
			
		}
		
		return expression;
	}

	@Override
	public ArrayList<Expression> canonicalize() {
		ArrayList<Expression> exprs = new ArrayList<Expression>();
		
		for(Expression e : this.exp) {
			try {
				if(e instanceof Constant && e.calc() == 0) {
					continue;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			exprs.addAll(e.canonicalize());
		}
		
		exprs.sort(new util.ExprComparator());

		for(int i = 0; i < exprs.size()-1; i++) {
			if(isSameCategory(exprs.get(i), exprs.get(i+1))) {
				try {
					double coeffSum = 0;
					Expression toRight = null;
					if(exprs.get(i) instanceof Multiply && ((Multiply)exprs.get(i)).getLeft() instanceof Constant) {
						coeffSum += ((Multiply)exprs.get(i)).getLeft().calc();
						toRight = ((Multiply)exprs.get(i)).getRight();
					} else {
						coeffSum += exprs.get(i).getCoeff();
						toRight = exprs.get(i);
					}
					
					if(exprs.get(i+1) instanceof Multiply && ((Multiply)exprs.get(i+1)).getLeft() instanceof Constant) {
						coeffSum += ((Multiply)exprs.get(i+1)).getLeft().calc();
					} else coeffSum += exprs.get(i).getCoeff();
					
					Expression newElem = new Multiply(new Constant(coeffSum), toRight);
					exprs.add(i, newElem);
					exprs.remove(i+1);
					exprs.remove(i+1);
					i--;
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return exprs;
	}
	
	private boolean isSameCategory(Expression e1, Expression e2) {
		Expression targetEx1 = e1, targetEx2 = e2;
		
		if(targetEx1 instanceof Multiply) {
			targetEx1 = ((Multiply)targetEx1).getRight();
		}
		
		if(targetEx2 instanceof Multiply) {
			targetEx2 = ((Multiply)targetEx2).getRight();
		}
		try {
			if(targetEx1 instanceof Power) {
				if(targetEx2 instanceof Power && 
						((Power) targetEx1).getPow() == ((Power) targetEx2).getPow() && 
						((Power) targetEx1).getVarEx() == ((Power) targetEx2).getVarEx()) {
					return true;
				}
			} else if(targetEx1 instanceof Variable) {
				if(targetEx2 instanceof Variable && targetEx1 == targetEx2) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
