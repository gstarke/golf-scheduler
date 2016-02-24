/**
 * Copyright (C) 2016 Garret W. Starke. All rights reserved.
 */
package com.pebblebeach.decisiontree;

/**
 * Constants used for the decision tree data structures.
 * 
 * @author gstarke
 */
public final class Constants {

	// conditions are possible values for attributes
	public static final Condition sunny = new Condition("sunny");
	public static final Condition overcast = new Condition("overcast");
	public static final Condition rain = new Condition("rain");
	public static final Condition gr_60 = new Condition(">60");
	public static final Condition gr_70 = new Condition(">70");
	public static final Condition gr_80 = new Condition(">80");
	public static final Condition gr_90 = new Condition(">90");
	public static final Condition yes = new Condition("yes");
	public static final Condition no = new Condition("no");

	// these attributes are overall, all possible values
	public static final Attribute[] real_attributes = new Attribute[4];

	static {
		real_attributes[0] = new Attribute("outlook", sunny, overcast, rain);
		real_attributes[1] = new Attribute("temperature", gr_60, gr_70, gr_80);
		real_attributes[2] = new Attribute("humidity", gr_60, gr_70, gr_80, gr_90);
		real_attributes[3] = new Attribute("windy", yes, no);
	}

	// these attributes will be linked to examples
	public static final Attribute[] example_attributes = new Attribute[5];

	static {
		example_attributes[0] = new Attribute("outlook", sunny, overcast, rain);
		example_attributes[1] = new Attribute("temperature", gr_60, gr_70, gr_80);
		example_attributes[2] = new Attribute("humidity", gr_60, gr_70, gr_80, gr_90);
		example_attributes[3] = new Attribute("windy", yes, no);
		example_attributes[4] = new Attribute("play", yes, no);
	}

	// the desired attribute is last
	public static final Attribute desired_attribute = example_attributes[example_attributes.length - 1];

}
