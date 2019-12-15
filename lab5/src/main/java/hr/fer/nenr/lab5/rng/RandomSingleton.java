package hr.fer.nenr.lab5.rng;

import java.util.Random;

public class RandomSingleton {

    private static RandomSingleton instance = new RandomSingleton();

    public static RandomSingleton getInstance() {
        return instance;
    }

    private Random random = new Random();

    public Random getRandom() {
        return random;
    }
}
