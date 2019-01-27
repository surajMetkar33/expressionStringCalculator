package com.capita.calculator_application.validation.imp;

import java.util.Set;
import java.util.Stack;

import org.springframework.stereotype.Component;

import com.capita.calculator_application.domain.OperatorPrecedence;
import com.capita.calculator_application.util.ExpressionUtil;
import com.capita.calculator_application.validation.api.ExpressionValidator;

@Component
public class ExpressionValidatorImpl implements ExpressionValidator {

	/**
	 * This method will validates the expression for the below conditions expression
	 * cannot start ')' expression cannot end '(' expression cannot start or end
	 * with any operators
	 * 
	 * @param String expression
	 * @return boolean
	 */
	public boolean validateStartAndEnd(String expression) {
		Set operators = OperatorPrecedence.PRECEDENCE_MAP.keySet();

		char startChar = expression.charAt(0);
		char endChar = expression.charAt(expression.length() - 1);
		if (expression.startsWith(")") || expression.endsWith("(") || operators.contains("" + startChar)
				|| operators.contains("" + endChar)) {
			return false;
		}
		return true;
	}

	/**
	 * This method will validates the expression for the below conditions Expression
	 * cannot have consecutive operators Expression cannot have consecutive operands
	 * 
	 * @param String expression
	 * @return boolean
	 */
	public boolean validateConsecutiveOperandAndOperator(String expression) {
		for (int i = 1; i < expression.length(); i++) {
			char currChar = expression.charAt(i);
			char prevChar = expression.charAt(i - 1);
			if (ExpressionUtil.isOperator("" + currChar) && ExpressionUtil.isOperator("" + prevChar)) {
				return false;
			} else if (ExpressionUtil.isNumeric("" + currChar) && ExpressionUtil.isNumeric("" + prevChar)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method will validates the expression for the below conditions Expression
	 * should contain equal opening bracket and closing brackets Expression cannot
	 * contain characters other than 0-9, (, ), operators
	 * 
	 * @param String expression
	 * @return boolean
	 */
	public boolean validateCornerCasses(String expression) {
		Stack<String> stack = new Stack<>();

		char[] charArr = expression.toCharArray();
		for (char ch : charArr) {
			if (!ExpressionUtil.isNumeric("" + ch) && ch != '(' && ch != ')' && !ExpressionUtil.isOperator("" + ch)) {
				return false;
			}
			if (ch != ')') {

				if (ch == '(') {
					if (!stack.empty() && ExpressionUtil.isNumeric(stack.peek())) {
						return false;
					}
				}
				stack.push("" + ch);
			} else {

				while (!stack.peek().equalsIgnoreCase("(")) {
					stack.pop();
				}
				stack.pop();
			}
		}
		if (!stack.empty())
			return false;
		return true;
	}
}