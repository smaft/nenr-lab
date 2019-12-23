package hr.fer.nenr.lab6.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class DatasetGenerator {

    private double xMin;
    private double xMax;
    private double xStep;

    private double yMin;
    private double yMax;
    private double yStep;

    private DoubleBinaryOperator function;

    public DatasetGenerator(double xMin, double xMax, double xStep,
            double yMin, double yMax, double yStep,
            DoubleBinaryOperator function) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.xStep = xStep;
        this.yMin = yMin;
        this.yMax = yMax;
        this.yStep = yStep;
        this.function = function;
    }

    public DatasetGenerator(double min, double max, double step,
            DoubleBinaryOperator function) {
        this(min, max, step, min, max, step, function);
    }

    public Dataset generate() {
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        List<Double> fList = new ArrayList<>();

        for (double x = xMin; x <= xMax; x += xStep) {
            for (double y = yMin; y <= yMax; y += yStep) {
                xList.add(x);
                yList.add(y);
                fList.add(function.applyAsDouble(x, y));
            }
        }

        return new ReadOnlyDataset(
                xList.stream().mapToDouble(Double::doubleValue).toArray(),
                yList.stream().mapToDouble(Double::doubleValue).toArray(),
                fList.stream().mapToDouble(Double::doubleValue).toArray()
        );
    }
}
