package hr.fer.nenr.lab4.algorithm;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

public interface OptimizationAlgorithm<T extends ObjectiveSolution> {

    T solve();
}
