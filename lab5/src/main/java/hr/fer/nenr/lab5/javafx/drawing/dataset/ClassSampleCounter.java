package hr.fer.nenr.lab5.javafx.drawing.dataset;

public class ClassSampleCounter {

    private static int classCount;

    int id;
    int sampleCount;

    static {
        reset();
    }

    static void reset() {
        classCount = 1;
    }

    public ClassSampleCounter() {
        this.id = classCount++;
    }

    public ClassSampleCounter(int sampleCount) {
        this();
        this.sampleCount = sampleCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }
}
