package Endorsement;

import Agent.Market;
import InputManager.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Endorsement {
    private static final Logger logger = LogManager.getRootLogger();

    private final int period;
    private final Market market;
    private final String attributeName;
    private final double value;

    public Endorsement(int period, Market market, String attributeName, double value) {
        this.period = period;
        this.market = market;
        this.attributeName = attributeName;

        //todo fix!
        logger.assertLog(value <= 0 && value > Configuration.LEVELS, "Wrong Evaluation Scale:" + value);
        this.value = value;
    }

    public int getPeriod() {
        return period;
    }

    public Market getMarket() {
        return market;
    }

    public double getValue() {
        return value;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
