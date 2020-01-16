package hr.fer.nenr.lab7.algorithm;

import hr.fer.nenr.lab7.algorithm.crossover.Crossover;
import hr.fer.nenr.lab7.algorithm.evaluator.Evaluator;
import hr.fer.nenr.lab7.algorithm.initializer.Initializer;
import hr.fer.nenr.lab7.algorithm.mutation.Mutation;
import hr.fer.nenr.lab7.algorithm.selection.Selection;
import hr.fer.nenr.lab7.solution.ObjectiveSolution;

public abstract class GeneticAlgorithm<T extends ObjectiveSolution> implements OptimizationAlgorithm<T> {

    protected Initializer<T> initializer;
    protected Evaluator<T> evaluator;
    protected Selection<T> selection;
    protected Crossover<T> crossover;
    protected Mutation<T> mutation;

    public GeneticAlgorithm(Initializer<T> initializer, Evaluator<T> evaluator,
            Selection<T> selection, Crossover<T> crossover, Mutation<T> mutation) {
        this.initializer = initializer;
        this.evaluator = evaluator;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
    }
}
