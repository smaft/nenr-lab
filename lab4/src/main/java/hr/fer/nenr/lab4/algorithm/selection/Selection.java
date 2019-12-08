package hr.fer.nenr.lab4.algorithm.selection;

import hr.fer.nenr.lab4.solution.ObjectiveSolution;

import java.util.List;

public interface Selection<T extends ObjectiveSolution> {

    List<T> select(List<T> population);
}
