package com.capita.calculator_application.util;

import com.capita.calculator_application.domain.OperatorPrecedence;

public class ExpressionUtil {

	public static boolean isNumeric(String str) {
		try {
			int a = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isOperator(String str) {
		try {
			return OperatorPrecedence.operatorList.contains(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isLetter(String str) {
		try {
			return !isNumeric(str) && !isOperator(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}