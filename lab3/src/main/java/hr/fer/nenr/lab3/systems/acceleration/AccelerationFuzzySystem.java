package hr.fer.nenr.lab3.systems.acceleration;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.rules.set.AccelerationRuleSet;
import hr.fer.nenr.lab3.systems.AbstractInputFuzzySystem;

public class AccelerationFuzzySystem extends AbstractInputFuzzySystem {

    public AccelerationFuzzySystem(Defuzzifier defuzzifier,
            InferenceEngine inferenceEngine) {
        super(new AccelerationRuleSet(), defuzzifier, inferenceEngine);
    }
}
