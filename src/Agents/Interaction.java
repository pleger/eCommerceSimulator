package Agents;

public class Interaction {
    final Market market;
    final double weight;
    final int time;

    public Interaction(Market market, double weight, int time) {
        this.market = market;
        this.weight = weight;
        this.time = time;
    }

    public Market getMarket() {
        return this.market;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getTime() {
        return this.time;
    }
}
