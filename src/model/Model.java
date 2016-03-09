package model;

import java.util.ArrayList;

public class Model {
	private ArrayList<Function> functionList;
	
	public Model() {
		this.functionList = new ArrayList<>();
	}
	
	public void addFunction(Function func) {
		this.functionList.add(func);
	}
	
	public Function getFunction(int index) {
		return this.functionList.get(index);
	}
}
