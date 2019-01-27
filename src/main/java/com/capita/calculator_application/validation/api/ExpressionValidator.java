package com.capita.calculator_application.validation.api;

public interface ExpressionValidator {

	boolean validateStartAndEnd(String expression);
	boolean validateConsecutiveOperandAndOperator(String expression);
	boolean validateCornerCasses(String expression);
	
	default boolean validate(String expression) {
		if (expression.length() != 0 && validateStartAndEnd(expression)) {
			expression = "(" + expression + ")";
			return validateCornerCasses(expression) && validateConsecutiveOperandAndOperator(expression);
		}
		return false;
	}
}