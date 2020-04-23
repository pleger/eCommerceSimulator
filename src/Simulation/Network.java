package Simulation;

import Agent.Buyer;
import Agent.Market;

import java.util.List;

public class Network implements FlyWeight {
    List<Buyer> buyers;
    List<Market> markets;

    Network(List<Buyer> buyers, List<Market> markets) {
        this.buyers = buyers;
        this.markets = markets;
        reinit();
    }

    @Override
    public void reinit() {
        buyers.iterator().forEachRemaining(Buyer::reinit);
        buyers.iterator().forEachRemaining(buyer -> buyer.setFriends(buyers));
        buyers.iterator().forEachRemaining(buyer -> buyer.setInitialEndorsements(markets));
    }
}
