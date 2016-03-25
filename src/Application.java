import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import exception.NumberOfVariableMismatchException;
import model.*;
import userInterface.GraphInterface;
import userInterface.MainGraphicInterface;

public class Application {

	public static void main(String[] args) throws Exception {
		while(true) {
			String formula;
			Scanner ci = new Scanner(System.in);
			formula = ci.nextLine();
			double under, upper;
			char varName = ci.next().charAt(0);
			under = ci.nextDouble();
			upper = ci.nextDouble();
			Function func = new Function(formula,"nightstand");
			showDemo(func, under, upper);
			try {
				Function dfunc = func.getDerivative(varName);
				showDemo(dfunc, under, upper);
				Function dsample = new Function(func.getDerivative(varName).stringify());
				showDemo(dsample, under, upper);
				//showDemo(dfunc.getIntegra(varName), under,upper);
				//System.out.println(func.getIntegraInRange(varName, under, upper));
				//if(func.getVarNames().size() == 1) {
				//	showDemo(func.getIntegra(varName), under, upper);
				//}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void showDemo(Function func) throws Exception {
		if(func.getVarNames().size()==1) {
			new GraphInterface(func,0,2).setVisible(true);
		} else {
			throw new NumberOfVariableMismatchException();
		}
	}
	
	private static void showDemo(Function func, double under, double upper) throws Exception {
		if(func.getVarNames().size()==1) {
			new GraphInterface(func,under,upper).setVisible(true);
		} else {
			throw new NumberOfVariableMismatchException();
		}
	}

}
