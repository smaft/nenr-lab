package hr.fer.nenr.lab3.systems.ship;

import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.io.Output;
import hr.fer.nenr.lab3.systems.IOFuzzySystem;
import hr.fer.nenr.lab3.systems.acceleration.AccelerationFuzzySystem;
import hr.fer.nenr.lab3.systems.rudder.RudderFuzzySystem;

public class ShipControlFuzzySystem implements IOFuzzySystem {

    private AccelerationFuzzySystem accelerationFuzzySystem;

    private RudderFuzzySystem rudderFuzzySystem;

    public ShipControlFuzzySystem(Defuzzifier defuzzifier,
            InferenceEngine inferenceEngine) {
        this.accelerationFuzzySystem = new AccelerationFuzzySystem(
                defuzzifier, inferenceEngine);
        this.rudderFuzzySystem = new RudderFuzzySystem(
                defuzzifier, inferenceEngine);
    }

    @Override
    public Output conclude(Input input) {
        return new Output(
                accelerationFuzzySystem.conclude(input),
                rudderFuzzySystem.conclude(input)
        );
    }

}
