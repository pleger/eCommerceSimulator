package agent;

import inputManager.Configuration;
import logger.Console;

public class MarketSelectionStrategies {

    public static int BY_MAX(double[] evaluations) {
        int selected = -1;
        double max = Double.MAX_VALUE * -1;
        for (int i = 0; i < evaluations.length; ++i) {
            if (max < evaluations[i]) {
                max = evaluations[i];
                selected = i;
            }
        }

        Console.setAssert(selected != -1, "MarketSelectionStrategies.BY_MAX: no market selected");
        return selected;
    }

    public static int BY_PROBABILITY(double[] evaluations) {
        int selected = -1;
        double random = Math.random();
        double sum  = 0;
        double acc = 0;

        for (double eval: evaluations) {
            sum += eval;
        }

        for (int i = 0; i < evaluations.length; ++i) {
            acc += evaluations[i]/sum;
            if (acc >= random) {
                selected = i;
                break;
            }
        }

        Console.setAssert(selected != -1, "MarketSelectionStrategies.BY_PROBABILITY: no market selected");
        return selected;
    }
}
