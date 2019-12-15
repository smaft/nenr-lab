package hr.fer.nenr.lab5.preprocessing.helpers;

import hr.fer.nenr.lab5.math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntSupplier;

import static hr.fer.nenr.lab5.math.MathUtil.distance;
import static hr.fer.nenr.lab5.math.MathUtil.sum;

public class MSampleProcessor implements Function<List<Point>, List<Point>> {

    private IntSupplier mSupplier;

    public MSampleProcessor(IntSupplier mSupplier) {
        this.mSupplier = mSupplier;
    }

    @Override
    public List<Point> apply(List<Point> points) {
        List<Point> transformed = new ArrayList<>();
        transformed.add(points.get(0));

        int m = mSupplier.getAsInt();
        double[] distances = calculateDistances(points);
        double step = sum(distances) / (m - 1);
        double distanceFromStart = step;
        double previousIndexDistance = 0.0;
        int previousIndex = 0;

        for (int i = 1; i < m; i++) {
            while (previousIndexDistance < distanceFromStart
                    && previousIndex < distances.length - 1) {
                previousIndexDistance += distances[previousIndex++];
            }

            Point previous = points.get(previousIndex);
            Point next = points.get(previousIndex + 1);

            double alpha = previousIndexDistance;
            transformed.add(new Point(
                    alpha * previous.x + (1 - alpha) * next.x,
                    alpha * previous.y + (1 - alpha) * next.y
            ));

            distanceFromStart += step;
        }

        return transformed;
    }

    private double[] calculateDistances(List<Point> points) {
        double[] distances = new double[points.size() - 1];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = distance(points.get(i), points.get(i + 1));
        }
        return distances;
    }
}
