package hr.fer.nenr.lab7.algorithm;

import hr.fer.nenr.lab7.algorithm.crossover.CompositeCrossover;
import hr.fer.nenr.lab7.algorithm.evaluator.NeuralNetworkDatasetEvaluator;
import hr.fer.nenr.lab7.algorithm.initializer.DoubleArrayInRangeInitializer;
import hr.fer.nenr.lab7.algorithm.mutation.CompositeMutation;
import hr.fer.nenr.lab7.algorithm.selection.TournamentSelection;
import hr.fer.nenr.lab7.dataset.Dataset;
import hr.fer.nenr.lab7.neural.NeuralNetwork;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

import java.util.*;

public class EliminationGeneticAlgorithm extends GeneticAlgorithm<DoubleArraySolution> {

    private static final double INIT_MIN = -1.0;
    private static final double INIT_MAX = 1.0;

    private static final int TOURNAMENT_SIZE = 3;

    private static final double MUTATION_T1 = 4;
    private static final double MUTATION_T2 = 2;
    private static final double MUTATION_T3 = 1;

    private int populationSize;
    private int maxIteration;
    private double minError;

    public EliminationGeneticAlgorithm(NeuralNetwork network, Dataset dataset,
            int populationSize, int maxIteration, double minError) {
        super(
                new DoubleArrayInRangeInitializer(network.getParameterCount(), INIT_MIN, INIT_MAX),
                new NeuralNetworkDatasetEvaluator(network, dataset),
                new TournamentSelection(TOURNAMENT_SIZE),
                new CompositeCrossover(),
                new CompositeMutation(MUTATION_T1, MUTATION_T2, MUTATION_T3)
        );

        this.populationSize = populationSize;
        this.maxIteration = maxIteration;
        this.minError = minError;
    }

    @Override
    public DoubleArraySolution optimize() {
        List<DoubleArraySolution> population = initializer.generatePopulation(populationSize);
        population.forEach(evaluator::evaluate);

        Collections.sort(population);
        DoubleArraySolution currentBest = population.get(0);

        Set<DoubleArraySolution> free = new HashSet<>(populationSize);
        outer:
        for (int iteration = 1; iteration <= maxIteration; iteration++) {
            free.clear();
            free.addAll(population);

            for (int i = 0; i < populationSize - 2; i++) {
                List<DoubleArraySolution> parents = selection.select(population);
                cleanParents(parents, free);
                Collections.sort(parents);

                DoubleArraySolution child = crossover.cross(parents.get(0), parents.get(1));
                mutation.mutate(child);
                evaluator.evaluate(child);

                DoubleArraySolution worstParent = parents.get(2);
                if (child.error < worstParent.error) {
                    population.remove(worstParent);
                    population.add(child);

                    if (child.error < currentBest.error) {
                        currentBest = child;
                        System.out.println("[" + iteration + "] " + currentBest.error);

                        if (currentBest.error <= minError) break outer;
                    }
                }
            }
        }

        Collections.sort(population);
        return population.get(0);
    }

    private void cleanParents(List<DoubleArraySolution> parents, Set<DoubleArraySolution> free) {
        for (int i = 0; i < parents.size(); i++) {
            DoubleArraySolution parent = parents.get(i);
            if (!free.contains(parent)) {
                Iterator<DoubleArraySolution> iterator = free.iterator();
                if (iterator.hasNext()) {
                    DoubleArraySolution any = iterator.next();
                    iterator.remove();
                    parents.set(i, any);
                }
            }
        }
    }
}
