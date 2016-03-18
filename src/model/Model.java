package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Model {
	private Map<String,Function> functionList;
	
	public Model() {
		this.functionList = new HashMap<>();
	}
	
	public void addFunction(Function func) {
		this.functionList.put(func.getFunctionName(), func);
	}
	
	public Function getFunctionByName(String funcName) {
		return this.functionList.get(funcName);
	}
	
	public Vector<String> getFunctionNames() {
		Vector<String> nameVec = new Vector<>();
		for(int i =  0; i < this.functionList.size(); i++) {
			nameVec.addAll(this.functionList.keySet());
		}
		return nameVec;
	}
}
