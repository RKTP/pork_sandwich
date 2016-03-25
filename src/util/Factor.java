package util;

import java.util.ArrayList;
import java.util.Map;

import exception.ParseException;
import model.Constant;
import model.Cosecant;
import model.Cosine;
import model.Cotangent;
import model.Euler;
import model.Exponential;
import model.Expression;
import model.Logarithm;
import model.Pi;
import model.Power;
import model.Secant;
import model.Sine;
import model.Tangent;
import model.Variable;

public class Factor {
	public enum FactorType {
		VALUE,
		EXPRESSION,
		FUNCTION,
		VARIABLE,
		PROMISE
	}
	
	private FactorType factorType;
	private ArrayList<String> tokens;
	
	public Factor(FactorType type, ArrayList<String> tokens) throws Exception {
		this.factorType = type;
		this.tokens = tokens;
	}
	
	public Expression functionize(Map<Character, Variable> varMap) throws Exception {
		Expression result = null;
		if(this.factorType == FactorType.VALUE) {
			if(this.tokens.size() == 1) {
				result = new Constant(Double.parseDouble(this.tokens.get(0)));
			} else if(this.tokens.size() == 2) {
				result = new Constant(Double.parseDouble(this.tokens.get(0) + this.tokens.get(1)));
			} else throw new ParseException();
			
		} else if(this.factorType == FactorType.EXPRESSION) {
			ArrayList<String> exprTokens = new ArrayList<String>();
			exprTokens.addAll(this.tokens.subList(1, this.tokens.size()-1));
			result = new Parser().parse(exprTokens).functionize(varMap);
			
		} else if(this.factorType == FactorType.FUNCTION) {
			Parser parser = new Parser();
			String funcName = tokens.get(0);
			if(funcName.equals("pow")) {
				ArrayList<String> leftToken = new ArrayList<>(), rightToken = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(",")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					leftToken.add(tokens.get(i++));
				}
				i++;
				while (!tokens.get(i).equals(")") || blockCount != 1) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					rightToken.add(tokens.get(i++));
				}
				result = new Power(parser.parse(leftToken).functionize(varMap), parser.parse(rightToken).functionize(varMap));
				
				
			} else if(funcName.equals("log")) {
				ArrayList<String> leftToken = new ArrayList<>(), rightToken = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(",")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					leftToken.add(tokens.get(i++));
				}
				i++;
				while (!tokens.get(i).equals(")") || blockCount != 1) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					rightToken.add(tokens.get(i++));
				}
				result = new Logarithm(parser.parse(leftToken).functionize(varMap), parser.parse(rightToken).functionize(varMap));
				
			} else if(funcName.equals("exp")) {
				ArrayList<String> leftToken = new ArrayList<>(), rightToken = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(",")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					leftToken.add(tokens.get(i++));
				}
				i++;
				while (!tokens.get(i).equals(")") || blockCount != 1) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					rightToken.add(tokens.get(i++));
				}

				result = new Exponential(parser.parse(leftToken).functionize(varMap), parser.parse(rightToken).functionize(varMap));
				
			} else if(funcName.equals("sin")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Sine(parser.parse(token).functionize(varMap));
				
			} else if(funcName.equals("cos")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Cosine(parser.parse(token).functionize(varMap));
				
			} else if(funcName.equals("tan")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Tangent(parser.parse(token).functionize(varMap));
				
			} else if(funcName.equals("sec")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Secant(parser.parse(token).functionize(varMap));
				
			} else if(funcName.equals("csc")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Cosecant(parser.parse(token).functionize(varMap));
				
			} else if(funcName.equals("cot")) {
				ArrayList<String> token = new ArrayList<>();
				int blockCount = 1;
				int i = 2;
				while(blockCount != 1 || !tokens.get(i).equals(")")) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					token.add(tokens.get(i++));
				}
				result = new Cotangent(parser.parse(token).functionize(varMap));
				
			} else {
				throw new ParseException();
			}
			
		} else if(this.factorType == FactorType.VARIABLE) {
			String token = this.tokens.get(0);
			if(varMap.containsKey(token.charAt(0))) {
				result = varMap.get(token.charAt(0));
			} else {
				Variable newVar = new Variable(token.charAt(0));
				varMap.put(token.charAt(0), newVar);
				result = newVar;
			}
			
		} else {
			String token = this.tokens.get(0);
			if(token.equals("E")) {
				result = new Euler();
				
			} else if(token.equals("PI")) {
				result = new Pi();
				
			} else {
				throw new ParseException();
			}
		}
		
		return canonicalize(result);
	}

	/*
	 * Somehow needed...maybe.
	 */
	private Expression canonicalize(Expression origin) {
		return origin;
	}
}
