package hr.fer.nenr.lab4.algorithm.evaluator;

import hr.fer.nenr.lab4.dataset.ReadOnlyDataset2D;
import hr.fer.nenr.lab4.function.BetaVectorFunction;
import hr.fer.nenr.lab4.solution.BetaVectorSolution;

public class BetaDatasetErrorEvaluator implements Evaluator<BetaVectorSolution> {

    private BetaVectorFunction function = new BetaVectorFunction();

    private ReadOnlyDataset2D dataset;

    public BetaDatasetErrorEvaluator(ReadOnlyDataset2D dataset) {
        this.dataset = dataset;
    }

    @Override
    public void evaluate(BetaVectorSolution solution) {
        solution.error = calculateError(solution);
    }

    private double calculateError(BetaVectorSolution solution) {
        double error = 0.0;
        int sampleSize = dataset.getSampleSize();
        double[] beta = solution.getBeta();

        for (int i = 0; i < sampleSize; i++) {
            double x = dataset.xInputAt(i);
            double y = dataset.yInputAt(i);

            double expected = dataset.outputAt(i);
            double real = function.valueAt(x, y, beta);

            double delta = expected - real;
            error += delta * delta;
        }

        return error / sampleSize;
    }
}
