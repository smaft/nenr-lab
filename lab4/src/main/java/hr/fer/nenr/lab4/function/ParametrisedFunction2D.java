package hr.fer.nenr.lab4.function;

public interface ParametrisedFunction2D<T> {

    double valueAt(double x, double y, T parameter);
}
