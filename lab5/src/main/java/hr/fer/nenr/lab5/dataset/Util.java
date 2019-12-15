package hr.fer.nenr.lab5.dataset;

public final class Util {

    private Util() {
    }

    public static int classIndex(double[] output) {
        int index = -1;
        for (int i = 0; i < output.length; i++) {
            if (output[i] == 1.0) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean miniBatchesPossible(Dataset dataset) {
        int[] samplesPerClass = new int[dataset.outputAt(0).length];
        for (int i = 0; i < dataset.sampleCount(); i++) {
            samplesPerClass[Util.classIndex(dataset.outputAt(i))]++;
        }

        int samplesInFirst = samplesPerClass[0];
        if (samplesInFirst % 2 != 0) return false;

        for (int i = 1; i < samplesPerClass.length; i++) {
            int samples = samplesPerClass[i];
            if (samples != samplesInFirst) return false;
        }

        return true;
    }
}
