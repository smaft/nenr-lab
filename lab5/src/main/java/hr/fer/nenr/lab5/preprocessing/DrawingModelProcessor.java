package hr.fer.nenr.lab5.preprocessing;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import hr.fer.nenr.lab5.drawing.GestureModel;

import java.util.function.Consumer;

public class DrawingModelProcessor implements Consumer<GestureModel> {

    private DatasetModel datasetModel;
    private PointsToInputsConverter pointsToInputsConverter;

    public DrawingModelProcessor(DatasetModel datasetModel) {
        this.datasetModel = datasetModel;
        this.pointsToInputsConverter = new PointsToInputsConverter(datasetModel::getM);
    }

    @Override
    public void accept(GestureModel model) {
        datasetModel.addInput(pointsToInputsConverter.apply(model));
    }
}
