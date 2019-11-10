package hr.fer.nenr.lab3.defuzzifiers;

import hr.fer.nenr.lab3.set.IFuzzySet;

public interface Defuzzifier {

    int decode(IFuzzySet fuzzySet);
}
