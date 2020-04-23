package Simulation;

import Agent.Buyer;
import Agent.Market;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements FlyWeight, Step {
    private final int periods;
    private final List<Buyer> buyers;
    private final List<Market> markets;
    private final Network network;

    public Simulation(List<Buyer> buyers, List<Market> markets, int periods) {
        this.periods = periods;
        this.buyers = buyers;
        this.markets = markets;
        network = new Network(buyers, markets);

        reinit();
    }
    
    @Override
    public void reinit() {
        network.reinit();
    }

    public void run() {
        for (int period = 0; period < periods; ++period) {
            doStep();
            System.out.println(period);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> doStep() {
        buyers.iterator().forEachRemaining(Buyer::doStep);
        return null;
    }
}
