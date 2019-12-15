package hr.fer.nenr.lab5.preprocessing.helpers;

import hr.fer.nenr.lab5.math.Point;

import java.util.List;
import java.util.function.Function;

public class DoubleArrayConverter implements Function<List<Point>, double[]> {

    @Override
    public double[] apply(List<Point> points) {
        double[] values = new double[points.size() * 2];
        int i = 0;
        for (Point p : points) {
            values[i++] = p.x;
            values[i++] = p.y;
        }
        return values;
    }
}
