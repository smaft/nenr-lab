package hr.fer.nenr.lab4.algorithm.initializer;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

import java.util.ArrayList;
import java.util.List;

public class RandomInRangeInitializer implements Initializer<BetaVectorSolution> {

    private double minBeta;

    private double maxBeta;

    public RandomInRangeInitializer(double minBeta, double maxBeta) {
        this.minBeta = minBeta;
        this.maxBeta = maxBeta;
    }

    @Override
    public List<BetaVectorSolution> generateInitialPopulation(int size) {
        List<BetaVectorSolution> population = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            population.add(new BetaVectorSolution(
                    RandomSingleton.getInstance().nextInRange(minBeta, maxBeta),
                    RandomSingleton.getInstance().nextInRange(minBeta, maxBeta),
                    RandomSingleton.getInstance().nextInRange(minBeta, maxBeta),
                    RandomSingleton.getInstance().nextInRange(minBeta, maxBeta),
                    RandomSingleton.getInstance().nextInRange(minBeta, maxBeta)
            ));
        }
        return population;
    }
}
