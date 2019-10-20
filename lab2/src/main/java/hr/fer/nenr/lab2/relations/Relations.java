package hr.fer.nenr.lab2.relations;

import hr.fer.nenr.lab2.domain.Domain;
import hr.fer.nenr.lab2.domain.DomainElement;
import hr.fer.nenr.lab2.domain.IDomain;
import hr.fer.nenr.lab2.operations.Operations;
import hr.fer.nenr.lab2.set.IFuzzySet;
import hr.fer.nenr.lab2.set.MutableFuzzySet;

public final class Relations {

    private Relations() {
    }

    public static boolean isUTimesURelation(IFuzzySet relation) {
        IDomain domain = relation.getDomain();
        if (domain.getNumberOfComponents() != 2) return false;

        return domain.getComponent(0).equals(domain.getComponent(1));
    }

    public static boolean isSymmetric(IFuzzySet relation) {
        if (!isUTimesURelation(relation)) return false;

        for (DomainElement xy : relation.getDomain()) {
            int x = xy.getComponentValue(0);
            int y = xy.getComponentValue(1);
            if (x >= y) continue;

            DomainElement yx = DomainElement.of(y, x);
            if (!epsilonEquals(relation.getValueAt(xy), relation.getValueAt(yx))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isReflexive(IFuzzySet relation) {
        if (!isUTimesURelation(relation)) return false;

        for (DomainElement element : relation.getDomain().getComponent(0)) {
            int x = element.getComponentValue(0);
            DomainElement xx = DomainElement.of(x, x);

            if (!epsilonEquals(relation.getValueAt(xx), 1.0)) return false;
        }

        return true;
    }

    public static boolean isMaxMinTransitive(IFuzzySet relation) {
        if (!isUTimesURelation(relation)) return false;

        IDomain domain = relation.getDomain();
        for (DomainElement xz : domain) {
            int x = xz.getComponentValue(0);
            int z = xz.getComponentValue(1);
            double xzValue = relation.getValueAt(xz);

            for (DomainElement element : domain.getComponent(0)) {
                int y = element.getComponentValue(0);
                DomainElement xy = DomainElement.of(x, y);
                DomainElement yz = DomainElement.of(y, z);

                double xyValue = relation.getValueAt(xy);
                double yzValue = relation.getValueAt(yz);

                double tNorm = Operations.zadehAnd().valueAt(xyValue, yzValue);
                if (xzValue < tNorm) return false;
            }
        }

        return true;
    }

    public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) {
        IDomain r1d = r1.getDomain();
        IDomain r2d = r2.getDomain();
        IDomain domain = Domain.combine(r1d.getComponent(0), r2d.getComponent(1));

        MutableFuzzySet composition = new MutableFuzzySet(domain);
        for (DomainElement xz : domain) {
            int x = xz.getComponentValue(0);
            int z = xz.getComponentValue(1);

            double max = Double.NEGATIVE_INFINITY;
            for (DomainElement element : r1d.getComponent(1)) {
                int y = element.getComponentValue(0);
                DomainElement xy = DomainElement.of(x, y);
                DomainElement yz = DomainElement.of(y, z);
                double min = Operations.zadehAnd()
                        .valueAt(r1.getValueAt(xy), r2.getValueAt(yz));
                if (min > max) max = min;
            }

            composition.set(xz, max);
        }

        return composition;
    }

    public static boolean isFuzzyEquivalence(IFuzzySet relation) {
        return isReflexive(relation)
                && isSymmetric(relation)
                && isMaxMinTransitive(relation);
    }

    private static boolean epsilonEquals(double a, double b) {
        return Math.abs(b - a) < 1e-6;
    }
}
