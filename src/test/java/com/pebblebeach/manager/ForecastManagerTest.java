/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.manager;

import java.util.List;

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
public class ForecastManagerTest extends TestCase {
	
	private ForecastManager forecastManager = new ForecastManagerImpl("/forecast.xlsx");
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ForecastManagerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ForecastManagerTest.class);
	}

	public void testLoad(){
		// assert the manager
		assertNotNull(forecastManager);
		
		// assert the forecasts
		List<Forecast> forecasts = forecastManager.load();
		assertNotNull(forecasts);
		
		// assert each forecast
		for(Forecast f : forecasts){
			assertNotNull(f.getOutlook());
			assertNotNull(f.getTemperature());
			assertNotNull(f.getHumidity());
			assertNotNull(f.isWindy());
			assertNotNull(f.isPlay());
			System.out.println(f);
		}
	}

}