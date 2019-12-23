package hr.fer.nenr.lab6.math;

import java.util.function.DoubleBinaryOperator;

import static java.lang.Math.cos;
import static java.lang.Math.pow;

public final class Functions {

    public static final DoubleBinaryOperator DEFAULT_FUNCTION = (x, y) ->
            (pow(x - 1, 2) + pow(y + 2, 2) - 5 * x * y + 3) * pow(cos(x / 5), 2);

    private Functions() {
    }
}
