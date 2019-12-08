package hr.fer.nenr.lab4.algorithm.evaluator;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

public interface Evaluator<T extends ObjectiveSolution> {

    void evaluate(T solution);
}
