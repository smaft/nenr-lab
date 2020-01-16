package hr.fer.nenr.lab7.algorithm.evaluator;

import hr.fer.nenr.lab7.solution.ObjectiveSolution;

public interface Evaluator<T extends ObjectiveSolution> {

    void evaluate(T solution);
}
