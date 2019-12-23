package hr.fer.nenr.lab6.demo;

import hr.fer.nenr.lab6.dataset.Dataset;
import hr.fer.nenr.lab6.fuzzy.Rule;
import hr.fer.nenr.lab6.neurofuzzy.ANFIS;

public final class DemoUtil {

    private DemoUtil() {
    }

    public static void printStatistics(Dataset dataset, ANFIS anfis) {
        System.out.println();
        System.out.println("Predictions:");
        printPredictions(dataset, anfis);

        System.out.println();
        System.out.println("Errors:");
        printErrors(dataset, anfis);

        System.out.println();
        System.out.println("Fuzzy sets:");
        printRuleFuzzySets(anfis);
    }

    public static void printDataset(Dataset dataset) {
        System.out.println("# x y z");
        for (int i = 0; i < dataset.sampleCount(); i++) {
            double x = dataset.xAt(i);
            double y = dataset.yAt(i);
            double t = dataset.outputAt(i);

            System.out.println(x + " " + y + " " + t);
        }
    }

    public static void printPredictions(Dataset dataset, ANFIS anfis) {
        System.out.println("# x y z");
        for (int i = 0; i < dataset.sampleCount(); i++) {
            double x = dataset.xAt(i);
            double y = dataset.yAt(i);
            double o = anfis.calculateOutput(x, y);

            System.out.println(x + " " + y + " " + o);
        }
    }

    public static void printErrors(Dataset dataset, ANFIS anfis) {
        System.out.println("# x y z");
        for (int i = 0; i < dataset.sampleCount(); i++) {
            double x = dataset.xAt(i);
            double y = dataset.yAt(i);
            double t = dataset.outputAt(i);
            double o = anfis.calculateOutput(x, y);

            System.out.println(x + " " + y + " " + (o - t));
        }
    }

    public static void printRuleFuzzySets(ANFIS anfis) {
        for (Rule rule : anfis.getRules()) {
            System.out.println(rule.fuzzyA.a + " " + rule.fuzzyA.b
                    + " " + rule.fuzzyB.a + " " + rule.fuzzyB.b);
        }
    }
}
