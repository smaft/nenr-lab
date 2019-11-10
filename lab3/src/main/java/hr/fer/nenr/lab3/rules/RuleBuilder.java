package hr.fer.nenr.lab3.rules;

import hr.fer.nenr.lab3.io.helpers.InputType;
import hr.fer.nenr.lab3.set.IFuzzySet;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {

    private List<Expression> antecedent = new ArrayList<>();

    private IFuzzySet consequent;

    public RuleBuilder when(InputType inputType, IFuzzySet fuzzySet) {
        if (!antecedent.isEmpty()) {
            throw new IllegalStateException("Only one when() call expected");
        }

        add(inputType, fuzzySet);
        return this;
    }

    public RuleBuilder and(InputType inputType, IFuzzySet fuzzySet) {
        if (antecedent.isEmpty()) {
            throw new IllegalStateException("Expected when() before and() call");
        }

        add(inputType, fuzzySet);
        return this;
    }

    public RuleBuilder then(IFuzzySet consequent) {
        this.consequent = consequent;
        return this;
    }

    public Rule build() {
        return new Rule(antecedent, consequent);
    }

    private void add(InputType inputType, IFuzzySet fuzzySet) {
        antecedent.add(new Expression(inputType, fuzzySet));
    }
}
