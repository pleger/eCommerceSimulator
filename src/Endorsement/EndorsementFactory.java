package Endorsement;

import Agent.Buyer;
import Agent.Market;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.function.BiFunction;

public class EndorsementFactory {
    private static final Logger logger = LogManager.getRootLogger();


    public static Endorsements createInitial(int period, Buyer buyer, Market market) {
        return create(period,buyer,market,EndorsementEvaluation::BY_MAX);
    }
    public static Endorsements createByStep(int period, Buyer buyer, Market market) {
        return create(period,buyer,market,EndorsementEvaluation::BY_PROBABILITY);
    }

    private static Endorsements create(int period, Buyer buyer, Market market, BiFunction<Double[], Double, Double> strategy) {
        Endorsements endors = new Endorsements();

        AttributesMarket aMarket = market.getAttributes();
        AttributesBuyer aBuyer = buyer.getAttribute();

        double[] results = EndorsementEvaluation.evaluate(aMarket, aBuyer, strategy);

        for (int i = 0; i < results.length; ++i) {
            endors.add(new Endorsement(period, market, aMarket.getName(i), results[i]));
        }

        return endors;
    }
}
