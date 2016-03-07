package model;

import exception.VariableAlreadyExistsException;

import java.util.*;

public class Function {
	private Map<Character, Variable> varMap;
	private Expression formula;

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
	
	public boolean isContinuous() {
		
		return false;
	}
	
	public boolean isDerivativable() {
		
		return false;
	}
}
