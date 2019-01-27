package com.capita.calculator_application.service;

import org.springframework.stereotype.Component;

@Component
public class MathematicalOperationFactory {

	/**
	 * This method returns the lambda expression of a Mathematical operator
	 * 
	 * @param String operator
	 * @return MathematicalOperation
	 **/
	public MathematicalOperation getMathematicalOperation(String operator) {

		MathematicalOperation mathematicalOperation = null;

		switch (operator) {
		case "^":
			mathematicalOperation = (Double a, Double b) -> {
				return (double) Math.pow(a, b);
			};
			break;

		case "*":
			mathematicalOperation = (Double a, Double b) -> {
				return (double) a * b;
			};
			break;

		case "/":
			mathematicalOperation = (Double a, Double b) -> {
				// Check divide by zero
				if (b == 0) {
					throw new Exception();
				}
				return (double) a / b;
			};
			break;

		case "+":
			mathematicalOperation = (Double a, Double b) -> {
				return (double) a + b;
			};
			break;

		case "-":
			mathematicalOperation = (Double a, Double b) -> {
				return (double) a - b;
			};
			break;
		}

		return mathematicalOperation;
	}
}