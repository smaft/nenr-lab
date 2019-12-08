package hr.fer.nenr.lab4.algorithm.selection;

import hr.fer.nenr.lab4.solution.BetaVectorSolution;
import hr.fer.nenr.lab4.util.RandomSingleton;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements Selection<BetaVectorSolution> {

    private int n;

    public TournamentSelection(int n) {
        this.n = n;
    }

    @Override
    public List<BetaVectorSolution> select(List<BetaVectorSolution> population) {
        List<BetaVectorSolution> selected = new ArrayList<>(n);
        int size = population.size();

        for (int i = 0; i < n; i++) {
            BetaVectorSolution solution;
            do {
                int index = RandomSingleton.getInstance().nextInt(size);
                solution = population.get(index);
            } while (selected.contains(solution));

            selected.add(solution);
        }

        return selected;
    }
}
