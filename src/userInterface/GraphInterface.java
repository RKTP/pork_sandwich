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
		super("2D Graph Plot");
		this.function = func;
		this.under = underbound;
		this.upper = upperbound;
		
		JPanel chartPanel = createChartPanel();
		
		setSize(800,600);
		add(chartPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
