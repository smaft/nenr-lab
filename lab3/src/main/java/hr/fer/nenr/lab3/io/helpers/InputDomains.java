package hr.fer.nenr.lab3.io.helpers;

import hr.fer.nenr.lab3.domain.Domain;
import hr.fer.nenr.lab3.domain.IDomain;

import java.util.HashMap;
import java.util.Map;

public final class InputDomains {

    private static final int LOWER = 0;
    private static final int UPPER = 1301;

    private static Map<InputType, IDomain> domains = new HashMap<>();

    static {
        domains.put(InputType.LEFT, Domain.intRange(LOWER, UPPER));
        domains.put(InputType.RIGHT, Domain.intRange(LOWER, UPPER));
        domains.put(InputType.LEFT_ANGLED, Domain.intRange(LOWER, UPPER));
        domains.put(InputType.RIGHT_ANGLED, Domain.intRange(LOWER, UPPER));
        domains.put(InputType.SPEED, Domain.intRange(LOWER, UPPER));
        domains.put(InputType.CORRECT_DIRECTION, Domain.intRange(0, 2));
    }

    private InputDomains() {
    }

    public static IDomain domainOf(InputType type) {
        return domains.get(type);
    }
}
