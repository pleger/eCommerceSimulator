package agent;

import endorsement.EndorsementFactory;
import endorsement.Endorsements;
import inputManager.Configuration;
import logger.Console;
import reporter.MarketEvaluationData;
import simulation.Simulation;

import java.util.List;

public class Interaction {

    public static Endorsements interact(int period, Buyer buyer, List<Market> markets) {
        int selectedMarket = selectMarket(period, buyer, markets);
        Console.setAssert(selectedMarket != -1, "Interaction: No Market selected. Selected:" + selectedMarket + " marketSize:" + markets.size() + " buyerSize:" + buyer.getID());

        return EndorsementFactory.createByStep(period, buyer, markets.get(selectedMarket));
    }

    private static int selectMarket(int period, Buyer buyer, List<Market> markets) {
        double[] evaluations = new double[markets.size()];
        for (Market market : markets) {
            Endorsements endors = buyer.getEndorsements().filterByMarket(market).filterByMemory(period);
            evaluations[market.getID()] = evaluateMarket(endors.toArray());

            reporter.Reporter.addMarketEvaluationData(new MarketEvaluationData(Simulation.ID, period, buyer.getID(), market.getName(), evaluations[market.getID()]));
        }

        /*double max = Double.MAX_VALUE * -1;
        int selected = -1;
        for (int i = 0; i < evaluations.length; ++i) {
            if (max < evaluations[i]) {
                max = evaluations[i];
                selected = i;
            }
        }*/

        int selected = MarketSelectionStrategies.BY_PROBABILITY(evaluations);

        buyer.setCurrentEvaluation(evaluations[selected]);
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
