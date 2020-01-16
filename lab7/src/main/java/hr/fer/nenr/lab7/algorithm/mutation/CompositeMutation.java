package hr.fer.nenr.lab7.algorithm.mutation;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

import java.util.Arrays;
import java.util.List;

public class CompositeMutation implements Mutation<DoubleArraySolution> {

    private static final double PROBABILITY = 0.03;

    private static final double SIGMA_1 = 0.2;
    private static final double SIGMA_2 = 2.0;
    private static final double SIGMA_3 = 1.0;

    private RandomSingleton rng = RandomSingleton.getInstance();

    private List<Mutation<DoubleArraySolution>> mutations = Arrays.asList(
            new GaussianDeltaMutation(PROBABILITY, SIGMA_1),
            new GaussianDeltaMutation(PROBABILITY, SIGMA_2),
            new GaussianValueMutation(PROBABILITY, SIGMA_3)
    );

    private double[] choiceProbabilities;

    public CompositeMutation(double... preferredChoiceIntensities) {
        if (preferredChoiceIntensities == null
                || preferredChoiceIntensities.length != mutations.size()) {
            throw new IllegalArgumentException(
                    "Expected " + mutations.size() + " preferred choice intensity values"
            );
        }

        initChoiceProbabilities(preferredChoiceIntensities);
    }

    @Override
    public void mutate(DoubleArraySolution solution) {
        double randomValue = rng.nextDouble();
        for (int i = 0; i < choiceProbabilities.length; i++) {
            if (randomValue >= choiceProbabilities[i]) {
                mutations.get(i).mutate(solution);
                return;
            }
        }

        mutations.get(mutations.size() - 1).mutate(solution); // Rounding errors
    }

    private void initChoiceProbabilities(double[] preferredChoiceIntensities) {
        double sum = 0.0;
        for (int i = 0; i < preferredChoiceIntensities.length; i++) {
            sum += preferredChoiceIntensities[i];
        }

        choiceProbabilities = new double[preferredChoiceIntensities.length];
        for (int i = 0; i < choiceProbabilities.length; i++) {
            choiceProbabilities[i] = preferredChoiceIntensities[i] / sum;
        }
    }
}
