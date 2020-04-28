package simulation;

import agent.Buyer;
import agent.Market;
import GUI.Chart;
import inputManager.Configuration;
import reporter.Reporter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Simulation implements FlyWeight, Step {
    private final Logger logger = LogManager.getRootLogger();
    public static int ID = 0;

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
        System.out.println("REINIT: markets:"+markets.size());
        ++Simulation.ID;
        buyers.iterator().forEachRemaining(Buyer::reinit);
        buyers.iterator().forEachRemaining(buyer -> buyer.setFriends(buyers));
        buyers.iterator().forEachRemaining(buyer -> buyer.setKnowMarkets(markets));
        buyers.iterator().forEachRemaining(Buyer::setInitialEndorsements);
        System.out.println("END-REINIT: markets:"+markets.size());
    }

    public void run() {
        logger.trace("Simulation: Starting");
        for (int period = 1; period <= periods; ++period) {
            doStep(period);
            logger.trace("Simulation: Period " + period);
        }

        buyers.iterator().forEachRemaining(buyer -> Reporter.addEndorsementData(buyer.getEndorsementData()));

        if (Configuration.GUI) {
            logger.trace("Simulation: Displaying & Saving chart");
            Chart.display(buyers, markets);
        }
    }

    @Override
    public void doStep(int period) {
        buyers.iterator().forEachRemaining(buyer -> buyer.doStep(period));
    }
}
