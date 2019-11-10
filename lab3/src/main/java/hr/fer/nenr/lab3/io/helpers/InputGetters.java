package hr.fer.nenr.lab3.io.helpers;

import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.util.IntGetter;

import java.util.HashMap;
import java.util.Map;

public final class InputGetters {

    private static Map<InputType, IntGetter<Input>> getters = new HashMap<>();

    static {
        getters.put(InputType.LEFT, Input::getLeftDistance);
        getters.put(InputType.RIGHT, Input::getRightDistance);
        getters.put(InputType.LEFT_ANGLED, Input::getLeftAngledDistance);
        getters.put(InputType.RIGHT_ANGLED, Input::getRightAngledDistance);
        getters.put(InputType.SPEED, Input::getSpeed);
        getters.put(InputType.CORRECT_DIRECTION, Input::getCorrectDirection);
    }

    private InputGetters() {
    }

    public static int value(Input input, InputType type) {
        return getters.get(type).getInt(input);
    }
}
