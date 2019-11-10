package hr.fer.nenr.lab3.systems;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.rules.set.RuleSet;

public class AbstractInputFuzzySystem implements IntFuzzySystem<Input> {

    private RuleSet ruleSet;

    private Defuzzifier defuzzifier;

    private InferenceEngine inferenceEngine;

    public AbstractInputFuzzySystem(RuleSet ruleSet, Defuzzifier defuzzifier,
            InferenceEngine inferenceEngine) {
        this.ruleSet = ruleSet;
        this.defuzzifier = defuzzifier;
        this.inferenceEngine = inferenceEngine;
    }

    @Override
    public int conclude(Input input) {
        return defuzzifier.decode(ruleSet.conclude(input, inferenceEngine));
    }
}
