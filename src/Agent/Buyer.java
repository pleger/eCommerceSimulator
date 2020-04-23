package Agent;

import Endorsement.AttributesBuyer;
import Endorsement.EndorsementFactory;
import Endorsement.Endorsements;
import InputManager.Configuration;
import InputManager.InnerBuyer;
import Simulation.FlyWeight;
import Simulation.Step;

import java.util.ArrayList;
import java.util.List;

public class Buyer implements Step, FlyWeight {
    private final int ID;
    private final AttributesBuyer attributes;
    private final List<Buyer> friends;
    private final Endorsements endors;

    Buyer(InnerBuyer ib) {
        ID = System.identityHashCode(this);
        this.friends = new ArrayList<>();
        this.endors = new Endorsements();

        ArrayList<Double[]> values = new ArrayList<>();
        for (Double value : ib.attributeValues) {
            values.add(new Double[]{value});
        }

        attributes = new AttributesBuyer(ib.attributeNames, values);
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

    public AttributesBuyer getAttributes() {
        return attributes;
    }

    public void setInitialEndorsements(List<Market> markets) {
        endors.clear();
        markets.iterator().forEachRemaining(market -> endors.addAll(EndorsementFactory.createInitial(-1, this, market)));
    }

    @Override
    public ArrayList<ArrayList<String>> doStep() {
        return null;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "ID=" + ID +
                ", attr=" + attributes +
                '}';
    }

    @Override
    public void reinit() {
        friends.clear();
    }
}
