package hr.fer.nenr.lab4.algorithm;

import hr.fer.nenr.lab4.algorithm.crossover.BlxAlphaCrossover;
import hr.fer.nenr.lab4.algorithm.crossover.Crossover;
import hr.fer.nenr.lab4.algorithm.evaluator.BetaDatasetErrorEvaluator;
import hr.fer.nenr.lab4.algorithm.evaluator.Evaluator;
import hr.fer.nenr.lab4.algorithm.initializer.Initializer;
import hr.fer.nenr.lab4.algorithm.initializer.RandomInRangeInitializer;
import hr.fer.nenr.lab4.algorithm.mutation.Mutation;
import hr.fer.nenr.lab4.algorithm.mutation.NormalRandomMutation;
import hr.fer.nenr.lab4.algorithm.selection.RouletteWheelSelection;
import hr.fer.nenr.lab4.algorithm.selection.Selection;
import hr.fer.nenr.lab4.dataset.ReadOnlyDataset2D;
import hr.fer.nenr.lab4.solution.BetaVectorSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerationalGeneticAlgorithm implements OptimizationAlgorithm<BetaVectorSolution> {

    private int populationSize;

    private int maxIteration;

    private boolean elitism;

    private Initializer<BetaVectorSolution> initializer = new RandomInRangeInitializer(-4, 4);

    private Evaluator<BetaVectorSolution> evaluator;

    private Selection<BetaVectorSolution> selection = new RouletteWheelSelection();

    private Crossover<BetaVectorSolution> crossover = new BlxAlphaCrossover(0.33);

    private Mutation<BetaVectorSolution> mutation = new NormalRandomMutation(0.2, 1.0);

    public GenerationalGeneticAlgorithm(int populationSize, int maxIteration,
            boolean elitism, ReadOnlyDataset2D dataset) {
        this.populationSize = populationSize;
        this.maxIteration = maxIteration;
        this.elitism = elitism;
        this.evaluator = new BetaDatasetErrorEvaluator(dataset);
    }

    public BetaVectorSolution solve() {
        List<BetaVectorSolution> population = initializer.generateInitialPopulation(populationSize);
        population.forEach(evaluator::evaluate);
        Collections.sort(population);

        for (int i = 0; i < maxIteration; i++) {
            List<BetaVectorSolution> children = new ArrayList<>(populationSize);

            if (elitism) {
                children.add(population.get(0));
            }

            while (children.size() < populationSize) {
                List<BetaVectorSolution> parents = selection.select(population);

                BetaVectorSolution child = crossover.cross(
                        parents.get(0), parents.get(1));
                mutation.mutate(child);
                evaluator.evaluate(child);

                children.add(child);
            }

            Collections.sort(children);
            if (children.get(0).error < population.get(0).error) {
                System.out.println("[" + i + "] " + children.get(0).error);
            }

            population = children;
        }

        return population.get(0);
    }
}
