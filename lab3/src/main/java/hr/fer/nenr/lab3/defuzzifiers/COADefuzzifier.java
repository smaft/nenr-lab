package hr.fer.nenr.lab3.defuzzifiers;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.set.IFuzzySet;

public class COADefuzzifier implements Defuzzifier {

    @Override
    public int decode(IFuzzySet fuzzySet) {
        double numerator = 0.0;
        double denominator = 0.0;

        for (DomainElement element : fuzzySet.getDomain()) {
            double mu = fuzzySet.getValueAt(element);
            numerator += mu * element.getComponentValue(0);
            denominator += mu;
        }

        return (int) (numerator / denominator);
    }
}
