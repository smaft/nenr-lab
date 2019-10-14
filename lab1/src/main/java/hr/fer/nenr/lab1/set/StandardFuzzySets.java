package hr.fer.nenr.lab1.set;

import hr.fer.nenr.lab1.util.IIntUnaryFunction;

public final class StandardFuzzySets {

    private StandardFuzzySets() {
    }

    public static IIntUnaryFunction lFunction(int alpha, int beta) {
        return operand -> {
            if (operand < alpha) return 1.0;
            if (operand >= beta) return 0.0;

            return (double) (beta - operand) / (beta - alpha);
        };
    }

    public static IIntUnaryFunction gammaFunction(int alpha, int beta) {
        return operand -> {
            if (operand < alpha) return 0.0;
            if (operand >= beta) return 1.0;

            return (double) (operand - alpha) / (beta - alpha);
        };
    }

    public static IIntUnaryFunction lambdaFunction(int alpha, int beta, int gamma) {
        return operand -> {
            if (operand < alpha) return 0.0;
            if (operand < beta) return (double) (operand - alpha) / (beta - alpha);
            if (operand < gamma) return (double) (gamma - operand) / (gamma - beta);

            return 0.0;
        };
    }
}
