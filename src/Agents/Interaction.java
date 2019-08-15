package Agents;

import java.util.ArrayList;

public class Interaction {
    private final Market market;
    private final double weight;
    private final int time;
    private final ArrayList<Integer> generatedExperience;
    private double probability;

    public Interaction(Market market, ArrayList<Integer> generatedExperience, double weight, int time) {
        this.market = market;
        this.weight = weight;
        this.time = time;
        this.generatedExperience = generatedExperience;
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

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public ArrayList<Integer> getGeneratedExperience() {
        return this.generatedExperience;
    }
}
