package hr.fer.nenr.lab3.rules.util;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.domain.IDomain;
import hr.fer.nenr.lab3.set.CalculatedFuzzySet;
import hr.fer.nenr.lab3.set.IFuzzySet;

import static hr.fer.nenr.lab3.io.helpers.InputDomains.domainOf;
import static hr.fer.nenr.lab3.io.helpers.InputType.*;
import static hr.fer.nenr.lab3.io.helpers.OutputDomains.domainOf;
import static hr.fer.nenr.lab3.io.helpers.OutputType.ACCELERATION;
import static hr.fer.nenr.lab3.io.helpers.OutputType.RUDDER;
import static hr.fer.nenr.lab3.set.StandardFuzzySets.gammaFunction;
import static hr.fer.nenr.lab3.set.StandardFuzzySets.lFunction;

public final class Functions {

    public static final IFuzzySet CLOSE = l(domainOf(LEFT), 20, 50);

    public static final IFuzzySet CLOSE_ANGLED = l(domainOf(LEFT_ANGLED), 0, 80);
    public static final IFuzzySet FAR_ANGLED = gamma(domainOf(LEFT_ANGLED), 0, 40);

    public static final IFuzzySet FALSE = l(domainOf(CORRECT_DIRECTION), 0, 1);
    public static final IFuzzySet TRUE = gamma(domainOf(CORRECT_DIRECTION), 0, 1);

    public static final IFuzzySet SLOW = l(domainOf(SPEED), 0, 60);
    public static final IFuzzySet FAST = gamma(domainOf(SPEED), 60, 80);

    public static final IFuzzySet STEER_LEFT = gamma(domainOf(RUDDER), 80, 90);
    public static final IFuzzySet STEER_RIGHT = l(domainOf(RUDDER), -90, -80);

    public static final IFuzzySet SPEED_UP = gamma(domainOf(ACCELERATION), 0, 10);
    public static final IFuzzySet SLOW_DOWN = l(domainOf(ACCELERATION), -5, 0);

    private Functions() {
    }

    private static IFuzzySet gamma(IDomain domain, int alpha, int beta) {
        return new CalculatedFuzzySet(domain, gammaFunction(
                domain.indexOfElement(DomainElement.of(alpha)),
                domain.indexOfElement(DomainElement.of(beta))));
    }

    private static IFuzzySet l(IDomain domain, int alpha, int beta) {
        return new CalculatedFuzzySet(domain, lFunction(
                domain.indexOfElement(DomainElement.of(alpha)),
                domain.indexOfElement(DomainElement.of(beta))));
    }
}
