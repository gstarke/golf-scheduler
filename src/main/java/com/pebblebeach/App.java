package com.pebblebeach;

import java.util.List;

import com.pebblebeach.decisiontree.Constants;
import com.pebblebeach.decisiontree.DecisionTree;
import com.pebblebeach.decisiontree.Example;
import com.pebblebeach.decisiontree.Node;
import com.pebblebeach.dto.Forecast;
import com.pebblebeach.manager.ForecastManager;
import com.pebblebeach.manager.ForecastManagerImpl;

/**
 * This example builds a decision tree given a set of data. The description of
 * the problem is
 * <a target="_blank" href="http://rubyquiz.strd6.com/quizzes/213-decision-tree-learning">here
 * </a>. The decision tree is built using the Information Gain algorithm 
 * (<a target="_blank" href="https://en.wikipedia.org/wiki/Information_gain_in_decision_trees">more</a>). 
 * Source code for the decision tree implementation was provided by
 * Jakob Griffith (<a target="_blank" href="https://github.com/kobaj/JavaDecisionTree">more</a>).
 */
public class App {
	public static void main(String[] args) {

		// create the forecast manager
		ForecastManager mgr = new ForecastManagerImpl("/forecast.xlsx");

		// load the weather data
		List<Forecast> forecasts = mgr.load();

		// convert the weather data into example data
		Example[] examples = DecisionTree.convertTo(forecasts);

		// build the decision tree
		Node<?> tree = DecisionTree.learnDecision(examples, Constants.real_attributes, Constants.no,
				Constants.desired_attribute);

		// print the decision tree
		System.out.println(tree.toString());
	}
}
