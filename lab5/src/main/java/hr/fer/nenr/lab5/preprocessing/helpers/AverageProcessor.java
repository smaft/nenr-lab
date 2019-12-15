package hr.fer.nenr.lab5.preprocessing.helpers;

import hr.fer.nenr.lab5.math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AverageProcessor implements Function<List<Point>, List<Point>> {

    @Override
    public List<Point> apply(List<Point> points) {
        List<Point> transformed = new ArrayList<>(points.size());
        Point average = averagePoint(points);
        for (Point p : points) {
            transformed.add(new Point(p.x - average.x, p.y - average.y));
        }
        return transformed;
    }

    private Point averagePoint(List<Point> points) {
        double xSum = 0.0;
        double ySum = 0.0;
        for (Point p : points) {
            xSum += p.x;
            ySum += p.y;
        }

        int count = points.size();
        return new Point(xSum / count, ySum / count);
    }
}
