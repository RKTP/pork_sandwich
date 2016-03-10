package component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Model;

public class FunctionComponent extends JPanel {
	private JButton calc;
	private JButton derivative;
	private JButton Integration;
	private JButton definiteIntegration;
	private JButton plot;
	private JPanel buttonPanel;
	
	private Model model;
	
	public FunctionComponent(Model model) {
		this.model = model;
		
		buttonPanel = new JPanel();
		calc = new JButton("CALC");
		derivative = new JButton("DERIV");
		Integration = new JButton("INTEG");
		definiteIntegration = new JButton("DEF.INTEG");
		plot = new JButton("PLOT");
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(calc);
		buttonPanel.add(derivative);
		buttonPanel.add(Integration);
		buttonPanel.add(definiteIntegration);
		buttonPanel.add(plot);
		
		this.add(buttonPanel,BorderLayout.SOUTH);
	}

}
