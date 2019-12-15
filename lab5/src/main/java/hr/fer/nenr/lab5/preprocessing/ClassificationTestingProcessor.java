package hr.fer.nenr.lab5.preprocessing;

import hr.fer.nenr.lab5.drawing.GestureModel;
import hr.fer.nenr.lab5.neural.NeuralNetwork;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.function.Consumer;

public class ClassificationTestingProcessor implements Consumer<GestureModel> {

    private NeuralNetwork network;
    private PointsToInputsConverter pointsToInputsConverter;
    private XYChart.Series<String, Number> overviewSeries;

    public ClassificationTestingProcessor(NeuralNetwork network,
            XYChart.Series<String, Number> overviewSeries) {
        this.network = network;
        this.overviewSeries = overviewSeries;

        int m = network.getLayers().get(0).outputs.length / 2;
        pointsToInputsConverter = new PointsToInputsConverter(m);
    }

    @Override
    public void accept(GestureModel model) {
        double[] output = network.calculateOutput(
                pointsToInputsConverter.apply(model)
        );

        ObservableList<XYChart.Data<String, Number>> list = overviewSeries.getData();
        for (int i = 0; i < output.length; i++) {
            XYChart.Data<String, Number> data = list.get(i);
            data.setYValue(output[i]);
        }
    }
}
