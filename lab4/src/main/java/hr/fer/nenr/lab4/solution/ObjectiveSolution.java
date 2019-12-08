package hr.fer.nenr.lab4.solution;

public abstract class ObjectiveSolution implements Comparable<ObjectiveSolution> {

    public double error;

    public int compareTo(ObjectiveSolution o) {
        return Double.compare(this.error, o.error);
    }
}
