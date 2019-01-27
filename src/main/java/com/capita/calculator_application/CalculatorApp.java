package com.capita.calculator_application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.capita.calculator_application.service.ExpressionExecutor;

public class CalculatorApp {

	public static int caseCount = 1;

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ExpressionExecutor.class);
		ExpressionExecutor executorImpl = context.getBean(ExpressionExecutor.class);

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of expression to evaluate");
		int expCount = sc.nextInt();

		List<String> inputExpressionList = new ArrayList<String>();

		while (expCount > 0) {
			String exp = sc.next();
			inputExpressionList.add(exp);
			expCount--;
		}

		// evaluate expressions
		List<String> resultList = executorImpl.executeExpressionList(inputExpressionList);

		// print results
		resultList.stream().forEach(result -> {
			System.out.println("Case #" + caseCount + ": " + result);
			caseCount++;
		});

		context.close();
	}
}