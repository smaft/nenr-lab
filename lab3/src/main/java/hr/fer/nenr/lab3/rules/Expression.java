package hr.fer.nenr.lab3.rules;

import hr.fer.nenr.lab3.io.helpers.InputType;
import hr.fer.nenr.lab3.set.IFuzzySet;

public class Expression {

    private InputType type;

    private IFuzzySet fuzzySet;

    public Expression(InputType type, IFuzzySet fuzzySet) {
        this.type = type;
        this.fuzzySet = fuzzySet;
    }

    public InputType getType() {
        return type;
    }

    public IFuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
