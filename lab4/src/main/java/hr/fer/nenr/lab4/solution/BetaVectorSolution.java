package hr.fer.nenr.lab4.solution;

import java.util.Arrays;

public class BetaVectorSolution extends ObjectiveSolution {

    private double[] beta;

    public BetaVectorSolution(double... beta) {
        this.beta = beta;
    }

    public double[] getBeta() {
        return beta;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(beta);
    }
}
