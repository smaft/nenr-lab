package hr.fer.nenr.lab3;

import hr.fer.nenr.lab3.defuzzifiers.COADefuzzifier;
import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.io.Output;
import hr.fer.nenr.lab3.systems.IOFuzzySystem;
import hr.fer.nenr.lab3.systems.ship.ShipControlMinFuzzySystem;
import hr.fer.nenr.lab3.systems.ship.ShipControlProductFuzzySystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private static final String END = "KRAJ";

    public static void main(String[] args) {
        Defuzzifier defuzzifier = new COADefuzzifier();
//        IOFuzzySystem fuzzySystem = new ShipControlMinFuzzySystem(defuzzifier);
        IOFuzzySystem fuzzySystem = new ShipControlProductFuzzySystem(defuzzifier);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String inputLine = reader.readLine();
                if (inputLine == null || inputLine.equals(END)) break;

                Input input = Input.parse(inputLine);
                Output output = fuzzySystem.conclude(input);

                System.out.println(output.toString());
                System.out.flush();
            }
        } catch (IOException ex) {
            System.out.println("Standard input error occurred. Exiting...");
        }
    }
}
