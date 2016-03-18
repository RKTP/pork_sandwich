package component;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Function;

public class FunctionVisualization extends JPanel {
	private Function func;
	private JLabel textLabel;
	
	public FunctionVisualization(Function function) {
		this.func = function;
		try {
			textLabel = new JLabel(this.func.getFunctionName() + " : " + this.func.stringify());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.add(textLabel,BorderLayout.CENTER);
	}
}
