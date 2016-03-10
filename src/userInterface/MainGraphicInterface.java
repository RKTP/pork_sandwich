package userInterface;

import component.*;
import javax.swing.*;
import model.Model;

public class MainGraphicInterface extends JFrame {
	public static final String DEFAULT_TITLE = "APP";
	
	public static final int FRAME_HEIGHT = 500;
	public static final int FRAME_WIDTH = 800;
	
	private FunctionComponent functionalPart;
	private FunctionVisualization labelPart;
	private Model model;
	private JPanel mainPanel;
	
	public MainGraphicInterface(Model model) {
		this.model = model;
		this.mainPanel = new JPanel();
		mainPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		functionalPart = new FunctionComponent(this.model);
		
		mainPanel.add(functionalPart);
		//mainPanel.add(labelPart);
		
		this.add(mainPanel);
		
	}
}
