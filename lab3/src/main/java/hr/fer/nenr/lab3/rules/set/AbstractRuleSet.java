package hr.fer.nenr.lab3.rules.set;

import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.operations.Operations;
import hr.fer.nenr.lab3.rules.Rule;
import hr.fer.nenr.lab3.set.IFuzzySet;

import java.util.List;

public class AbstractRuleSet implements RuleSet {

    private List<Rule> rules;

    public AbstractRuleSet(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public IFuzzySet conclude(Input input, InferenceEngine inferenceEngine) {
        IFuzzySet conclusion = rules.get(0).conclude(input, inferenceEngine);
        for (int i = 1; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            IFuzzySet ruleConclusion = rule.conclude(input, inferenceEngine);
            conclusion = Operations.binaryOperation(
                    conclusion, ruleConclusion,
                    Operations.zadehOr()
            );
        }
        return conclusion;
    }

    @Override
    public int ruleCount() {
        return rules.size();
    }

    @Override
    public Rule getRule(int index) {
        return rules.get(index);
    }
}
