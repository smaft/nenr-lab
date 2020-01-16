package hr.fer.nenr.lab7.rng;

import java.util.Random;

public class RandomSingleton {

    private static RandomSingleton instance = new RandomSingleton();

    public static RandomSingleton getInstance() {
        return instance;
    }

    private Random random = new Random();

    private RandomSingleton() {
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextInRange(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public double nextGaussian(double sigma) {
        return sigma * random.nextGaussian();
    }
}
