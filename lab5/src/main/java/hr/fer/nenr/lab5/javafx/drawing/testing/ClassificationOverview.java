package hr.fer.nenr.lab5.javafx.drawing.testing;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class ClassificationOverview extends BarChart<String, Number> {

    private Series<String, Number> series;

    public ClassificationOverview(int classCount) {
        super(new CategoryAxis(), new NumberAxis(0.0, 1.0, 0.5));

        series = new Series<>();
        for (int i = 1; i <= classCount; i++) {
            series.getData().add(new Data<>(String.valueOf(i), 0.0));
        }
        //noinspection unchecked
        getData().addAll(series);

        setMinHeight(0.0);
        setMaxHeight(100.0);
    }

    public Series<String, Number> getSeries() {
        return series;
    }
}
