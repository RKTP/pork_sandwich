package parser;

import java.util.ArrayList;
import java.util.Map;

import model.AddSub;
import model.Expression;
import model.Variable;

public class Expr {
	ArrayList<Term> terms;
	ArrayList<String> ops;
	
	public Expr(ArrayList<Term> terms, ArrayList<String> ops) {
		this.terms = terms;
		this.ops = ops;
	}
	
	public Expression functionize(Map<Character, Variable> varMap) throws Exception {
		ArrayList<Expression> addsubExpr = new ArrayList<>();
		
		addsubExpr.add(this.terms.get(0).functionize(varMap));
		
		for(int i = 0; i < this.ops.size(); i++) {
			Expression termExpr = this.terms.get(i+1).functionize(varMap);
			if(this.ops.get(i).equals("-")) {
				termExpr.setNegativeCoeff();
			}
			
			addsubExpr.add(termExpr);
		}
		
		return new AddSub(addsubExpr);
	}
}
