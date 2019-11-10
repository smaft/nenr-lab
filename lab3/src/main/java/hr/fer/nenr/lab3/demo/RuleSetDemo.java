package hr.fer.nenr.lab3.demo;

import hr.fer.nenr.lab3.defuzzifiers.COADefuzzifier;
import hr.fer.nenr.lab3.defuzzifiers.Defuzzifier;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.inference.MinInferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.rules.set.AccelerationRuleSet;
import hr.fer.nenr.lab3.rules.set.RudderRuleSet;
import hr.fer.nenr.lab3.rules.set.RuleSet;
import hr.fer.nenr.lab3.set.IFuzzySet;
import hr.fer.nenr.lab3.util.Debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuleSetDemo {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            RuleSet ruleSet = pickRuleSet(reader);

            InferenceEngine inferenceEngine = new MinInferenceEngine();
            Defuzzifier defuzzifier = new COADefuzzifier();

            concludeForInput(reader, ruleSet, inferenceEngine, defuzzifier);
        } catch (IOException e) {
            System.out.println("Standard input error occurred. Exiting...");
        }
    }

    private static RuleSet pickRuleSet(BufferedReader reader) throws IOException {
        while (true) {
            System.out.print("Rule set: ");
            String line = reader.readLine();

            char c = Character.toLowerCase(line.charAt(0));
            if (c == 'a') {
                return new AccelerationRuleSet();
            } else if (c == 'r') {
                return new RudderRuleSet();
            }

            System.out.println("Invalid rule set: " + c);
        }
    }

    private static void concludeForInput(BufferedReader reader, RuleSet ruleSet,
            InferenceEngine inferenceEngine, Defuzzifier defuzzifier) throws IOException {
        while (true) {
            System.out.print("Input values: ");
            String line = reader.readLine();

            try {
                Input input = Input.parse(line);
                IFuzzySet fuzzySet = ruleSet.conclude(input, inferenceEngine);

                Debug.print(fuzzySet, "Concluded fuzzy set:");
                System.out.println("Decoded:" + defuzzifier.decode(fuzzySet));
                return;
            } catch (RuntimeException ignored) {
            }

            System.out.println("Invalid input: " + line);
        }
    }
}
