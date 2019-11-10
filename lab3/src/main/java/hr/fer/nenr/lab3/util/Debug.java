package hr.fer.nenr.lab3.util;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.domain.IDomain;
import hr.fer.nenr.lab3.set.IFuzzySet;

public final class Debug {

    private Debug() {
    }

    public static void print(IDomain domain, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }

        for (DomainElement e : domain) {
            System.out.println("Domain element: " + e);
        }

        System.out.println("Domain cardinality is: "
                + domain.getCardinality() + "\n");
    }

    public static void print(IFuzzySet set, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }

        for (DomainElement e : set.getDomain()) {
            System.out.format("d(%s)=%f\n", e, set.getValueAt(e));
        }
        System.out.println();
    }
}
