package hr.fer.nenr.lab7.algorithm.crossover;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

public class SinglePointCrossover implements Crossover<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    @Override
    public DoubleArraySolution cross(DoubleArraySolution first, DoubleArraySolution second) {
        double[] firstChromosome = first.chromosome;
        double[] secondChromosome = second.chromosome;
        double[] childChromosome = new double[firstChromosome.length];

        if (rng.nextBoolean()) {
            double[] firstChromosomeCopy = firstChromosome;
            firstChromosome = secondChromosome;
            secondChromosome = firstChromosomeCopy;
        }

        int randomIndex = rng.nextInt(childChromosome.length);
        int i;
        for (i = 0; i < randomIndex; i++) {
            childChromosome[i] = firstChromosome[i];
        }
        for (; i < childChromosome.length; i++) {
            childChromosome[i] = secondChromosome[i];
        }

        return new DoubleArraySolution(childChromosome);
    }
}
