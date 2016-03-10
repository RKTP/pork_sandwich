package model;

import java.util.ArrayList;
import java.util.Vector;

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
	
	public Vector<String> getFunctionNames() {
		Vector<String> nameVec = new Vector<>();
		for(int i =  0; i < this.functionList.size(); i++) {
			nameVec.add(this.functionList.get(i).getFunctionName());
		}
		return nameVec;
	}
}
