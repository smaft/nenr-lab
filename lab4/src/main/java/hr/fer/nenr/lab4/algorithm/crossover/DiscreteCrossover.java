package hr.fer.nenr.lab4.algorithm.crossover;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

public class DiscreteCrossover implements Crossover<BetaVectorSolution> {

    @Override
    public BetaVectorSolution cross(BetaVectorSolution firstParent,
            BetaVectorSolution secondParent) {
        double[] firstBeta = firstParent.getBeta();
        double[] secondBeta = secondParent.getBeta();
        double[] childBeta = new double[firstBeta.length];

        for (int i = 0; i < childBeta.length; i++) {
            childBeta[i] = RandomSingleton.getInstance().nextBoolean() ?
                    firstBeta[i] : secondBeta[i];
        }

        return new BetaVectorSolution(childBeta);
    }
}
