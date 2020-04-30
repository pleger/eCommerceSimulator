package simulation;

import agent.Buyer;
import agent.Market;
import gui.Chart;
import inputManager.Configuration;
import logger.Console;
import reporter.Reporter;

import java.util.List;

public class Simulation implements FlyWeight, Step {
    public static int ID = 0;

    private final int periods;
    private final List<Buyer> buyers;
    private final List<Market> markets;

    public Simulation(List<Buyer> buyers, List<Market> markets, int periods) {
        this.periods = periods;
        this.buyers = buyers;
        this.markets = markets;

        reinit();
        Console.trace("Simulation: created with buyers and markets:" + buyers.size() + " " + markets.size());
    }

    @Override
    public void reinit() {
        ++Simulation.ID;
        buyers.iterator().forEachRemaining(Buyer::reinit);
        buyers.iterator().forEachRemaining(buyer -> buyer.setFriends(buyers));
        buyers.iterator().forEachRemaining(buyer -> buyer.setKnowMarkets(markets));
        buyers.iterator().forEachRemaining(Buyer::setInitialEndorsements);
    }

    public void run() {
        Console.trace("Simulation: Starting");
        for (int period = 1; period <= periods; ++period) {
            doStep(period);
            Console.trace("Simulation: Period " + period);
        }

        if (Configuration.GUI) {
            Chart.display(buyers, markets);
        }

        buyers.iterator().forEachRemaining(buyer -> Reporter.addEndorsementData(buyer.getEndorsementData()));
        reinit();
    }

    @Override
    public void doStep(int period) {
        buyers.iterator().forEachRemaining(buyer -> buyer.doStep(period));
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "ID=" + Simulation.ID +
                ", periods=" + periods +
                ", buyers=" + buyers.size() +
                ", markets=" + markets.size() +
                '}';
    }
}
