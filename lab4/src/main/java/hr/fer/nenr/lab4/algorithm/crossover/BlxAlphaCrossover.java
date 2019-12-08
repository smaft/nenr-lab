package hr.fer.nenr.lab4.algorithm.crossover;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

public class BlxAlphaCrossover implements Crossover<BetaVectorSolution> {

    private double alpha;

    public BlxAlphaCrossover(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public BetaVectorSolution cross(BetaVectorSolution firstParent,
            BetaVectorSolution secondParent) {
        double[] firstBeta = firstParent.getBeta();
        double[] secondBeta = secondParent.getBeta();
        double[] childBeta = new double[firstBeta.length];

        for (int i = 0; i < childBeta.length; i++) {
            double lower = firstBeta[i];
            double upper = secondBeta[i];

            if (lower > upper) {
                double lowerCopy = lower;
                lower = upper;
                upper = lowerCopy;
            }

            double extension = (upper - lower) * alpha;
            lower -= extension;
            upper += extension;
            childBeta[i] = RandomSingleton.getInstance().nextInRange(lower, upper);
        }

        return new BetaVectorSolution(childBeta);
    }
}
