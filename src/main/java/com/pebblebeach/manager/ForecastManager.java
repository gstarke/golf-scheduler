/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.manager;

import java.util.List;

import com.pebblebeach.dto.Forecast;

/**
 * @author gstarke
 *
 */
public interface ForecastManager {

	/**
	 * This method returns a list of {@link Forecast} objects.
	 * 
	 * @return {@link List}&lt;{@link Forecast}&gt;
	 */
	List<Forecast> load();

}
