package Endorsement;

import Agent.Buyer;
import Agent.Market;

import java.util.function.BiFunction;

public class EndorsementFactory {

    public static Endorsements createInitial(int period, Buyer buyer, Market market) {
        return create(period,buyer,market,EndorsementEvaluation::MAX);
    }
    public static Endorsements createByStep(int period, Buyer buyer, Market market) {
        return create(period,buyer,market,EndorsementEvaluation::BY_PROBABILITY);
    }

    private static Endorsements create(int period, Buyer buyer, Market market, BiFunction<Double[], Double, Double> strategy) {
        Endorsements endors = new Endorsements();

        AttributesMarket aMarket = market.getAttributes();
        AttributesBuyer aBuyer = buyer.getAttributes();

        double[] results = EndorsementEvaluation.evaluate(aMarket, aBuyer, strategy);

        for (int i = 0; i < results.length; ++i) {
            endors.add(new Endorsement(period, market, aMarket.getName(i), results[i]));
        }

        return endors;
    }
}
