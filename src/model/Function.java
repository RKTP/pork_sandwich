package model;

import java.util.ArrayList;

public class Function {
	private ArrayList<Variable> varList;
	private Expression formula;

	/*
	 * mock constructor
	 */
	public Function(ArrayList<Variable> varList, Expression formula) {
		this.varList = varList;
		this.formula = formula;
	}
}
