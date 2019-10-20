package hr.fer.nenr.lab2.set;

import hr.fer.nenr.lab2.domain.DomainElement;
import hr.fer.nenr.lab2.domain.IDomain;
import hr.fer.nenr.lab2.util.IIntUnaryFunction;

public class CalculatedFuzzySet implements IFuzzySet {

    private IDomain domain;

    private IIntUnaryFunction function;

    public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
        this.domain = domain;
        this.function = function;
    }

    @Override
    public IDomain getDomain() {
        return domain;
    }

    @Override
    public double getValueAt(DomainElement element) {
        return function.valueAt(domain.indexOfElement(element));
    }
}
