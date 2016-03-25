package model;

import java.util.ArrayList;

import exception.CannotIntegrateException;
import exception.NoSuchSyntaxExistsException;

public class Multiply extends Expression {
	private Expression left, right;

	public Multiply(Expression left, Expression right) throws Exception {
		this.left = left;
		this.right = right;
		
		this.varList.addAll(this.left.getUsingVariables());
		this.varList.addAll(this.right.getUsingVariables());
		
		ArrayList<Expression> exprs = new ArrayList<>();
		exprs.add(this.left);
		exprs.add(this.right);
		
		exprs.sort(new MultiExprComparator());
		
		this.left = exprs.get(0);
		this.right = exprs.get(1);
	}
	
	public Multiply(Expression left, Expression right, double co) {
		this.left = left;
		this.right = right;
		this.coefficient = co;
		
		this.varList.addAll(this.left.getUsingVariables());
		this.varList.addAll(this.right.getUsingVariables());
		
		ArrayList<Expression> exprs = new ArrayList<>();
		exprs.add(this.left);
		exprs.add(this.right);
		
		exprs.sort(new MultiExprComparator());
		
		this.left = exprs.get(0);
		this.right = exprs.get(1);
	}

	public Expression getLeft() {
		return this.left;
	}
	
	public Expression getRight() {
		return this.right;
	}

	@Override
	public Expression derivative(Variable var) throws Exception {
		if(!this.varList.contains(var)) {
			return new Constant(0.0);
		}

		Expression left = new Multiply(this.left.derivative(var), this.right, this.coefficient);
		Expression right = new Multiply(this.left, this.right.derivative(var), this.coefficient);
		
		ArrayList<Expression> exp = new ArrayList<Expression>();
		exp.add(left);
		exp.add(right);
		
		Expression deriv = new AddSub(exp);
		return deriv;
	}

	@Override
	public Expression integrate(Variable var) throws CannotIntegrateException {
		throw new CannotIntegrateException();
	}

	@Override
	public double calc() throws Exception {
		return this.left.calc() * this.right.calc() * this.coefficient;
	}

	@Override
	public String stringify() throws Exception {
		String leftString, rightString;
		
		try {
				if(this.left instanceof Constant && this.right instanceof Constant) {
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
		
		String coeff = "";
		if(Math.abs(this.coefficient) != 1) {
			coeff = this.coeffToString() + "*";
		}
		
		return coeff + leftString + " * " + rightString;
	}

	@Override
	public ArrayList<Expression> canonicalize() {
		ArrayList<Expression> leftExprs = this.left.canonicalize();
		ArrayList<Expression> rightExprs = this.right.canonicalize();
		ArrayList<Expression> exprs = new ArrayList<Expression>();
		//multiply canonicalizing still on WORK - constant multiplying, 
		
		for(int i = 0; i < leftExprs.size(); i++) {
			for(int j = 0; j < rightExprs.size(); j++) {
				Expression lEx = leftExprs.get(i), rEx = rightExprs.get(j);
				try {
					if((lEx instanceof Constant && lEx.calc() == 0) ||
							(rEx instanceof Constant && rEx.calc() == 0)) {
						exprs.add(new Constant(0));
						continue;
					} else if(lEx instanceof Constant && Math.abs(lEx.calc()) == 1) {
						if(lEx.calc() == 1) {
							exprs.add(rEx);
						} else {
							rEx.setNegativeCoeff();
							exprs.add(rEx);
						}
						continue;
					} else if(lEx instanceof Constant && rEx instanceof Constant) {
						exprs.add(new Constant(lEx.calc() * rEx.calc()));
						continue;
					} else if(lEx instanceof Power) {
							if(rEx instanceof Power && ((Power) rEx).getVarEx() == ((Power) lEx).getVarEx()) {
								exprs.add(new Power(((Power) lEx).getVarEx(),
										((Power)lEx).getPow() + ((Power)rEx).getPow(),
										lEx.getCoeff() * rEx.getCoeff()));
								continue;
							} else if(rEx instanceof Variable && rEx == ((Power) lEx).getVarEx()) {
								exprs.add(new Power(((Power) lEx).getVarEx(),
										((Power) lEx).getPow() + 1,
										lEx.getCoeff() * rEx.getCoeff()));
								continue;
							}
					} else if(lEx instanceof Variable) {
						if(rEx instanceof Power && ((Power) rEx).getVarEx() == lEx) {
							exprs.add(new Power(lEx,
									1 + ((Power)rEx).getPow(),
									lEx.getCoeff() * rEx.getCoeff()));
							continue;
								
						} else if(rEx instanceof Variable && rEx == lEx) {
							exprs.add(new Power(lEx,
									2,
									lEx.getCoeff() * rEx.getCoeff()));
							continue;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exprs.add(new Multiply(leftExprs.get(i), rightExprs.get(j), this.coefficient));
			}
		}
		
		exprs.sort(new util.ExprComparator());
		
		return exprs;
	}

	private class MultiExprComparator extends util.ExprComparator {
		@Override
		protected int typeOrder(Expression obj) {
			if(obj instanceof Power || obj instanceof Variable) {
				return 0;
			} else if(obj instanceof Exponential) {
				return 1;
			} else if(obj instanceof Logarithm) {
				return 2;
			} else if(obj instanceof Sine) {
				return 3;
			} else if(obj instanceof Cosine) {
				return 4;
			} else if(obj instanceof Tangent) {
				return 5;
			} else if(obj instanceof Cosecant) {
				return 6;
			} else if(obj instanceof Secant) {
				return 7;
			} else if(obj instanceof Cotangent) {
				return 8;
			} else if(obj instanceof Multiply) {
				return typeOrder(((Multiply)obj).getRight());
			} else if(obj instanceof Divide) {
				return typeOrder(((Divide)obj).getRight());
			} else {
				return -1;
			}
		}
		
		@Override
		protected double powerOrder(Expression obj) throws Exception {
			if(obj instanceof Power) {
				return -((Power)obj).getPow();
			} else if(obj instanceof Variable) {
				return -1.0;
			} else {
				if(obj instanceof Multiply) {
					return powerOrder(((Multiply) obj).getRight());
				} else if(obj instanceof Divide) {
					return powerOrder(((Divide) obj).getRight());
				} else return 0;
			}
		}
	}
}
