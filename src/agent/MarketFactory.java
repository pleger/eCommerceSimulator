package agent;

import inputManager.InnerMarket;
import inputManager.Markets;

import java.util.ArrayList;
import java.util.List;

public class MarketFactory {

    public static List<Market> createFromInput() {
        ArrayList<InnerMarket> innerMarkets =  Markets.getInnerMarkets();
        ArrayList<Market> markets = new ArrayList<>();
        innerMarkets.iterator().forEachRemaining(innerMarket -> markets.add(new Market(innerMarket)));
        return markets;
    }
}
