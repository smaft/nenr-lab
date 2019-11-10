package hr.fer.nenr.lab3.set;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.domain.IDomain;

public interface IFuzzySet {

    IDomain getDomain();

    double getValueAt(DomainElement element);
}
