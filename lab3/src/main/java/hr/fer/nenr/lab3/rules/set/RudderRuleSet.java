package hr.fer.nenr.lab3.rules.set;

import hr.fer.nenr.lab3.rules.RuleBuilder;

import java.util.Arrays;

import static hr.fer.nenr.lab3.io.helpers.InputType.*;
import static hr.fer.nenr.lab3.rules.util.Functions.*;

public class RudderRuleSet extends AbstractRuleSet {

    public RudderRuleSet() {
        super(Arrays.asList(
                new RuleBuilder()
                        .when(LEFT, CLOSE)
                        .and(CORRECT_DIRECTION, TRUE)
                        .then(STEER_RIGHT)
                        .build(),
                new RuleBuilder()
                        .when(RIGHT, CLOSE)
                        .and(CORRECT_DIRECTION, TRUE)
                        .then(STEER_LEFT)
                        .build(),
                new RuleBuilder()
                        .when(LEFT_ANGLED, CLOSE_ANGLED)
                        .and(CORRECT_DIRECTION, TRUE)
                        .then(STEER_RIGHT)
                        .build(),
                new RuleBuilder()
                        .when(RIGHT_ANGLED, CLOSE_ANGLED)
                        .and(CORRECT_DIRECTION, TRUE)
                        .then(STEER_LEFT)
                        .build(),
                new RuleBuilder()
                        .when(CORRECT_DIRECTION, FALSE)
                        .then(STEER_LEFT)
                        .build()
        ));
    }
}
