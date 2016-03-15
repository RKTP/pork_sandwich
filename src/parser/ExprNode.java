package parser;

import java.util.ArrayList;
import java.util.Map;

import exception.NoSuchSyntaxExistsException;
import model.Expression;
import model.Variable;

public class ExprNode {
	public enum ExprType {
		BLOCK,
		ADDSUB,
		MULTIPLY,
		DIVIDE,
		POWER,
		EXPONENTIAL,
		LOGARITHM,
		TRIGONOMETRIC,
		VARIABLE,
		CONSTANT,
		PROMISED
	}
	
	protected ExprType exprType;
	protected ExprType parentType;
	
	protected ArrayList<ExprNode> childNodes;
	protected ArrayList<String> tokens;
	
	public ExprNode(ArrayList<String> tokens, ExprType parentType) throws Exception {
		this.exprType = null;
		this.parentType = parentType;
		this.tokens = tokens;
		
		if(isInBlock(tokens)) {
			this.exprType = ExprType.BLOCK;
			ArrayList<String> inBlock = new ArrayList<>();
			inBlock.addAll(tokens.subList(1, tokens.size()-1));
			this.childNodes.add(new ExprNode(inBlock, this.exprType));
		}
		
		else if(parentType == ExprType.BLOCK) {
			this.exprType = ExprType.ADDSUB;
			this.onAddSub(tokens);
		
		} else {
			int blockCount = 0;
			for(int i = 0; i < tokens.size(); i++) {
				if(tokens.get(i).equals("(")) blockCount++;
				else if(tokens.get(i).equals(")")) blockCount--;
				else if(blockCount == 0 && tokens.get(i).equals("*")) {
					this.exprType = ExprType.MULTIPLY;
					this.onMultDiv(tokens);
					break;
				} else if(blockCount == 0 && tokens.get(i).equals("/")) {
					this.exprType = ExprType.DIVIDE;
					this.onMultDiv(tokens);
					break;
				}
			}
			
			blockCount = 0;
			if(this.exprType == null) {
				for(int i = 0; i < tokens.size(); i++) {
					if(tokens.get(i).equals("(")) blockCount++;
					else if(tokens.get(i).equals(")")) blockCount--;
					else if(blockCount == 0 && tokens.get(i).equals("^")) {
						this.exprType = ExprType.MULTIPLY;
						this.onMultDiv(tokens);
						break;
					}
				}
			}
		}
	}
	
	private boolean isInBlock(ArrayList<String> tokens) {
		int blockCount = 0;
		if(tokens.get(0) != "(") return false;
		
		for(String e : tokens) {
			if(e.equals("(")) {
				blockCount++;
			} else if(e.equals(")")) {
				blockCount--;
				if(blockCount == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void onAddSub(ArrayList<String> tokens) throws Exception {
		int blockCount = 0;
		ArrayList<String> childTokens = new ArrayList<>();
		
		for(int i = 0; i < tokens.size(); i++) {
			if(tokens.get(i).equals("(")) {
				blockCount++;
				childTokens.add(tokens.get(i));
				
			} else if(tokens.get(i).equals(")")) {
				blockCount--;
				childTokens.add(tokens.get(i));
				
			}else if(blockCount == 0 && tokens.get(i).matches("[+\\-]")) {
				this.childNodes.add(new ExprNode(childTokens, this.exprType));
				childTokens = new ArrayList<>();
				
			} else {
				childTokens.add(tokens.get(i));
			}
		}
		this.childNodes.add(new ExprNode(childTokens, this.exprType));
	}
	
	private void onMultDiv(ArrayList<String> tokens) throws Exception {
		int blockCount = 0;
		ArrayList<String> childTokens = new ArrayList<>();
		
		for(int i = 0; i < tokens.size(); i++) {
			if(tokens.get(i).equals("(")) {
				blockCount++;
				childTokens.add(tokens.get(i));
				
			} else if(tokens.get(i).equals(")")) {
				blockCount--;
				childTokens.add(tokens.get(i));
				
			} else if(blockCount == 0 && tokens.get(i).matches("[*/]")) {
				this.childNodes.add(new ExprNode(childTokens, this.exprType));
				childTokens = new ArrayList<>();
				childTokens.addAll(tokens.subList(i+1, tokens.size()));
				this.childNodes.add(new ExprNode(childTokens,this.exprType));
			}
		}
	}

	private void onPowExp(ArrayList<String> tokens) throws Exception {
		
	}
	
	private void onLogarithm(ArrayList<String> tokens) throws Exception {
		
	}
	
	private void onTrigonometric(ArrayList<String> tokens) throws Exception {
		
	}
	

	
	public Expression buildUpExpression(Map<Character, Variable> varMap) throws Exception {
		return null;
	}
}
