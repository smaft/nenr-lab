package hr.fer.nenr.lab7.algorithm.mutation;

import hr.fer.nenr.lab7.solution.ObjectiveSolution;

public interface Mutation<T extends ObjectiveSolution> {

    void mutate(T solution);
}
