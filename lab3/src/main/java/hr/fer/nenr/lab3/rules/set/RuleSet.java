package hr.fer.nenr.lab3.rules.set;

import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.rules.Rule;
import hr.fer.nenr.lab3.set.IFuzzySet;

public interface RuleSet {

    IFuzzySet conclude(Input input, InferenceEngine inferenceEngine);

    int ruleCount();

    Rule getRule(int index);
}
