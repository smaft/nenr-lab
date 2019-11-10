package hr.fer.nenr.lab3.rules;

import hr.fer.nenr.lab3.domain.DomainElement;
import hr.fer.nenr.lab3.inference.InferenceEngine;
import hr.fer.nenr.lab3.io.Input;
import hr.fer.nenr.lab3.io.helpers.InputType;
import hr.fer.nenr.lab3.operations.IUnaryFunction;
import hr.fer.nenr.lab3.operations.Operations;
import hr.fer.nenr.lab3.set.IFuzzySet;

import java.util.List;

public class Rule {

    private List<Expression> antecedent;

    private IFuzzySet consequent;

    private double[] muValues; // Allocated once

    public Rule(List<Expression> antecedent, IFuzzySet consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
        this.muValues = new double[antecedent.size()];
    }

    public IFuzzySet conclude(Input input, InferenceEngine inferenceEngine) {
        for (int i = 0; i < antecedent.size(); i++) {
            Expression expression = antecedent.get(i);
            IFuzzySet fuzzySet = expression.getFuzzySet();
            InputType inputType = expression.getType();

            int x = input.valueOf(inputType);
            double mu = fuzzySet.getValueAt(DomainElement.of(x));
            muValues[i] = mu;
        }

        double inferenceOutput = inferenceEngine.infer(muValues);
        IUnaryFunction cutoff = operand -> operand > inferenceOutput ?
                inferenceOutput : operand;
        return Operations.unaryOperation(consequent, cutoff);
    }
}
