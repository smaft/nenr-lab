package hr.fer.nenr.lab4.algorithm.mutation;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

public class NormalRandomMutation implements Mutation<BetaVectorSolution> {

    private double mutationProbability;

    private double sigma;

    public NormalRandomMutation(double mutationProbability, double sigma) {
        this.mutationProbability = mutationProbability;
        this.sigma = sigma;
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
}
