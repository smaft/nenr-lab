package hr.fer.nenr.lab7.algorithm;

import hr.fer.nenr.lab7.solution.ObjectiveSolution;

public interface OptimizationAlgorithm<T extends ObjectiveSolution> {

    T optimize();
}
