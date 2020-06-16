package agent;

import inputManager.InnerMarket;
import inputManager.Markets;
import logger.Console;
import sun.jvm.hotspot.oops.Mark;

import java.util.ArrayList;
import java.util.List;

public class MarketFactory {

    private static ArrayList<Market> markets;

    public static List<Market> createFromInput() {
        ArrayList<InnerMarket> innerMarkets =  Markets.getInnerMarkets();
        markets = new ArrayList<>();
        innerMarkets.iterator().forEachRemaining(innerMarket -> markets.add(new Market(innerMarket)));
        return markets;
    }


    public static Market getMarket(List<Market> markets, int id) {
        for (Market mk : markets) {
            if (mk.getID() == id) {
                return mk;
            }
        }

        return null;
    }

    public static Market getMarket(int id) {
        return getMarket(markets, id);
    }
}
