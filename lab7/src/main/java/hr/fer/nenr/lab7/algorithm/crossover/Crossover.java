package hr.fer.nenr.lab7.algorithm.crossover;

import hr.fer.nenr.lab7.solution.ObjectiveSolution;

public interface Crossover<T extends ObjectiveSolution> {

    T cross(T first, T second);
}
