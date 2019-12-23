package hr.fer.nenr.lab6.fuzzy;

public class SigmoidFuzzySet implements FuzzySet {

    public double a;
    public double b;

    public SigmoidFuzzySet(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double valueAt(double x) {
        return 1.0 / (1.0 + Math.exp(b * (x - a)));
    }
}
