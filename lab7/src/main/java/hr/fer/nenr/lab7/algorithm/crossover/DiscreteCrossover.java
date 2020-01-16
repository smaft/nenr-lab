package hr.fer.nenr.lab7.algorithm.crossover;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

public class DiscreteCrossover implements Crossover<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    @Override
    public DoubleArraySolution cross(DoubleArraySolution first, DoubleArraySolution second) {
        double[] firstChromosome = first.chromosome;
        double[] secondChromosome = second.chromosome;
        double[] childChromosome = new double[firstChromosome.length];

        for (int i = 0; i < childChromosome.length; i++) {
            childChromosome[i] = rng.nextBoolean() ? firstChromosome[i] : secondChromosome[i];
        }

        return new DoubleArraySolution(childChromosome);
    }
}
