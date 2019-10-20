package hr.fer.nenr.lab2.set;

import hr.fer.nenr.lab2.domain.DomainElement;
import hr.fer.nenr.lab2.domain.IDomain;

public interface IFuzzySet {

    IDomain getDomain();

    double getValueAt(DomainElement element);
}
