package model;

import exception.VariableAlreadyExistsException;

import java.util.*;

public class Function {
	private Map<Character, Variable> varMap;
	private Expression formula;

	public Function(Set<Variable> varList, Expression formula) throws Exception {
		this.formula = formula;
		this.varMap = new HashMap<>();
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

	public void setVariableValue(char key, double value) {
		Variable var = this.varMap.get(key);
		var.setValue(value);
	}

	public ArrayList<Expression> getDerivative() throws Exception {
		Set<Character> keySet = this.varMap.keySet();
		Iterator<Character> it = keySet.iterator();
		ArrayList<Expression> derivativeList = new ArrayList<>();

		for(;it.hasNext();) {
			Character key = it.next();
			derivativeList.add(this.formula.derivative(this.varMap.get(key)));
		}

		return derivativeList;
	}

	public Expression getDerivative(Variable var) throws Exception {
		return this.formula.derivative(var);
	}

	public double calc() throws Exception {
		return this.formula.calc();
	}
}
