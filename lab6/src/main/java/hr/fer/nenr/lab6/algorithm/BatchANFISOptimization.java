package hr.fer.nenr.lab6.algorithm;

import hr.fer.nenr.lab6.dataset.Dataset;
import hr.fer.nenr.lab6.neurofuzzy.ANFIS;

public class BatchANFISOptimization extends ANFISOptimization {

    public BatchANFISOptimization(ANFIS anfis, Dataset dataset,
            int maxIterations, double etaAntecedent, double etaConsequent) {
        super(anfis, dataset, maxIterations, etaAntecedent, etaConsequent);
    }

    @Override
    protected void iteration() {
        for (int i = 0; i < dataset.sampleCount(); i++) {
            updateRuleDeltas(dataset.xAt(i), dataset.yAt(i), dataset.outputAt(i));
        }
        updateRules();
    }
}
