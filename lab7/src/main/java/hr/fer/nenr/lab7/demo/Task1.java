package hr.fer.nenr.lab7.demo;

import hr.fer.nenr.lab7.util.Util;

/*
http://gnuplot.respawned.com/

set terminal svg size 400,300 enhanced fname 'arial' fsize 10 butt solid
set output 'out.svg'
set key inside top left
set xlabel 'x'
set ylabel 'y'
set title 'Single input neuron, w = 2'
plot "data.txt" using 1:2 title 's = 1.0' with lines,\
 "data.txt" using 1:3 title 's = 0.25' with lines,\
 "data.txt" using 1:4 title 's = 4.0' with lines
*/
public class Task1 {

    private static final double w = 2;
    private static final double[] sValues = new double[]{1.0, 0.25, 4.0};
    private static final double xMin = -8.0;
    private static final double xMax = 10.0;

    public static void main(String[] args) {
        for (double x = xMin; x <= xMax; x = Util.round(x + 0.1)) {
            System.out.print(x);
            for (double s : sValues) {
                System.out.print(" " + simpleNeuronOutput(x, w, s));
            }
            System.out.println();
        }
    }

    private static double simpleNeuronOutput(double x, double w, double s) {
        return 1.0 / (1.0 + Math.abs(x - w) / Math.abs(s));
    }
}
