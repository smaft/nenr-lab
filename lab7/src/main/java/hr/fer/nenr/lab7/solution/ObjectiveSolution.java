package hr.fer.nenr.lab7.solution;

public abstract class ObjectiveSolution implements Comparable<ObjectiveSolution> {

    public double error;

    @Override
    public int compareTo(ObjectiveSolution o) {
        return Double.compare(this.error, o.error);
    }
}
