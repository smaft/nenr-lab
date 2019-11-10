package hr.fer.nenr.lab3.systems;

public interface FuzzySystem<T, R> {

    R conclude(T input);
}
