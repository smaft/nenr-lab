package hr.fer.nenr.lab4.algorithm;

import hr.fer.nenr.lab4.algorithm.annealing.GeometricTemperatureSchedule;
import hr.fer.nenr.lab4.algorithm.annealing.TemperatureSchedule;
import hr.fer.nenr.lab4.algorithm.crossover.BlxAlphaCrossover;
import hr.fer.nenr.lab4.algorithm.crossover.Crossover;
import hr.fer.nenr.lab4.algorithm.evaluator.BetaDatasetErrorEvaluator;
import hr.fer.nenr.lab4.algorithm.evaluator.Evaluator;
import hr.fer.nenr.lab4.algorithm.initializer.Initializer;
import hr.fer.nenr.lab4.algorithm.initializer.RandomInRangeInitializer;
import hr.fer.nenr.lab4.algorithm.mutation.MutableMutation;
import hr.fer.nenr.lab4.algorithm.mutation.MutableNormalRandomMutation;
import hr.fer.nenr.lab4.algorithm.selection.Selection;
import hr.fer.nenr.lab4.algorithm.selection.TournamentSelection;
import hr.fer.nenr.lab4.dataset.ReadOnlyDataset2D;
import hr.fer.nenr.lab4.solution.BetaVectorSolution;

import java.util.*;

public class EliminationGeneticAlgorithm implements OptimizationAlgorithm<BetaVectorSolution> {

    private int populationSize;

    private Initializer<BetaVectorSolution> initializer = new RandomInRangeInitializer(-4, 4);

    private Evaluator<BetaVectorSolution> evaluator;

    private Selection<BetaVectorSolution> selection = new TournamentSelection(3);

    private Crossover<BetaVectorSolution> crossover = new BlxAlphaCrossover(0.33);

    private MutableMutation<BetaVectorSolution> mutation = new MutableNormalRandomMutation(0.05);

    private TemperatureSchedule temperatureSchedule;

    public EliminationGeneticAlgorithm(int populationSize, int maxIteration,
            ReadOnlyDataset2D dataset) {
        this.populationSize = populationSize;
        this.evaluator = new BetaDatasetErrorEvaluator(dataset);
        this.temperatureSchedule = new GeometricTemperatureSchedule(100, maxIteration / 100, 1.2, 0.4);
    }

    public BetaVectorSolution solve() {
        List<BetaVectorSolution> population = initializer.generateInitialPopulation(populationSize);
        population.forEach(evaluator::evaluate);

        Collections.sort(population);
        BetaVectorSolution currentBest = population.get(0);

        final int outerLimit = temperatureSchedule.getOuterLoopCount();
        final int innerLimit = temperatureSchedule.getInnerLoopCount();

        loop:
        for (int outer = 0; outer < outerLimit; outer++) {
            mutation.setSigma(temperatureSchedule.nextTemperature());
            for (int inner = 0; inner < innerLimit; inner++) {
                Set<BetaVectorSolution> free = new HashSet<>(populationSize);
                free.addAll(population);

                for (int i = 0; i < populationSize - 2; i++) {
                    List<BetaVectorSolution> parents = selection.select(population);
                    cleanParents(parents, free);
                    Collections.sort(parents);

                    BetaVectorSolution child = crossover.cross(
                            parents.get(0), parents.get(1));
                    mutation.mutate(child);
                    evaluator.evaluate(child);

                    BetaVectorSolution worstParent = parents.get(2);
                    if (child.error < worstParent.error) {
                        population.remove(worstParent);
                        population.add(child);

                        if (child.error < currentBest.error) {
                            currentBest = child;
                            System.out.println("[" + (outer * innerLimit + inner)
                                    + "] " + currentBest.error);
                            if (currentBest.error == 0.0) break loop;
                        }
                    }
                }
            }
        }

        Collections.sort(population);
        return population.get(0);
    }

    private void cleanParents(List<BetaVectorSolution> parents,
            Set<BetaVectorSolution> free) {
        for (int i = 0; i < parents.size(); i++) {
            BetaVectorSolution parent = parents.get(i);
            if (!free.contains(parent)) {
                Iterator<BetaVectorSolution> iterator = free.iterator();
                BetaVectorSolution any = iterator.next();
//                iterator.remove();
                parents.set(i, any);
            }
        }
    }
}
