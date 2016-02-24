/**
 * Copyright (c) 2013 Jakob Griffith
 */
package com.pebblebeach.decisiontree;

import java.util.Comparator;

public class ExampleComparator implements Comparator<Example> {

	@Override
	public int compare(Example o1, Example o2) {
		return (o1.get_label()).compareTo(o2.get_label());
	}

}