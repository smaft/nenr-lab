package hr.fer.nenr.lab4.algorithm.mutation;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

public class MutableNormalRandomMutation implements MutableMutation<BetaVectorSolution> {

    private double mutationProbability;

    private double sigma;

    public MutableNormalRandomMutation(double mutationProbability, double sigma) {
        this(mutationProbability);
        this.sigma = sigma;
    }

    public MutableNormalRandomMutation(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    @Override
    public void mutate(BetaVectorSolution solution) {
        double[] beta = solution.getBeta();
        for (int i = 0; i < beta.length; i++) {
            if (RandomSingleton.getInstance().nextDouble() < mutationProbability) {
                beta[i] += RandomSingleton.getInstance().nextGaussian(sigma);
            }
        }
    }

    @Override
    public void setSigma(double sigma) {
        this.sigma = sigma;
    }
}
