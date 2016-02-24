package com.pebblebeach;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);

		for (int n = 0; n < 10; n++)
			System.out.println(String.format("N = %s, F(N) = %s, , F1(N) = %s", n, f(n), f1(n)));
	}

	/**
	 * Running time O(2^N)
	 * 
	 * @param n
	 *            the value
	 * @return the f(N)
	 */
	long f(long n) {
		if (n == 0)
			return 1;
		else if (n == 1)
			return 2;
		else
			return f(n - 1) * f(n - 2);
	}

	long f1(long n) {
		if (n == 0)
			return 1;
		else if (n == 1)
			return 2;
		else {
			long x = 1, y = 2, z = 1;
			for (int i = 0; i < n; i++) {
				x = y;
				y = z;
				z = x * y;
			}
			return z;
		}
	}
}
