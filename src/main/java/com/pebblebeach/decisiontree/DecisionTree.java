/**
 * Copyright (c) 2013 Jakob Griffith
 */
package com.pebblebeach.decisiontree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pebblebeach.dto.Forecast;

public final class DecisionTree {

	// begin code modification 
	
	/**
	 * Returns an array of {@link Example} given the list of {@link Forecast}.
	 * 
	 * @param forecasts
	 *            the weather
	 * @return array of {@link Example}
	 */
	public static Example[] convertTo(List<Forecast> forecasts) {
		List<Example> examples = new ArrayList<Example>();
		String sValue = null;
		Integer iValue = null;

		for (Forecast f : forecasts) {
			Condition outlook = null;
			sValue = f.getOutlook();
			if (sValue.equals("overcast"))
				outlook = Constants.overcast;
			else if (sValue.equals("rain"))
				outlook = Constants.rain;
			else if (sValue.equals("sunny"))
				outlook = Constants.sunny;
			else
				throw new RuntimeException("Failed to determine the outlook.");

			Condition temperature = null;
			iValue = f.getTemperature();
			if (iValue < 70)
				temperature = Constants.gr_60;
			else if (iValue < 80)
				temperature = Constants.gr_70;
			else if (iValue < 90)
				temperature = Constants.gr_80;
			else if (iValue >= 90)
				temperature = Constants.gr_90;
			else
				throw new RuntimeException("Failed to determine the temperature.");

			Condition humidity = null;
			iValue = f.getHumidity();
			if (iValue < 70)
				humidity = Constants.gr_60;
			else if (iValue < 80)
				humidity = Constants.gr_70;
			else if (iValue < 90)
				humidity = Constants.gr_80;
			else if (iValue >= 90)
				humidity = Constants.gr_90;
			else
				throw new RuntimeException("Failed to determine the humidity.");

			Condition windy = (f.isWindy() ? Constants.yes : Constants.no);

			Condition play = (f.isPlay() ? Constants.yes : Constants.no);

			examples.add(new Example(Constants.example_attributes, outlook, temperature, humidity, windy, play));
		}

		// convert to an array of Example
		Example[] retval = new Example[examples.size()];
		examples.toArray(retval);

		return retval;
	}
	
	// end code modification

	/**
	 * Main learn loop that builds a decision tree
	 * 
	 * @param examples
	 *            list of input samples built from attributes and conditions
	 * @param attributes
	 *            list of all attributes to iterate over
	 * @param default_label
	 *            list of default condition for desired attribute to apply
	 * @param desired_attribute
	 *            the attribute to test for
	 * @return the decision tree
	 */
	public static Node<?> learnDecision(Example[] examples, Attribute[] attributes, Condition default_label,
			Attribute desired_attribute) {
		// first base case
		if (examples.length == 0) {
			System.out.println("leaf 1");
			return new Node<Condition>(default_label, "first leaf ");
		}

		// second base case
		ArrayList<Example> example_copy = new ArrayList<Example>();
		for (Example e : examples)
			example_copy.add(e);
		Collections.sort(example_copy, new ExampleComparator());
		if (example_copy.get(0).get_label().equals(//
				example_copy.get(example_copy.size() - 1).get_label())) {
			System.out.println("leaf 2");
			return new Node<Condition>(examples[0].get_label(), "second leaf ");
		}

		// third base case
		if (attributes.length == 0) {
			Condition mode = Mode(examples);
			System.out.println("leaf 3");
			return new Node<Condition>(mode, "third leaf ");
		}

		// recurse
		Attribute best = ChooseBestAttribute(examples, attributes, desired_attribute);
		System.out.println("Finding a best: " + best.name);
		Node<Attribute> tree = new Node<Attribute>(best, "middle node ");
		Condition label = Mode(examples);

		for (Condition c : best.possible_conditions) {
			Example[] example_i = best.satisfied(examples, c);
			Node<?> sub_tree = learnDecision(example_i, removeBest(attributes, best), label, desired_attribute);
			sub_tree.identifier += c.toString();

			tree.children.add(sub_tree);
		}

		return tree;
	}

	/**
	 * Takes in an array of attributes and removes one.
	 * 
	 * @param attributes
	 *            array of attributes
	 * @param best
	 *            attribute to be removed
	 * @return an array of attributes
	 */
	public static Attribute[] removeBest(Attribute[] attributes, Attribute best) {
		ArrayList<Attribute> modified_attributes = new ArrayList<Attribute>();

		for (Attribute a : attributes) {
			if (!a.equals(best))
				modified_attributes.add(a);
		}

		return modified_attributes.toArray(new Attribute[0]);
	}

	/**
	 * Calculates the mathematical mode condition (based on desired attribute,
	 * aka, last attribute in example).
	 * 
	 * @param examples
	 *            the examples
	 * @return condition that appears most often
	 */
	public static Condition Mode(Example[] examples) {
		Condition max_condition = null;
		int max_count = 0;

		// not the most efficient
		for (Example e : examples) {
			int local_count = 0;
			for (Example inner_e : examples) {
				if (inner_e.get_label().equals(e.get_label()))
					local_count++;
			}

			if (local_count > max_count) {
				max_count = local_count;
				max_condition = e.get_label();
			}
		}

		System.out.println("Mode value: " + max_condition);

		return max_condition;
	}

	/**
	 * This will calculate the Gain and Remainder of attributes and picks the
	 * best to recurse over
	 * 
	 * @param examples
	 *            the examples
	 * @param attributes
	 *            the attributes
	 * @param desired_attribute
	 *            the desired_attribute
	 * @return {@link Attribute}
	 */
	public static Attribute ChooseBestAttribute(Example[] examples, Attribute[] attributes,
			Attribute desired_attribute) {

		Attribute best = null;
		double smallest_double = Double.MAX_VALUE;

		for (Attribute a : attributes) {
			double remain = Remain(examples, a, desired_attribute);
			if (best == null || remain < smallest_double) {
				smallest_double = remain;
				best = a;
			}
		}

		return best;
	}

	/**
	 * Computes the Remain, sum(p/t * I(pi/ti, ni/ti));
	 * 
	 * @param examples
	 *            the examples
	 * @param attribute
	 *            the attribute
	 * @param desired_attribute
	 *            the desired_attribute
	 * @return a double value for the remain
	 */
	public static double Remain(Example[] examples, Attribute attribute, Attribute desired_attribute) {
		System.out.println("\nrunning a rem for: " + attribute.name);

		double total_examples = examples.length;

		double total = 0;

		// figure out each attribute
		for (Condition major_condition : attribute.possible_conditions) {
			System.out.println("possible condition: " + major_condition.name);

			Example[] sub_examples = attribute.satisfied(examples, major_condition);
			Double total_sub_examples = (double) sub_examples.length;
			double precident = total_sub_examples / total_examples;

			System.out.println("Number satisfied: " + total_sub_examples);

			// figure out the igain
			ArrayList<Double> sub_example_count = new ArrayList<Double>();
			for (Condition c : desired_attribute.possible_conditions) {
				Example[] examples_c = desired_attribute.satisfied(sub_examples, c);
				System.out.println("number of passing sub examples: " + examples_c.length);
				sub_example_count.add(examples_c.length / total_sub_examples);
			}
			double i_gain = IGain(sub_example_count.toArray(new Double[0]));
			System.out.println("iGain: " + i_gain);

			double total_local_value = precident * i_gain;

			total += total_local_value;
		}

		System.out.println("got a result of: " + total);

		return total;
	}

	/**
	 * Computes the I value of Remain sum(-p(vi)logbase2ofp(vi))
	 * 
	 * @param ds
	 *            which comes from remain
	 * @return double value of I
	 */
	public static double IGain(Double... ds) {
		double final_value = 0;
		for (double d : ds) {
			if (d != 0.0)
				final_value += -d * Math.log(d) / Math.log(2.0);
		}

		if (Double.isNaN(final_value))
			final_value = 0;

		return final_value;
	}

}