package hr.fer.nenr.lab7.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
http://gnuplot.respawned.com/

set terminal svg size 400,300 enhanced fname 'arial' fsize 10 butt solid
set output 'out.svg'
set key outside
set xlabel 'x_1'
set ylabel 'x_2'
set title 'Skup podataka za učenje'
set xrange [0:1]
set yrange [0:1]
plot "data.txt" index 0 using 2:3 title 'Razred A',\
 "data.txt" index 1 using 2:3 title 'Razred B', \
 "data.txt" index 2 using 2:3 title 'Razred C'
*/
public class Task2 {

    private static final String DATASET_PATH = "dataset.txt";

    private static final int CLASS_COUNT = 3;
    private static final int INPUT_DIMENSIONS = 2;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Files.newInputStream(Paths.get(DATASET_PATH))))) {
            Map<Integer, List<String>> classes = new HashMap<>();
            for (int classIndex = 0; classIndex < CLASS_COUNT; classIndex++) {
                classes.put(classIndex, new ArrayList<>());
            }

            fillClasses(reader, classes);
            printClasses(classes);
        } catch (IOException e) {
            System.err.println(DATASET_PATH + " not found");
            System.exit(1);
        }
    }

    private static void fillClasses(BufferedReader reader,
            Map<Integer, List<String>> classes) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            int classIndex;
            for (classIndex = 0; classIndex < CLASS_COUNT; classIndex++) {
                if (parts[INPUT_DIMENSIONS + classIndex].equals("1")) break;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < INPUT_DIMENSIONS; i++) {
                sb.append(parts[i]).append(" ");
            }
            sb.setLength(sb.length() - 1);
            classes.get(classIndex).add(sb.toString());
        }
    }

    private static void printClasses(Map<Integer, List<String>> classes) {
        for (int i = 0; i < CLASS_COUNT; i++) {
            List<String> inputs = classes.get(i);
            for (String input : inputs) {
                System.out.println(i + " " + input);
            }
            System.out.println();
            System.out.println();
        }
    }
}
