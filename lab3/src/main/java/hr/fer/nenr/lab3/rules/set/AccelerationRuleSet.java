package hr.fer.nenr.lab3.rules.set;

import hr.fer.nenr.lab3.rules.RuleBuilder;

import java.util.Arrays;

import static hr.fer.nenr.lab3.io.helpers.InputType.*;
import static hr.fer.nenr.lab3.rules.util.Functions.*;

public class AccelerationRuleSet extends AbstractRuleSet {

    public AccelerationRuleSet() {
        super(Arrays.asList(
                new RuleBuilder()
                        .when(SPEED, SLOW)
                        .then(SPEED_UP)
                        .build(),
                new RuleBuilder()
                        .when(SPEED, FAST)
                        .then(SLOW_DOWN)
                        .build(),
                new RuleBuilder()
                        .when(LEFT_ANGLED, FAR_ANGLED)
                        .and(RIGHT_ANGLED, FAR_ANGLED)
                        .then(SPEED_UP)
                        .build()
        ));
    }
}
