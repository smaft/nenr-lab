package hr.fer.nenr.lab4.algorithm.annealing;

public interface TemperatureSchedule {

    int getInnerLoopCount();

    int getOuterLoopCount();

    double nextTemperature();
}
