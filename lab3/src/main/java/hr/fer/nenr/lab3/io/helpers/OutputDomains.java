package hr.fer.nenr.lab3.io.helpers;

import hr.fer.nenr.lab3.domain.Domain;
import hr.fer.nenr.lab3.domain.IDomain;

import java.util.HashMap;
import java.util.Map;

public class OutputDomains {

    private static Map<OutputType, IDomain> domains = new HashMap<>();

    static {
        domains.put(OutputType.ACCELERATION, Domain.intRange(-50, 51));
        domains.put(OutputType.RUDDER, Domain.intRange(-90, 91));
    }

    private OutputDomains() {
    }

    public static IDomain domainOf(OutputType type) {
        return domains.get(type);
    }
}
