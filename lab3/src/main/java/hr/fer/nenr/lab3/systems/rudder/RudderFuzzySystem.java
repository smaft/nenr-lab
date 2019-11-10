package hr.fer.nenr.lab3.systems.rudder;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.rules.set.RudderRuleSet;
import hr.fer.nenr.lab3.systems.AbstractInputFuzzySystem;

public class RudderFuzzySystem extends AbstractInputFuzzySystem {

    public RudderFuzzySystem(Defuzzifier defuzzifier,
            InferenceEngine inferenceEngine) {
        super(new RudderRuleSet(), defuzzifier, inferenceEngine);
    }
}
