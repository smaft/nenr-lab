package hr.fer.nenr.lab6.neurofuzzy;

import hr.fer.nenr.lab6.fuzzy.Rule;

public class ANFIS {

    private Rule[] rules;
    private double[] w;
    private double wSum;
    private double[] wNormalized;
    private double[] z;

    public ANFIS(int ruleCount) {
        rules = new Rule[ruleCount];
        for (int i = 0; i < rules.length; i++) {
            rules[i] = new Rule();
        }
        w = new double[ruleCount];
        wNormalized = new double[ruleCount];
        z = new double[ruleCount];
    }

    public Rule[] getRules() {
        return rules;
    }

    public double getCachedWSum() {
        return wSum;
    }

    public double[] getCachedW() {
        return w;
    }

    public double[] getCachedZ() {
        return z;
    }

    public double calculateOutput(double x, double y) {
        for (int i = 0; i < rules.length; i++) {
            Rule rule = rules[i];
            w[i] = rule.tNorm(x, y);
        }
        calculateWNormalized();

        double output = 0.0;
        for (int i = 0; i < rules.length; i++) {
            z[i] = rules[i].consequent(x, y);
            output += wNormalized[i] * z[i];
        }

        return output;
    }

    private void calculateWNormalized() {
        wSum = 0.0;
        for (int i = 0; i < w.length; i++) {
            wSum += w[i];
        }

        for (int i = 0; i < w.length; i++) {
            wNormalized[i] = w[i] / wSum;
        }
    }
}
