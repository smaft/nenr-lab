package hr.fer.nenr.lab4.algorithm.mutation;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

public class UniformRandomMutation implements Mutation<BetaVectorSolution> {

    private double mutationProbability;

    private double min;

    private double max;

    public UniformRandomMutation(double mutationProbability, double min, double max) {
        this.mutationProbability = mutationProbability;
        this.min = min;
        this.max = max;
    }

    @Override
    public void mutate(BetaVectorSolution solution) {
        double[] beta = solution.getBeta();
        for (int i = 0; i < beta.length; i++) {
            if (RandomSingleton.getInstance().nextDouble() < mutationProbability) {
                beta[i] += RandomSingleton.getInstance().nextInRange(min, max);
            }
        }
    }
}
