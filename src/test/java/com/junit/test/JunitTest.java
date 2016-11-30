package com.junit.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JunitTest extends TestCase {

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JunitTest.class);
	}

	public void testApp() {
		System.out.println("junit test");
		//assertTrue("true", true);
	}
}
