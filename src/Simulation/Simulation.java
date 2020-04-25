package Simulation;

import Agent.Buyer;
import Agent.Market;
import GUI.Chart;
import InputManager.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Simulation implements FlyWeight, Step {
    private final Logger logger = LogManager.getRootLogger();

    private final int periods;
    private final List<Buyer> buyers;
    private final List<Market> markets;

    public Simulation(List<Buyer> buyers, List<Market> markets, int periods) {
        this.periods = periods;
        this.buyers = buyers;
        this.markets = markets;

        reinit();
        logger.trace("Simulation: created with buyers and markets:" + buyers.size() + " " + markets.size());
    }

    @Override
    public void reinit() {
        buyers.iterator().forEachRemaining(Buyer::reinit);
        buyers.iterator().forEachRemaining(buyer -> buyer.setFriends(buyers));
        buyers.iterator().forEachRemaining(buyer -> buyer.setKnowMarkets(markets));
        buyers.iterator().forEachRemaining(Buyer::setInitialEndorsements);
    }

    public void run() {
        logger.trace("Simulation: Starting");
        for (int period = 0; period < periods; ++period) {
            doStep(period);
            logger.trace("Simulation: Period " + period);
        }

        if (Configuration.GUI) {
            logger.trace("Simulation: Displaying chart");
            Chart.display(buyers, markets);
        }
    }

    @Override
    public void doStep(int period) {
        buyers.iterator().forEachRemaining(buyer -> buyer.doStep(period));
    }
}
