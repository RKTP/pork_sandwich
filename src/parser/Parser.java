package parser;

import java.util.ArrayList;

import exception.ParseException;

public class Parser {
	public Expr parse(ArrayList<String> tokens) throws Exception {
		return takeExpr(tokens);
	}
	
	private Expr takeExpr(ArrayList<String> tokens) throws Exception {
		ArrayList<Term> terms = new ArrayList<>();
		ArrayList<String> ops = new ArrayList<>();
		
		if(tokens.isEmpty()) throw new ParseException();
		
		terms.add(takeTerm(tokens));
		while(tokens.size() > 0 && (tokens.get(0).equals("+") || tokens.get(0).equals("-"))) {
			ops.add(consume(tokens));
			if(tokens.size() == 0) throw new ParseException();
			terms.add(takeTerm(tokens));
		}
		return new Expr(terms, ops);
	}
	
	private Term takeTerm(ArrayList<String> tokens) throws Exception {
		ArrayList<Factor> factors = new ArrayList<>();
		ArrayList<String> ops = new ArrayList<>();
		
		factors.add(takeFactor(tokens));
		while(tokens.size() > 0 && (tokens.get(0).equals("*") || tokens.get(0).equals("/"))) {
			ops.add(consume(tokens));
			if(tokens.size() == 0) throw new ParseException();
			factors.add(takeFactor(tokens));
		}
		return new Term(factors, ops);
	}
	
	private Factor takeFactor(ArrayList<String> tokens) throws Exception {
		String factorToken = tokens.get(0);
		
		Factor factor;
		
		ArrayList<String> paramTokens = new ArrayList<>();
		
		if(factorToken.matches("[a-z]")) {
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.VARIABLE, paramTokens);
				
		} else if(factorToken.matches("[0-9]+\\.?[0-9]*")) {
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.VALUE, paramTokens);
			
		} else if(factorToken.equals("-") && tokens.get(1).matches("[0-9]+\\.?[0-9]*")) {
			paramTokens.add(consume(tokens));
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.VALUE, paramTokens);
			
		} else if(factorToken.matches("[a-z]+")) {
			paramTokens.add(consume(tokens));
			if(tokens.size() == 0) throw new ParseException();
			
			int blockCount = 0;
			while(blockCount != 1 || !tokens.get(0).equals(")")) {
				paramTokens.add(tokens.get(0));
				if(tokens.get(0).equals(")")) blockCount--;
				else if(tokens.get(0).equals("(")) blockCount++;
				consume(tokens);
				
				if (tokens.size() == 0) throw new ParseException();
			}
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.FUNCTION, paramTokens);
			
			
		} else if(factorToken.equals("(")) {
			int blockCount = 0;
			while(blockCount != 1 || !tokens.get(0).equals(")")) {
				paramTokens.add(tokens.get(0));
				if(tokens.get(0).equals(")")) blockCount--;
				else if(tokens.get(0).equals("(")) blockCount++;
				consume(tokens);
				
				if (tokens.size() == 0) throw new ParseException();
			}
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.EXPRESSION, paramTokens);
			
		} else if(factorToken.matches("[A-Z]+")) {
			paramTokens.add(consume(tokens));
			factor = new Factor(Factor.FactorType.PROMISE, paramTokens);
			
		} else {
			throw new ParseException();
		}
		
		return factor;
	}
	
	private String consume(ArrayList<String> tokens) throws ParseException {
		String token = tokens.get(0);
		tokens.remove(0);
		return token;
	}
}
