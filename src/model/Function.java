package model;

import exception.*;

import java.util.*;

import org.jfree.data.xy.XYSeries;

public class Function {
	private Map<Character, Variable> varMap;
	private Expression formula;
	private final double interval = 0.000000001;

	public Function(Expression formula) throws Exception {
		this.formula = formula;
		this.varMap = new HashMap<>();
		Set<Variable> varList = formula.getUsingVariables();
		Iterator<Variable> it = varList.iterator();

		for(;it.hasNext();) {
			Variable v = it.next();
			if(this.varMap.containsKey(v.getName())) {
				throw new VariableAlreadyExistsException();
			} else {
				this.varMap.put(v.getName(),v);
			}
		}
	}

	public void setVariableValue(char varName, double value) {
		Variable var = this.varMap.get(varName);
		var.setValue(value);
	}

	public ArrayList<Function> getDerivative() throws Exception {
		Set<Character> keySet = this.varMap.keySet();
		Iterator<Character> it = keySet.iterator();
		ArrayList<Function> derivativeList = new ArrayList<>();

		for(;it.hasNext();) {
			Character key = it.next();
			Function dFunction = new Function(this.formula.derivative(this.varMap.get(key)));
			derivativeList.add(dFunction);
		}

		return derivativeList;
	}

	public Function getDerivative(char varName) throws Exception {
		Function dFunction = new Function(this.formula.derivative(this.varMap.get(varName)));
		return dFunction;
	}

	public double calc() throws Exception {
		return this.formula.calc();
	}
	
	public boolean isContinuous(double value) throws Exception {
		if(this.varMap.size() > 1) {
			throw new NoSuchSyntaxExistsException();
		} else if(this.varMap.size() == 0) {
			throw new NumberOfVariableMismatchException();
		}
		

		char varName = varMap.keySet().iterator().next();
		Variable var = varMap.get(varName);
		double midValue = this.formula.calc();
		double diff = this.formula.derivative(var).calc();
		double range = Math.abs(diff * this.interval * 2.0);
		
		double varValue = var.calc();
		
		var.setValue(varValue - this.interval);
		double leftValue = this.formula.calc();
		
		var.setValue(varValue + this.interval);
		double rightValue = this.formula.calc();
		
		var.setValue(varValue);
		if(Math.abs(rightValue - midValue) < range && Math.abs(leftValue - midValue) < range) {
			return true;
		} else return false;

	}
	
	public boolean isDerivativable(double value) throws Exception {
		if(this.varMap.size() > 1) {
			throw new NoSuchSyntaxExistsException();
		} else if(this.varMap.size() == 0) {
			throw new NumberOfVariableMismatchException();
		}
		
		if(!this.isContinuous(value)) return false;
		
		char varName = varMap.keySet().iterator().next();
		Variable var = varMap.get(varName);
		double varValue = var.calc();
		
		double midDiff = this.formula.derivative(var).calc();
		double ddiff = this.formula.derivative(var).derivative(var).calc();
		double range = ddiff * this.interval * 2.0;
		
		var.setValue(varValue - this.interval);
		double leftDiff = this.formula.derivative(var).calc();
		
		var.setValue(varValue + this.interval);
		double rightDIff = this.formula.derivative(var).calc();
		
		var.setValue(varValue);
		if(Math.abs(rightDIff - midDiff) < range && Math.abs(leftDiff - midDiff) < range) {
			return true;
		} else return false;
	}
	
	/*
	 * Mock String Builder
	 */
	public String stringify() {
		String str = this.formula.stringify();
		return str;
	}
	
	public Set<Character> getVarNames() {
		return this.varMap.keySet();
	}
	
	public XYSeries getPlotSample(double underbound, double upperbound, int pxl) throws Exception {
		if(this.varMap.size() != 1) {
			throw new NumberOfVariableMismatchException();
		}
		
		XYSeries plotData = new XYSeries("function");
		double step = (upperbound - underbound)/pxl;
		double currentPos = underbound;
		char varName = varMap.keySet().iterator().next();
		Variable var = varMap.get(varName);
		
		while(currentPos <= upperbound) {
			var.setValue(currentPos);
			plotData.add(currentPos, this.calc());
			currentPos += step;
		}
		
		return plotData;
	}
}
