package hr.fer.nenr.lab5.drawing;

import hr.fer.nenr.lab5.math.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestureModel implements Iterable<Point> {

    private List<Point> points = new ArrayList<>();
    private List<GestureModelListener> listeners = new ArrayList<>();

    @Override
    public Iterator<Point> iterator() {
        return points.iterator();
    }

    public int size() {
        return points.size();
    }

    public Point get(int index) {
        return points.get(index);
    }

    public void add(Point point) {
        points.add(point);
        fireListeners();
    }

    public void clear() {
        points.clear();
        fireListeners();
    }

    public void addListener(GestureModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GestureModelListener listener) {
        listeners.remove(listener);
    }

    private void fireListeners() {
        listeners.forEach(GestureModelListener::onChange);
    }
}
