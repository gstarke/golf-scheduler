/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pebblebeach.dto.Forecast;

/**
 * @author gstarke
 *
 */
public class ForecastManagerImpl implements ForecastManager {

	/**
	 * log property
	 */
	private static final Logger log = LogManager.getLogger(ForecastManagerImpl.class);

	/**
	 * Public constructor
	 * 
	 * @param fileName
	 *            the fileName
	 */
	public ForecastManagerImpl(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * fileName property
	 */
	private String fileName;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vividseats.manager.ForecastManager#load()
	 */
	public List<Forecast> load() {
		List<Forecast> forecasts = new ArrayList<Forecast>();

		// Using XSSF for xlsx format, for xls use HSSF
		try (Workbook workbook = new XSSFWorkbook(getClass().getResourceAsStream(fileName))) {
			if (workbook.getNumberOfSheets() != 1)
				throw new RuntimeException("The Excel document is not valid.");

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			// iterating over each row
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (row.getRowNum() == 0)
					continue;

				String outlook = row.getCell(0).getStringCellValue();
				double temperature = row.getCell(1).getNumericCellValue();
				double humidity = row.getCell(2).getNumericCellValue();
				boolean windy = row.getCell(3).getBooleanCellValue();
				boolean play = row.getCell(4).getBooleanCellValue();

				forecasts.add(new Forecast(outlook, temperature, humidity, windy, play));
			}

			return forecasts;
		} catch (FileNotFoundException e) {
			log.error("Failed to load Excel document.", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error("Failed to open Excel document.", e);
			throw new RuntimeException(e);
		}
	}

}
