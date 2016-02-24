/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.decisiontree;

import java.util.List;

import com.pebblebeach.decisiontree.DecisionTree;
import com.pebblebeach.decisiontree.Example;
import com.pebblebeach.dto.Forecast;
import com.pebblebeach.manager.ForecastManager;
import com.pebblebeach.manager.ForecastManagerImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author gstarke
 *
 */
public class DecisionTreeTest extends TestCase {

	private ForecastManager forecastManager = new ForecastManagerImpl("/forecast.xlsx");

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public DecisionTreeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DecisionTreeTest.class);
	}

	/**
	 * Tests the DecisionTrue.convertTo method.
	 */
	public void testConvertTo() {
		// assert the manager
		assertNotNull(forecastManager);

		// assert the forecasts
		List<Forecast> forecasts = forecastManager.load();
		assertNotNull(forecasts);
		assertTrue(forecasts.size() == 14);

		// assert the examples
		Example[] examples = DecisionTree.convertTo(forecasts);
		assertNotNull(examples);
		assertTrue(examples.length == 14);

		for (Example e : examples) {
			assertTrue(e.my_attributes.length == 5);
			assertTrue(e.my_conditions.length == 5);
		}
	}

}
