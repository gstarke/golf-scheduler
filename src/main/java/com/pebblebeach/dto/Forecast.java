/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author gstarke
 *
 */
public class Forecast {

	/**
	 * Public constructor
	 * 
	 * @param outlook
	 *            the outlook
	 * @param temperature
	 *            the temperature (deg)
	 * @param humidity
	 *            the humidity (deg)
	 * @param windy
	 *            the windy flag
	 * @param play
	 *            the play flag
	 */
	public Forecast(final String outlook, final double temperature, final double humidity, final boolean windy,
			final boolean play) {
		this.outlook = outlook;
		this.temperature = (int) temperature;
		this.humidity = (int) humidity;
		this.windy = windy;
		this.play = play;
	}

	/**
	 * outlook property
	 */
	private String outlook;

	/**
	 * @return the outlook
	 */
	public String getOutlook() {
		return outlook;
	}

	/**
	 * @param outlook
	 *            the outlook to set
	 */
	public void setOutlook(final String outlook) {
		this.outlook = outlook;
	}

	/**
	 * temperature property
	 */
	private Integer temperature;

	/**
	 * @return the temperature
	 */
	public Integer getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(final Integer temperature) {
		this.temperature = temperature;
	}

	/**
	 * humidity property
	 */
	private Integer humidity;

	/**
	 * @return the humidity
	 */
	public Integer getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity
	 *            the humidity to set
	 */
	public void setHumidity(final Integer humidity) {
		this.humidity = humidity;
	}

	/**
	 * windy property
	 */
	private Boolean windy;

	/**
	 * @return the windy
	 */
	public Boolean isWindy() {
		return windy;
	}

	/**
	 * @param windy
	 *            the windy to set
	 */
	public void setWindy(final Boolean windy) {
		this.windy = windy;
	}

	/**
	 * play property
	 */
	private Boolean play;

	/**
	 * @return the play
	 */
	public Boolean isPlay() {
		return play;
	}

	/**
	 * @param play
	 *            the play to set
	 */
	public void setPlay(final Boolean play) {
		this.play = play;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
