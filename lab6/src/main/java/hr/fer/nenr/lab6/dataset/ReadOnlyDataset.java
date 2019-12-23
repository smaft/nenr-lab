package hr.fer.nenr.lab6.dataset;

public class ReadOnlyDataset implements Dataset {

    private double[] x;
    private double[] y;
    private double[] f;

    public ReadOnlyDataset(double[] x, double[] y, double[] f) {
        this.x = x;
        this.y = y;
        this.f = f;
    }

    @Override
    public double xAt(int index) {
        return x[index];
    }

    @Override
    public double yAt(int index) {
        return y[index];
    }

    @Override
    public double outputAt(int index) {
        return f[index];
    }

    @Override
    public int sampleCount() {
        return f.length;
    }
}
