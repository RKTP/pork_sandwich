package util;

import java.util.Comparator;

import model.*;

public class ExprComparator implements Comparator<Expression> {

	@Override
	public int compare(Expression o1, Expression o2) {
		double left, right;
		int result = 0;
		Expression leftObj, rightObj;

		leftObj = o1;
		rightObj = o2;
		
		left = typeOrder(leftObj);
		right = typeOrder(rightObj);
		
		if(left == right && left == 0) {
			try {
				left = powerOrder(leftObj);
				right = powerOrder(rightObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(right > left) result = -1;
		else if(right < left) result = 1;
		
		return result;
	}

	protected int typeOrder(Expression obj) {
		if(obj instanceof Power || obj instanceof Variable) {
			return 0;
		} else if(obj instanceof Exponential) {
			return 1;
		} else if(obj instanceof Logarithm) {
			return 2;
		} else if(obj instanceof Sine) {
			return 3;
		} else if(obj instanceof Cosine) {
			return 4;
		} else if(obj instanceof Tangent) {
			return 5;
		} else if(obj instanceof Cosecant) {
			return 6;
		} else if(obj instanceof Secant) {
			return 7;
		} else if(obj instanceof Cotangent) {
			return 8;
		} else if(obj instanceof Multiply) {
			return typeOrder(((Multiply)obj).getRight());
		} else if(obj instanceof Divide) {
			return typeOrder(((Divide)obj).getRight());
		} else {
			return 9;
		}
	}
	protected double powerOrder(Expression obj) throws Exception {
		if(obj instanceof Power) {
			return -((Power)obj).getPow();
		} else if(obj instanceof Variable) {
			return -1.0;
		} else {
			if(obj instanceof Multiply) {
				return powerOrder(((Multiply) obj).getRight());
			} else if(obj instanceof Divide) {
				return powerOrder(((Divide) obj).getRight());
			} else return 0;
		}
	}
}
