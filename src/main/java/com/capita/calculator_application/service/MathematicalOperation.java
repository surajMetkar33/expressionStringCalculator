package com.capita.calculator_application.service;

@FunctionalInterface
public interface MathematicalOperation {
	Double operate(Double ip1, Double ip2) throws Exception;
}