package hr.fer.nenr.lab4.algorithm.mutation;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

public interface Mutation<T extends ObjectiveSolution> {

    void mutate(T solution);
}
