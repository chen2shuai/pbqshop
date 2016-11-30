package com.junit.test;

import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest4 {
	private static Calculator calculator = new Calculator();
	
	/*@Before
	public void setUp() throws Exception {
		System.out.println("JUnit initialize the fixture state by overriding setup ");
		calculator.clear();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("JUnit clean-up after a test by overriding tearDown ");
		calculator.clear();
	}*/

	/*@Test
	public void add() {
		System.out.println("add result:" + calculator.getResult());
		calculator.add(10.1);
		assertEquals(10.1, calculator.getResult());
	}*/

	/*@Test
	public void testSubstract() {
		System.out.println("substract result:" + calculator.getResult());
		calculator.add(10.1);
		calculator.substract(2);
		//assertEquals(8.112, calculator.getResult());
	}

	@Test
	public void testMultiply() {
		System.out.println("multiply result:" + calculator.getResult());
		calculator.add(12);
		calculator.multiply(12);
		assertEquals(144.0, calculator.getResult());
	}*/

	/*@Test(expected=ArithmeticException.class)
	public void divide(){
		int i = 2/0;
	}*/
	
	/*@Test(timeout=10)
	public void count(){
		for (int i = 0; i < 10000; i++) {
			System.out.println(i);
		}
	}*/
	@Ignore("此方法现在不需要")
	@Test
	public void ignore(){
		System.out.println("不需要");
	}

}
