package hr.fer.nenr.lab3.operations;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.domain.IDomain;
import hr.fer.nenr.lab3.set.IFuzzySet;

public final class Operations {

    private static final IUnaryFunction ZADEH_NOT = operand -> 1.0 - operand;

    private static final IBinaryFunction ZADEH_AND = Math::min;

    private static final IBinaryFunction ZADEH_OR = Math::max;

    private Operations() {
    }

    public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction function) {
        return new IFuzzySet() {
            @Override
            public IDomain getDomain() {
                return set.getDomain();
            }

            @Override
            public double getValueAt(DomainElement element) {
                return function.valueAt(set.getValueAt(element));
            }
        };
    }

    public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2,
            IBinaryFunction function) {
        return new IFuzzySet() {
            @Override
            public IDomain getDomain() {
                return set1.getDomain();
            }

            @Override
            public double getValueAt(DomainElement element) {
                return function.valueAt(set1.getValueAt(element), set2.getValueAt(element));
            }
        };
    }

    public static IUnaryFunction zadehNot() {
        return ZADEH_NOT;
    }

    public static IBinaryFunction zadehAnd() {
        return ZADEH_AND;
    }

    public static IBinaryFunction zadehOr() {
        return ZADEH_OR;
    }

    public static IBinaryFunction hamacherTNorm(double nu) {
        if (nu == 0.0) {
            return (a, b) -> {
                if (a == 0.0 && b == 0) return 0.0;
                return calculateHamacherT(a, b, nu);
            };
        }
        return (a, b) -> calculateHamacherT(a, b, nu);
    }

    public static IBinaryFunction hamacherSNorm(double nu) {
        return (a, b) -> calculateHamacherS(a, b, nu);
    }

    private static double calculateHamacherT(double a, double b, double nu) {
        return a * b / (nu + (1 - nu) * (a + b - a * b));
    }

    private static double calculateHamacherS(double a, double b, double nu) {
        return (a + b - (2.0 - nu) * a * b) / (1.0 - (1.0 - nu) * a * b);
    }
}
