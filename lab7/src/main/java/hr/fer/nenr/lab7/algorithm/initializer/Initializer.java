package hr.fer.nenr.lab7.algorithm.initializer;

import hr.fer.nenr.lab7.solution.ObjectiveSolution;

import java.util.List;

public interface Initializer<T extends ObjectiveSolution> {

    List<T> generatePopulation(int size);
}
