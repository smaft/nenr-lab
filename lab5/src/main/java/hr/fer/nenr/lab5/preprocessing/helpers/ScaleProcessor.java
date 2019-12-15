package hr.fer.nenr.lab5.preprocessing.helpers;

import hr.fer.nenr.lab5.math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ScaleProcessor implements Function<List<Point>, List<Point>> {

    @Override
    public List<Point> apply(List<Point> points) {
        List<Point> transformed = new ArrayList<>(points.size());
        double xMax = -Double.MAX_VALUE;
        double yMax = -Double.MAX_VALUE;
        for (Point p : points) {
            double x = Math.abs(p.x);
            double y = Math.abs(p.y);

            if (x > xMax) xMax = x;
            if (y > yMax) yMax = y;
        }

        double max = Double.max(xMax, yMax);
        for (Point p : points) {
            transformed.add(new Point(p.x / max, p.y / max));
        }

        return transformed;
    }
}
