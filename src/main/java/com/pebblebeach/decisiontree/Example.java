/**
 * Copyright (c) 2013 Jakob Griffith
 */
package com.pebblebeach.decisiontree;

public class Example {

	public Attribute[] my_attributes;
	public Condition[] my_conditions;

	public Condition get_label() {
		return my_conditions[my_conditions.length - 1];
	}

	public Example(Attribute[] const_attributes, Condition... conditions) {
		my_attributes = const_attributes;
		my_conditions = conditions;
	}

	public boolean check_condition(Attribute attribute, Condition c) {
		for (int i = 0; i < my_attributes.length; i++) {
			if (my_attributes[i].equals(attribute))
				if (my_conditions[i].equals(c)) {
					return true;
				}
		}

		return false;
	}

}
