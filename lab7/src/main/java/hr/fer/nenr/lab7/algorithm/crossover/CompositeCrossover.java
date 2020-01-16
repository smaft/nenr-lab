package hr.fer.nenr.lab7.algorithm.crossover;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

import java.util.Arrays;
import java.util.List;

public class CompositeCrossover implements Crossover<DoubleArraySolution> {

    private static final double ALPHA = 0.33;

    private RandomSingleton rng = RandomSingleton.getInstance();

    private List<Crossover<DoubleArraySolution>> crossovers = Arrays.asList(
            new BlxAlphaCrossover(ALPHA),
            new DiscreteCrossover(),
            new SinglePointCrossover()
    );

    @Override
    public DoubleArraySolution cross(DoubleArraySolution first, DoubleArraySolution second) {
        return crossovers.get(rng.nextInt(crossovers.size())).cross(first, second);
    }
}
