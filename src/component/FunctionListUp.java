package component;

import java.util.ArrayList;

import javax.swing.JPanel;
import model.Function;

public class FunctionListUp extends JPanel {
	ArrayList<Function> functions;
	public FunctionListUp(ArrayList<Function> functions) {
		this.functions = functions;
	}
	
	public FunctionListUp(Function function) {
		this.functions = new ArrayList<>();
		this.functions.add(function);
	}
}
