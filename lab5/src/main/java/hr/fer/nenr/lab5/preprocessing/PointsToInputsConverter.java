package hr.fer.nenr.lab5.preprocessing;

import hr.fer.nenr.lab5.drawing.GestureModel;
import hr.fer.nenr.lab5.preprocessing.helpers.*;

import java.util.function.Function;
import java.util.function.IntSupplier;

public class PointsToInputsConverter implements Function<GestureModel, double[]> {

    private PointListExtractor pointListExtractor = new PointListExtractor();
    private AverageProcessor averageProcessor = new AverageProcessor();
    private ScaleProcessor scaleProcessor = new ScaleProcessor();
    private MSampleProcessor mSampleProcessor;
    private DoubleArrayConverter doubleArrayConverter = new DoubleArrayConverter();

    public PointsToInputsConverter(IntSupplier mSupplier) {
        mSampleProcessor = new MSampleProcessor(mSupplier);
    }

    public PointsToInputsConverter(int m) {
        this(() -> m);
    }

    @Override
    public double[] apply(GestureModel model) {
        return doubleArrayConverter.apply(
                mSampleProcessor.apply(
                        scaleProcessor.apply(
                                averageProcessor.apply(
                                        pointListExtractor.apply(model)))));
    }
}
