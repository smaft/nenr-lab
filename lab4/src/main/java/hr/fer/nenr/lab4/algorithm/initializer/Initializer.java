package hr.fer.nenr.lab4.algorithm.initializer;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

import java.util.List;

public interface Initializer<T extends ObjectiveSolution> {

    List<T> generateInitialPopulation(int size);
}
