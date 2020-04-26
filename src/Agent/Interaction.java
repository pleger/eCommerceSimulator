package Agent;

import Endorsement.EndorsementFactory;
import Endorsement.Endorsements;
import InputManager.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Interaction {

    private static final Logger logger = LogManager.getRootLogger();

    public static Endorsements interact(int period, Buyer buyer, List<Market> markets) {
        int selectedMarket = selectMarket(period, buyer, markets);
        logger.assertLog(selectedMarket != -1, "No Market selected. Selected:" + selectedMarket + " marketSize:" + markets.size() + " buyerSize:" + buyer.getID());

        return EndorsementFactory.createByStep(period, buyer, markets.get(selectedMarket));
    }

    private static int selectMarket(int period, Buyer buyer, List<Market> markets) {
        double[] evaluations = new double[markets.size()];
        for (int i = 0; i < markets.size(); ++i) {
            Endorsements endors = buyer.getEndorsements().filterByMarket(markets.get(i)).filterByMemory(period);
            System.out.println("Buyer:" + buyer.getID() + " endors:" + endors.size());
            evaluations[i] = evaluateMarket(endors.toArray());
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
            if (value > 0) System.out.println("COOL:" + value);
            result += value > 0 ? Math.pow(Configuration.BASE, value) : -1 * Math.pow(Configuration.BASE, Math.abs(value));
        }
        return result;
    }
}
