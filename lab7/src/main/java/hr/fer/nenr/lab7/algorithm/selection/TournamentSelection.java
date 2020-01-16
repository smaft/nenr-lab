package hr.fer.nenr.lab7.algorithm.selection;

import hr.fer.nenr.lab7.rng.RandomSingleton;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements Selection<DoubleArraySolution> {

    private RandomSingleton rng = RandomSingleton.getInstance();

    private int n;

    public TournamentSelection(int n) {
        this.n = n;
    }

    @Override
    public List<DoubleArraySolution> select(List<DoubleArraySolution> population) {
        List<DoubleArraySolution> selected = new ArrayList<>(n);
        int size = population.size();

        for (int i = 0; i < n; i++) {
            DoubleArraySolution solution;
            do {
                int index = rng.nextInt(size);
                solution = population.get(index);
            } while (selected.contains(solution));

            selected.add(solution);
        }

        return selected;
    }
}
