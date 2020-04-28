package Agent;

import Endorsement.EndorsementFactory;
import Endorsement.Endorsements;
import InputManager.Configuration;
import Reporter.MarketEvaluationData;
import Simulation.Simulation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Interaction {

    private static final Logger logger = LogManager.getRootLogger();

    public static Endorsements interact(int period, Buyer buyer, List<Market> markets) {
        int selectedMarket = selectMarket(period, buyer, markets);
        logger.assertLog(selectedMarket != -1, "Interaction: No Market selected. Selected:" + selectedMarket + " marketSize:" + markets.size() + " buyerSize:" + buyer.getID());

        return EndorsementFactory.createByStep(period, buyer, markets.get(selectedMarket));
    }

    private static int selectMarket(int period, Buyer buyer, List<Market> markets) {
        double[] evaluations = new double[markets.size()];
        for (Market market : markets) {
            Endorsements endors = buyer.getEndorsements().filterByMarket(market).filterByMemory(period);
            evaluations[market.getID()] = evaluateMarket(endors.toArray());

            Reporter.Reporter.addMarketEvaluationData(new MarketEvaluationData(Simulation.ID, period, buyer.getID(), market.getName(), evaluations[market.getID()]));
        }

        double max = Double.MAX_VALUE * -1;
        int selected = -1;
        for (int i = 0; i < evaluations.length; ++i) {
            if (max < evaluations[i]) {
                max = evaluations[i];
                selected = i;
            }
        }

        buyer.setCurrentEvaluation(max);
        return selected;
    }

    private static double evaluateMarket(double[] values) {
        double result = 0;

        for (double value : values) {
            result += value > 0 ? Math.pow(Configuration.BASE, value) : -1 * Math.pow(Configuration.BASE, Math.abs(value));
        }
        return result;
    }
}
