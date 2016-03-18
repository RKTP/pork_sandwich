package userInterface;

import java.awt.BorderLayout;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

import model.Function;

public class GraphInterface extends JFrame {
	private Function function;
	private double under, upper;
	public GraphInterface(Function func, double underbound, double upperbound) {
		super(func.getFunctionName());
		this.function = func;
		this.under = underbound;
		this.upper = upperbound;
		
		JPanel chartPanel = createChartPanel();
		JLabel formulaLabel = null;
		try {
			formulaLabel = new JLabel(this.function.stringify());
		} catch (Exception e) {
			e.printStackTrace();
		}
		formulaLabel.setHorizontalAlignment(JLabel.CENTER);
		
		setSize(800,600);
		this.add(formulaLabel, BorderLayout.NORTH);
		this.add(chartPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private JPanel createChartPanel() {
		String chartTitle = "";
		String xAxisLabel = this.function.getVarNames().iterator().next().toString();
		String yAxisLabel = "f(" + xAxisLabel + ")";
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		try {
			dataset.addSeries(this.function.getPlotSample(this.under, this.upper, 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
		
		return new ChartPanel(chart);
	}

}
