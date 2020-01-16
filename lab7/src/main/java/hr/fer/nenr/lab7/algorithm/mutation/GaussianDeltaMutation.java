package hr.fer.nenr.lab7.algorithm.mutation;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

public class GaussianDeltaMutation implements Mutation<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    private double probability;
    private double sigma;

    public GaussianDeltaMutation(double probability, double sigma) {
        this.probability = probability;
        this.sigma = sigma;
    }

    @Override
    public void mutate(DoubleArraySolution solution) {
        double[] chromosome = solution.chromosome;
        for (int i = 0; i < chromosome.length; i++) {
            if (rng.nextDouble() <= probability) {
                chromosome[i] += rng.nextGaussian(sigma);
            }
        }
    }
}
