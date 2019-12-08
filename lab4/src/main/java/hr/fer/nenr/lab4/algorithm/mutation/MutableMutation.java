package hr.fer.nenr.lab4.algorithm.mutation;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

public interface MutableMutation<T extends ObjectiveSolution> extends Mutation<T> {

    void setSigma(double sigma);
}
