package hr.fer.nenr.lab1.demo;

import hr.fer.nenr.lab1.domain.Domain;
import hr.fer.nenr.lab1.domain.DomainElement;
import hr.fer.nenr.lab1.domain.IDomain;
import hr.fer.nenr.lab1.set.CalculatedFuzzySet;
import hr.fer.nenr.lab1.set.IFuzzySet;
import hr.fer.nenr.lab1.set.MutableFuzzySet;
import hr.fer.nenr.lab1.set.StandardFuzzySets;
import hr.fer.nenr.lab1.util.Debug;

public class FuzzySetDemo {

    public static void main(String[] args) {
        IDomain d1 = Domain.intRange(0, 11); // {0,1,...,10}
        IFuzzySet set1 = new MutableFuzzySet(d1)
                .set(DomainElement.of(0), 1.0)
                .set(DomainElement.of(1), 0.8)
                .set(DomainElement.of(2), 0.6)
                .set(DomainElement.of(3), 0.4)
                .set(DomainElement.of(4), 0.2);
        Debug.print(set1, "Set 1:");

        IDomain d2 = Domain.intRange(-5, 6); // {-5,-4,...,4,5}
        IFuzzySet set2 = new CalculatedFuzzySet(
                d2,
                StandardFuzzySets.lambdaFunction(
                        d2.indexOfElement(DomainElement.of(-4)),
                        d2.indexOfElement(DomainElement.of(0)),
                        d2.indexOfElement(DomainElement.of(4))
                ));
        Debug.print(set2, "Set 2:");
    }
}
