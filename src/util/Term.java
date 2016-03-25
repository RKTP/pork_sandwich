package util;

import java.util.ArrayList;
import java.util.Map;

import model.Divide;
import model.Expression;
import model.Multiply;
import model.Variable;

public class Term {
	private ArrayList<Factor> factors;
	private ArrayList<String> ops;
	
	public Term(ArrayList<Factor> factors, ArrayList<String> ops) {
		this.factors = factors;
		this.ops = ops;
	
	}
	
	public Expression functionize(Map<Character, Variable> varMap) throws Exception {
		Expression result = this.factors.get(0).functionize(varMap);
		if(this.factors.size() <= 1) {
			return result;
		}


		for(int i = 0; i < this.ops.size(); i++) {
			String op = this.ops.get(i);
			if(op.equals("*")) {
				result = new Multiply(result, factors.get(i+1).functionize(varMap));
			} else {
				result = new Divide(result, factors.get(i+1).functionize(varMap));
			}
		}
		return result;
	}
	
}
