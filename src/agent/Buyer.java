package agent;

import endorsement.AttributesBuyer;
import endorsement.EndorsementFactory;
import endorsement.Endorsements;
import gui.DataChart;
import inputManager.Configuration;
import inputManager.InnerBuyer;
import logger.Console;
import org.jetbrains.annotations.NotNull;
import reporter.Reporter;
import reporter.IterationData;
import reporter.EndorsementData;
import simulation.FlyWeight;
import simulation.Simulation;
import simulation.Step;

import java.util.ArrayList;
import java.util.List;

public class Buyer implements Step, FlyWeight {
    private static int counter = 0;

    private final int ID;
    private final AttributesBuyer attribute;
    private final List<Buyer> friends;
    private final Endorsements endors;
    private List<Market> knownMarkets;

    private final DataChart data;
    private double evaluation;

    Buyer(@NotNull InnerBuyer ib) {
        this.ID = counter++;
        this.friends = new ArrayList<>();
        this.knownMarkets = new ArrayList<>();
        this.endors = new Endorsements();

        ArrayList<Double[]> values = new ArrayList<>();
        for (Double value : ib.attributeValues) {
            values.add(new Double[]{value});
        }

        attribute = new AttributesBuyer(ib.attributeNames, values);
        data = new DataChart(Integer.toString(ID));

        Console.info("Buyer:" + this);
    }

    public void setFriends(List<Buyer> buyers) {
        int friendCounter = 0;
        int friendSize = (int) (Configuration.CONTACTS * Configuration.FRIENDS);

        while (friendCounter < friendSize) {
            Buyer potentialContact = buyers.get((int) (Math.random() * buyers.size()));
            if (addFriend(potentialContact)) {
                ++friendCounter;
            }
        }
    }

    private boolean addFriend(Buyer potentialContact) {
        if (!friends.contains(potentialContact)) {
            friends.add(potentialContact);
            return true;
        }
        return false;
    }

    public Endorsements getEndorsements() {
        return endors;
    }

    public AttributesBuyer getAttribute() {
        return attribute;
    }

    public int getID() {
        return ID;
    }

    public DataChart getDataSeries() {
        return data;
    }

    public void setInitialEndorsements() {
        endors.clear();
        knownMarkets.iterator().forEachRemaining(market -> endors.addAll(EndorsementFactory.createInitial(-1, this, market)));
    }

    public void setKnowMarkets(List<Market> markets) {
        this.knownMarkets = new ArrayList<>(markets);
    }

    @Override
    public void doStep(int period) {
        if (knownMarkets.size() > 0) { //buyer could not ignore all markets
            endors.addAll(Interaction.interact(period, this, knownMarkets));

            //adding data
            data.addData(period, endors.getSelectedMarket(period).getID());
            Reporter.addIterationData(new IterationData(Simulation.ID, period, getID(), getLastSelectMarked(period).getName(), evaluation));
        }
    }

    public void setCurrentEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public ArrayList<EndorsementData> getEndorsementData() {
        ArrayList<EndorsementData> endorsData = new ArrayList<>();
        endors.forEach(endor -> endorsData.add(new EndorsementData(Simulation.ID, endor.getPeriod(), ID, endor.getMarket().getName(),
                endor.getAttributeName(), endor.getValue())));

        return endorsData;
    }

    public Market getLastSelectMarked(int period) {
      return endors.getSelectedMarket(period);
    }

    @Override
    public void reinit() {
        evaluation = 0;
        endors.clear();
        friends.clear();
        knownMarkets.clear();
    }

    @Override
    public String toString() {
        StringBuilder attributeValue = new StringBuilder();
        StringBuilder knowMks = new StringBuilder();

        for (int i = 0; i < attribute.size(); ++i) {
            attributeValue.append(attribute.getName(i)).append("[").append(attribute.getValue(i)).append("]");
        }

        for (Market knownMarket : knownMarkets) {
            knowMks.append(knownMarket.getName()).append(",");
        }

        return "Buyer{" +
                "ID=" + ID +
                ", attribute=" + attributeValue +
                ", knownMarkets={" + knowMks + "}" +
                '}';
    }
}

