package Endorsement;

import Agent.Market;
import InputManager.Configuration;

public class Endorsement {
    private final int period;
    private final Market market;
    private final String attributeName;
    private final double evaluation;

    public Endorsement(int period, Market market, String attributeName, double evaluation) {
        this.period = period;
        this.market = market;
        this.attributeName = attributeName;
        
        assert evaluation > 0 && evaluation <= Configuration.LEVELS: "Wrong Evaluation Scale";
        this.evaluation = evaluation;
    }

    public int getPeriod() {
        return period;
    }

    public Market getMarket() {
        return market;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public String getAttributeName(){
        return attributeName;
    }
}
