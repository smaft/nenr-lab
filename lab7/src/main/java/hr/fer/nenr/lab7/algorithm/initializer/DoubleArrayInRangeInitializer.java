package hr.fer.nenr.lab7.algorithm.initializer;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

import java.util.ArrayList;
import java.util.List;

public class DoubleArrayInRangeInitializer implements Initializer<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    private int chromosomeLength;

    private double min;
    private double max;

    public DoubleArrayInRangeInitializer(int chromosomeLength, double min, double max) {
        this.chromosomeLength = chromosomeLength;
        this.min = min;
        this.max = max;
    }

    @Override
    public List<DoubleArraySolution> generatePopulation(int size) {
        List<DoubleArraySolution> population = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            population.add(new DoubleArraySolution(generateChromosome()));
        }
        return population;
    }

    private double[] generateChromosome() {
        double[] chromosome = new double[chromosomeLength];
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = rng.nextInRange(min, max);
        }
        return chromosome;
    }
}
