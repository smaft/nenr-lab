package hr.fer.nenr.lab3.systems.ship;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.ProductInferenceEngine;

public class ShipControlProductFuzzySystem extends ShipControlFuzzySystem {

    public ShipControlProductFuzzySystem(Defuzzifier defuzzifier) {
        super(defuzzifier, new ProductInferenceEngine());
    }
}
