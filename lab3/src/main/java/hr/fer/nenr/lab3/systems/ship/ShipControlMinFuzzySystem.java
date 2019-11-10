package hr.fer.nenr.lab3.systems.ship;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.MinInferenceEngine;

public class ShipControlMinFuzzySystem extends ShipControlFuzzySystem {

    public ShipControlMinFuzzySystem(Defuzzifier defuzzifier) {
        super(defuzzifier, new MinInferenceEngine());
    }
}
