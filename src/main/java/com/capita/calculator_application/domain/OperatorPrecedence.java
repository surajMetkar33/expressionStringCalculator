package com.capita.calculator_application.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorPrecedence {
	public static final Map<String, Integer> PRECEDENCE_MAP;
	public static List operatorList = new ArrayList();
	
	static {
		PRECEDENCE_MAP = new HashMap<>();

		PRECEDENCE_MAP.put("-", 1);
		PRECEDENCE_MAP.put("+", 1);
		PRECEDENCE_MAP.put("/", 2);
		PRECEDENCE_MAP.put("*", 2);
		PRECEDENCE_MAP.put("^", 3);

		operatorList.add("+");
		operatorList.add("-");
		operatorList.add("*");
		operatorList.add("/");
		operatorList.add("^");
	}
}