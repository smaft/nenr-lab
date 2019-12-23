package hr.fer.nenr.lab6.algorithm;

import hr.fer.nenr.lab6.dataset.Dataset;
import hr.fer.nenr.lab6.fuzzy.Rule;
import hr.fer.nenr.lab6.neurofuzzy.ANFIS;

public abstract class ANFISOptimization implements OptimizationAlgorithm {

    protected ANFIS anfis;
    protected Dataset dataset;

    private int maxIterations;
    private double etaAntecedent;
    private double etaConsequent;

    private Rule[] rules;
    private DeltaRule[] deltaRules;

    public ANFISOptimization(ANFIS anfis, Dataset dataset,
            int maxIterations, double etaAntecedent, double etaConsequent) {
        this.dataset = dataset;
        this.anfis = anfis;
        this.maxIterations = maxIterations;
        this.etaAntecedent = etaAntecedent;
        this.etaConsequent = etaConsequent;

        rules = anfis.getRules();
        initDeltaRules(rules.length);
    }

    @Override
    public void optimize() {
        for (int i = 1; i <= maxIterations; i++) {
//            System.out.println("[" + i + "] " + meanSquareError());
            System.out.println(i + " " + meanSquareError());
            iteration();
        }
    }

    protected abstract void iteration();

    protected void updateRuleDeltas(double x, double y, double expected) {
        double actual = anfis.calculateOutput(x, y);
        double difference = expected - actual;

        double wSum = anfis.getCachedWSum();
        double[] z = anfis.getCachedZ();
        double[] w = anfis.getCachedW();

        for (int i = 0; i < rules.length; i++) {
            Rule rule = rules[i];

            double ai = rule.fuzzyA.a;
            double bi = rule.fuzzyA.b;
            double ci = rule.fuzzyB.a;
            double di = rule.fuzzyB.b;

            double fuzzyA = rule.fuzzyA.valueAt(x);
            double fuzzyB = rule.fuzzyB.valueAt(y);
            double dFuzzyA = fuzzyA * (1 - fuzzyA);
            double dFuzzyB = fuzzyB * (1 - fuzzyB);
            double common = -difference * (z[i] - actual) / wSum;

            deltaRules[i].a += common * dFuzzyA * fuzzyB * bi;
            deltaRules[i].b += common * dFuzzyA * fuzzyB * (ai - x);
            deltaRules[i].c += common * fuzzyA * dFuzzyB * di;
            deltaRules[i].d += common * fuzzyA * dFuzzyB * (ci - x);

            double rGradient = -difference * w[i] / wSum;

            deltaRules[i].p += rGradient * x;
            deltaRules[i].q += rGradient * y;
            deltaRules[i].r += rGradient;
        }
    }

    protected void updateRules() {
        for (int i = 0; i < rules.length; i++) {
            rules[i].fuzzyA.a -= etaAntecedent * deltaRules[i].a;
            rules[i].fuzzyA.b -= etaAntecedent * deltaRules[i].b;
            rules[i].fuzzyB.a -= etaAntecedent * deltaRules[i].c;
            rules[i].fuzzyB.b -= etaAntecedent * deltaRules[i].d;

            rules[i].p -= etaConsequent * deltaRules[i].p;
            rules[i].q -= etaConsequent * deltaRules[i].q;
            rules[i].r -= etaConsequent * deltaRules[i].r;

            deltaRules[i].reset();
        }
    }

    private void initDeltaRules(int count) {
        deltaRules = new DeltaRule[count];
        for (int i = 0; i < deltaRules.length; i++) {
            deltaRules[i] = new DeltaRule();
        }
    }

    private double meanSquareError() {
        double sum = 0.0;
        int N = dataset.sampleCount();
        for (int i = 0; i < N; i++) {
            double difference = dataset.outputAt(i)
                    - anfis.calculateOutput(dataset.xAt(i), dataset.yAt(i));
            sum += difference * difference;
        }
        return sum / N;
    }

    private static class DeltaRule {

        double a;
        double b;
        double c;
        double d;
        double p;
        double q;
        double r;

        void reset() {
            a = 0.0;
            b = 0.0;
            c = 0.0;
            d = 0.0;
            p = 0.0;
            q = 0.0;
            r = 0.0;
        }
    }
}
