package Endorsement;

import InputManager.Configuration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.function.BiFunction;

public class EndorsementEvaluation {

    public static Double MAX(Double[] attributes, Double mean) {
        int index = -1;
        double max = -1;
        for (int i = 0; i < Configuration.ATTRIBUTES_M; ++i) {
           if (max < attributes[i]) {
               max = attributes[i];
               index = i;
           }
        }

        return  calculateEndorsementFormula(index, mean, Configuration.LEVELS);
    }

    public static Double BY_PROBABILITY(Double @NotNull [] attributes, Double mean) {
        double random = Math.random();
        double acc = 0;
        int index = -1;

        for (int i = 0; i < Configuration.ATTRIBUTES_M; ++i) {
            acc += attributes[i];
            if (acc >= random) {
                index = i;
                break;
            }
        }

        return calculateEndorsementFormula(index, mean, Configuration.LEVELS);
    }

    /**
     *
     * @param index index of levels
     * @param mean  mean of buyers
     * @param levels maximum of levels
     * @return Formula of Oswaldo
     */
    private static Double calculateEndorsementFormula(@Range(from = 0, to = Integer.MAX_VALUE) int index, Double mean, int levels) {
        int k =  (int) Math.floor(index - levels / 2.0);
        double result = 0;

        if (k > 0) {
            result = mean*k*(2.0/levels);
        }
        if (k < 0) {
            result = mean*k*(1/(levels - 1.0));
        }
        
        return result;
    }

    public static double[] evaluate(AttributesMarket amarkets, AttributesBuyer abuyer, BiFunction<Double[], Double, Double> strategy) {
        int attributesNumber = amarkets.size();
        double[] results = new double[attributesNumber];

        assert Configuration.ATTRIBUTES_M ==  attributesNumber : "wrong number of attributes of market";

        for (int i = 0; i < attributesNumber; ++i) {
            results[i] = strategy.apply(amarkets.getValues(i), abuyer.getValue(i));
        }
       return results;
    }


}
