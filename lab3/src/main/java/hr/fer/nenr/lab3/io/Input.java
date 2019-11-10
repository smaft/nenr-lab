package hr.fer.nenr.lab3.io;

import hr.fer.nenr.lab3.io.helpers.InputGetters;
import hr.fer.nenr.lab3.io.helpers.InputType;

public class Input {

    private static final int COUNT = InputType.values().length;

    private final int leftDistance;
    private final int rightDistance;
    private final int leftAngledDistance;
    private final int rightAngledDistance;
    private final int speed;
    private final int correctDirection;

    public Input(int leftDistance, int rightDistance,
            int leftAngledDistance, int rightAngledDistance,
            int speed, int correctDirection) {
        this.leftDistance = leftDistance;
        this.rightDistance = rightDistance;
        this.leftAngledDistance = leftAngledDistance;
        this.rightAngledDistance = rightAngledDistance;
        this.speed = speed;
        this.correctDirection = correctDirection;
    }

    public int getLeftDistance() {
        return leftDistance;
    }

    public int getRightDistance() {
        return rightDistance;
    }

    public int getLeftAngledDistance() {
        return leftAngledDistance;
    }

    public int getRightAngledDistance() {
        return rightAngledDistance;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCorrectDirection() {
        return correctDirection;
    }

    public int valueOf(InputType type) {
        return InputGetters.value(this, type);
    }

    public static Input parse(String text) {
        String[] parts = text.split("\\s+");
        if (parts.length != COUNT) {
            throw new IllegalArgumentException(
                    "Expected " + COUNT + " values, but got " + parts.length
            );
        }

        return new Input(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[5])
        );
    }
}
