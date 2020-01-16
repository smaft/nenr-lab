package hr.fer.nenr.lab7.algorithm.crossover;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

public class BlxAlphaCrossover implements Crossover<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    private double alpha;

    public BlxAlphaCrossover(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public DoubleArraySolution cross(DoubleArraySolution first, DoubleArraySolution second) {
        double[] firstChromosome = first.chromosome;
        double[] secondChromosome = second.chromosome;
        double[] childChromosome = new double[firstChromosome.length];

        for (int i = 0; i < childChromosome.length; i++) {
            double lower = firstChromosome[i];
            double upper = secondChromosome[i];

            if (lower > upper) {
                double lowerCopy = lower;
                lower = upper;
                upper = lowerCopy;
            }

            double extension = (upper - lower) * alpha;
            lower -= extension;
            upper += extension;
            childChromosome[i] = rng.nextInRange(lower, upper);
        }

        return new DoubleArraySolution(childChromosome);
    }
}
