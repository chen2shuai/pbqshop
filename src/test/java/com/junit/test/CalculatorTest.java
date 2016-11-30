package com.junit.test;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {
	
	private static Calculator calculator = new Calculator();
	
	@Override
	protected void setUp() throws Exception {
		System.out.println("JUnit initialize the fixture state by overriding setup ");
		calculator.clear();
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("JUnit clean-up after a test by overriding tearDown ");
		calculator.clear();
	}

	public void testAdd() {
		System.out.println("add result:" + calculator.getResult());
		calculator.add(10.1);
		assertEquals(10.1, calculator.getResult());
	}

	public void testSubstract() {
		System.out.println("substract result:" + calculator.getResult());
		calculator.add(10.1);
		calculator.substract(2);
		assertEquals(8.1, calculator.getResult());
	}

	public void testMultiply() {
		System.out.println("multiply result:" + calculator.getResult());
		calculator.add(12);
		calculator.multiply(12);
		assertEquals(144.0, calculator.getResult());
	}

	public void testDivide() {
		System.out.println("divide result:" + calculator.getResult());
		calculator.add(12);
		calculator.divide(12);
		assertEquals(1.0, calculator.getResult());
	}

}
