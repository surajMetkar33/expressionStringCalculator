package com.capita.calculator_application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import junit.framework.TestCase;

public class ExpressionExecutorTest extends TestCase {

	@org.junit.Test
	public void test() {

		List<String> inputExpList = new ArrayList<String>();
		inputExpList.add("7+(6*5^2+3-4/2)");
		inputExpList.add("7+(67(56*2))");
		inputExpList.add("8*+7");
		inputExpList.add("(8*5/8)-(3/1)-5");

		ApplicationContext context = new AnnotationConfigApplicationContext(ExpressionExecutor.class);
		ExpressionExecutor expressionExecutorImplTest = context.getBean(ExpressionExecutor.class);
		List<String> resultList = expressionExecutorImplTest.executeExpressionList(inputExpList);
		System.out.println(resultList);

		assertEquals("158.00", resultList.get(0));
		assertEquals("INVALID EXPRESSION", resultList.get(1));
		assertEquals("INVALID EXPRESSION", resultList.get(2));
		assertEquals("-3.00", resultList.get(3));
	}
}