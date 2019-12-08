package hr.fer.nenr.lab4.algorithm.crossover;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

public interface Crossover<T extends ObjectiveSolution> {

    T cross(T firstParent, T secondParent);
}
