package com.capita.calculator_application.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.capita.calculator_application.operation.ExpressionOperation;
import com.capita.calculator_application.validation.api.ExpressionValidator;

@Configuration
@ComponentScan("com.capita")
public class ExpressionExecutor {

	@Autowired
	private ExpressionValidator expressionValidator;

	@Autowired
	private ExpressionOperation expressionOperation;

	/**
	 * This method will execute list by by validating it, converting it to postfix 
	 * and computing the result in retruned list
	 * 
	 * @param List<String> expressionList
	 * @return  List<String> resultList
	 */
	public List<String> executeExpressionList(List<String> expressionList) {

		DecimalFormat decimalFormat = new DecimalFormat("##.00");

		List<String> resultList = new ArrayList<String>();

		expressionList.stream().forEach(expression -> {

			// Validation
			if (expressionValidator.validate(expression)) {

				// Infix To Postfix
				String infixExpression = expressionOperation.infixToPostfix(expression);

				// Compute the Expression
				try {
					Double resultVal = expressionOperation.compute(infixExpression);
					resultList.add(resultVal.equals(null) ? "" : decimalFormat.format(resultVal));

				} catch (Exception ex) {
					resultList.add("INVALID EXPRESSION");
				}
			} else {
				resultList.add("INVALID EXPRESSION");
			}
		});

		return resultList;
	}
}