package hr.fer.nenr.lab6.fuzzy;

import java.util.Random;

public class Rule {

    private static Random random = new Random();

    public SigmoidFuzzySet fuzzyA;
    public SigmoidFuzzySet fuzzyB;

    public double p;
    public double q;
    public double r;

    public Rule() {
        fuzzyA = new SigmoidFuzzySet(randomSmallValue(), randomSmallValue());
        fuzzyB = new SigmoidFuzzySet(randomSmallValue(), randomSmallValue());

        p = randomSmallValue();
        q = randomSmallValue();
        r = randomSmallValue();
    }

    public double tNorm(double x, double y) {
        return fuzzyA.valueAt(x) * fuzzyB.valueAt(y);
    }

    public double consequent(double x, double y) {
        return p * x + q * y + r;
    }

    private double randomSmallValue() {
        return random.nextDouble() * 2 - 1;
    }
}
