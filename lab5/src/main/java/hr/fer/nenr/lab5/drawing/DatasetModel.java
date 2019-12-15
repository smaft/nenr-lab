package hr.fer.nenr.lab5.drawing;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.dataset.ReadOnlyDataset;
import hr.fer.nenr.lab5.dataset.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatasetModel implements Iterable<List<double[]>> {

    private static final int M_DEFAULT = 20;

    private int m = M_DEFAULT;
    private int selectedIndex = -1;
    private List<List<double[]>> classes = new ArrayList<>();
    private List<DatasetModelListener> listeners = new ArrayList<>();

    public DatasetModel() {
        addClass();
    }

    @Override
    public Iterator<List<double[]>> iterator() {
        return classes.iterator();
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public boolean isEmpty() {
        return classes.isEmpty() || classes.stream().allMatch(List::isEmpty);
    }

    public void addClass() {
        classes.add(new ArrayList<>());
        selectedIndex++;
        fireClassAdded();
    }

    public void addInput(double[] input) {
        classes.get(selectedIndex).add(input);
        fireInputAdded(selectedIndex);
    }

    public Dataset toDataset() {
        List<double[]> inputs = new ArrayList<>();
        List<double[]> outputs = new ArrayList<>();

        int classCount = classes.size();
        for (int i = 0; i < classCount; i++) {
            List<double[]> currentClassInputs = classes.get(i);
            inputs.addAll(currentClassInputs);

            for (int j = 0; j < currentClassInputs.size(); j++) {
                double[] output = new double[classCount];
                output[i] = 1.0;
                outputs.add(output);
            }
        }

        return new ReadOnlyDataset(inputs, outputs);
    }

    public void reset(Dataset dataset) {
        classes.clear();
        for (int i = 0; i < dataset.outputDimension(); i++) {
            classes.add(new ArrayList<>());
        }
        selectedIndex = classes.size() - 1;
        m = dataset.inputDimension() / 2;

        for (int i = 0; i < dataset.sampleCount(); i++) {
            int classIndex = Util.classIndex(dataset.outputAt(i));
            classes.get(classIndex).add(dataset.inputAt(i));
        }

        fireDatasetReset();
    }

    public void reset() {
        classes.clear();
        classes.add(new ArrayList<>());
        selectedIndex = 0;
        fireDatasetReset();
    }

    public void addListener(DatasetModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(DatasetModelListener listener) {
        listeners.remove(listener);
    }

    private void fireClassAdded() {
        listeners.forEach(DatasetModelListener::classAdded);
    }

    private void fireInputAdded(int classIndex) {
        listeners.forEach(l -> l.inputAdded(classIndex));
    }

    private void fireDatasetReset() {
        listeners.forEach(DatasetModelListener::datasetReset);
    }
}
