package com.capita.calculator_application.operation;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capita.calculator_application.service.MathematicalOperation;
import com.capita.calculator_application.service.MathematicalOperationFactory;
import com.capita.calculator_application.util.ExpressionUtil;

@Component
public class ExpressionOperation {

	@Autowired
	private MathematicalOperationFactory mathematicalOperationFactory;

	/**
	 * This method will convet infix expression to postfix
	 * 
	 * @param String expression
	 * @return String postfixExpression
	 */
	public static String infixToPostfix(String exp) {
		String result = "";

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			// if c is an operand, add it to output.
			if (Character.isLetterOrDigit(c))
				result += c;

			// if c is an '(', push it to the stack.
			else if (c == '(')
				stack.push(c);

			else if (c == ')') {
				// pop elements till ')'
				while (!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();

				if (!stack.isEmpty() && stack.peek() != '(')
					return "INVALID EXPRESSION"; // invalid expression
				else
					stack.pop();
			} else {
				// c is encountered
				while (!stack.isEmpty() && Precedence(c) <= Precedence(stack.peek()))
					result += stack.pop();
				stack.push(c);
			}

		}

		// pop all the operators from the stack till it is empty
		while (!stack.isEmpty())
			result += stack.pop();

		return result;
	}

	private Double process(Double ip1, Double ip2, String op) throws Exception {
		MathematicalOperation mathematicalOperation = mathematicalOperationFactory.getMathematicalOperation(op);
		return mathematicalOperation.operate(ip1, ip2);
	}

	/**
	 * This method will compute postfix expression by using stack
	 * 
	 * @param String
	 *            expression
	 * @return Double computedValue
	 */
	public Double compute(String expression) throws Exception {

		Stack<String> stack = new Stack<>();
		double inp1 = 0;
		double inp2 = 0;

		for (int i = 0; i < expression.length(); i++) {

			String str = "" + expression.charAt(i);
			if (ExpressionUtil.isNumeric(str)) {
				stack.push(str);
			} else {
				inp2 = Double.parseDouble(stack.pop());
				inp1 = Double.parseDouble(stack.pop());
				stack.push(process(inp1, inp2, str).toString());
			}
		}
		return Double.parseDouble(stack.pop());
	}

	static int Precedence(char ch) {
		switch (ch) {
		case '+':
			return 1;

		case '-':
			return 1;

		case '*':
			return 2;

		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}
}