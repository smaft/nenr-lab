package hr.fer.nenr.lab4.function;

import static java.lang.Math.*;

public class BetaVectorFunction implements ParametrisedFunction2D<double[]> {

    @Override
    public double valueAt(double x, double y, double[] beta) {
        return sin(beta[0] + beta[1] * x)
                + beta[2] * cos(x * (beta[3] + y))
                * 1.0 / (1 + exp(pow(x - beta[4], 2)));
    }
}
