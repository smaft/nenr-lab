package hr.fer.nenr.lab4.algorithm.selection;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheelSelection implements Selection<BetaVectorSolution> {

    @Override
    public List<BetaVectorSolution> select(List<BetaVectorSolution> population) {
        List<BetaVectorSolution> selected = new ArrayList<>(2);
        selected.add(selectSingle(population));

        BetaVectorSolution single;
//        do {
            single = selectSingle(population);
//        } while (selected.contains(single));
        selected.add(single);

        return selected;
    }

    private BetaVectorSolution selectSingle(List<BetaVectorSolution> population) {
        double fitnessSum = 0.0;
        for (BetaVectorSolution solution : population) {
            fitnessSum += 1.0 / solution.error;
        }

        double value = RandomSingleton.getInstance().nextDouble() * fitnessSum;
        for (BetaVectorSolution solution : population) {
            value -= 1.0 / solution.error;
            if (value < 0) return solution;
        }

        return population.get(population.size() - 1); // Last element rounding errors.
    }
}
