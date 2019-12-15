package hr.fer.nenr.lab5.preprocessing.helpers;

import hr.fer.nenr.lab5.drawing.GestureModel;
import hr.fer.nenr.lab5.math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PointListExtractor implements Function<GestureModel, List<Point>> {

    @Override
    public List<Point> apply(GestureModel model) {
        List<Point> points = new ArrayList<>(model.size());
        for (Point p : model) {
            points.add(p);
        }
        return points;
    }
}
