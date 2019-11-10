package hr.fer.nenr.lab3.io;

public class Output {

    private final int acceleration;
    private final int rudder;

    public Output(int acceleration, int rudder) {
        this.acceleration = acceleration;
        this.rudder = rudder;
    }

    @Override
    public String toString() {
        return acceleration + " " + rudder;
    }
}
