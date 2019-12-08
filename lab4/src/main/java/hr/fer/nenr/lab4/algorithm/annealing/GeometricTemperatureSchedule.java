package hr.fer.nenr.lab4.algorithm.annealing;

public class GeometricTemperatureSchedule implements TemperatureSchedule {

    private final int innerLimit;

    private final int outerLimit;

    private double alpha;

    private double tInitial;

    private double tCurrent;

    public GeometricTemperatureSchedule(int innerLimit, int outerLimit,
            double tInitial, double tFinal) {
        this.innerLimit = innerLimit;
        this.outerLimit = outerLimit;
        this.tInitial = tInitial;
        this.tCurrent = tInitial;
        calculateAlpha(tFinal);
    }

    @Override
    public int getInnerLoopCount() {
        return innerLimit;
    }

    @Override
    public int getOuterLoopCount() {
        return outerLimit;
    }

    @Override
    public double nextTemperature() {
        tCurrent *= alpha;
        return tCurrent;
    }

    private void calculateAlpha(double tFinal) {
        if (tFinal == 0.0) tFinal = 1;
        alpha = Math.pow(tFinal / tInitial, 1.0 / (outerLimit - 1));
    }
}
