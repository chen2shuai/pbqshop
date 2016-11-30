package com.junit.test;

public class Calculator {
	private static double result = 0.0;

	public void add(double num) {
		result = result + num;
	}

	public void substract(double num) {
		result = result - num;
	}

	public void multiply(double num) {
		result = result * num;
	}

	public void divide(double num) {
		if (num != 0) {
			result = result / num;
		} else {
			result = result;
		}
	}

	// 清零
	public void clear() {
		result = 0;
	}

	public double getResult() {
		return result;
	}
}
