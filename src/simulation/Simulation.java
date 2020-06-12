package simulation;

import agent.Buyer;
import agent.Market;
import gui.Chart;
import inputManager.Configuration;
import logger.Console;
import reporter.Reporter;
import reporter.SalesPerMarketData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Console.info("Simulation: created with "+buyers.size()+" buyers and "+ markets.size()+ " markets");
    }

    @Override
    public void reinit() {
        ++Simulation.ID;
        buyers.iterator().forEachRemaining(Buyer::reinit);
        buyers.iterator().forEachRemaining(buyer -> buyer.setFriends(buyers));
        buyers.iterator().forEachRemaining(buyer -> buyer.setKnowMarkets(filterQuota(markets)));
        buyers.iterator().forEachRemaining(Buyer::setInitialEndorsements);
    }

    private List<Market> filterQuota(List<Market> markets) {
        if (!Configuration.MARKET_QUOTA) {
            return markets; //all markets
        }

        List<Market> filteredMarket = new ArrayList<>();

        double random = Math.random();
        for (Market mk: markets) {
            if (random < mk.getQuota()) {
                filteredMarket.add(mk);
            }
        }
        
        return filteredMarket;
    }

    private void generateSalesPerData(int period) {
        int[] sales = new int[markets.size()];
        buyers.iterator().forEachRemaining(buyer -> {
            Market selectedMarket = buyer.getLastSelectMarked(period);
            if (selectedMarket != null)
                sales[selectedMarket.getID()]++;
        });
        Reporter.addSalesByMarketData(new SalesPerMarketData(ID, period, sales));
    }

    public void run() {
        Console.info("Simulation: Starting " + Simulation.ID);
        for (int period = 1; period <= periods; ++period) {
            doStep(period);
            Console.debug("Simulation: Period " + period);
            generateSalesPerData(period);
        }

        if (Configuration.GUI) {
            //Chart.displaySelection(buyers, markets);
            Chart.displaySales(markets);
        }

        buyers.iterator().forEachRemaining(buyer -> Reporter.addEndorsementData(buyer.getEndorsementData()));
        reinit();
    }

    @Override
    public void doStep(int period) {
        buyers.iterator().forEachRemaining(buyer -> {
            buyer.doStep(period);
        });
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
