package hr.fer.nenr.lab1.set;

import hr.fer.nenr.lab1.domain.DomainElement;
import hr.fer.nenr.lab1.domain.IDomain;

public interface IFuzzySet {

    IDomain getDomain();

    double getValueAt(DomainElement element);
}
