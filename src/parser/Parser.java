package parser;

import java.util.ArrayList;

import exception.NoSuchSyntaxExistsException;

public class Parser {
	public ExprNode parse(ArrayList<String> tokens) throws Exception {
		ExprNode root = new ExprNode(tokens, ExprNode.ExprType.BLOCK);
		return root;
	}
}
