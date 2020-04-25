package Agent;

import Endorsement.EndorsementFactory;
import Endorsement.Endorsements;
import InputManager.Configuration;

import java.util.List;

public class Interaction {

    public static Endorsements interact(int period, Buyer buyer, List<Market> markets) {
        int selectedMarket = selectMarket(period, buyer, markets);

        System.out.println(period+" "+buyer.getID()+ " "+ selectedMarket);

        return EndorsementFactory.createByStep(period, buyer, markets.get(selectedMarket));
    }

    private static int selectMarket(int period, Buyer buyer, List<Market> markets) {
        double[] evaluations = new double[markets.size()];
        for (int i = 0; i < markets.size(); ++i) {
            Endorsements endors = buyer.getEndorsements().filterByMarket(markets.get(i)).filterByMemory(period);
            evaluations[i] = evaluateMarket(endors.toArray());
        }

        System.out.println("evaluations:"+evaluations.length+ " " + markets.size());

        double max = Double.MAX_VALUE*-1;
        int selected = -1;
        for (int i = 0; i < evaluations.length; ++i) {
            System.out.println(evaluations[i]+ " "+max);
            if (max < evaluations[i]) {
                max = evaluations[i];
                selected = i;
            }
        }
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
