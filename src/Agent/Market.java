package Agent;

import Endorsement.AttributesMarket;
import InputManager.InnerMarket;
import Simulation.Step;

import java.util.ArrayList;

public class Market implements Step {

    private final String name;
    private final AttributesMarket attributes;

    Market(InnerMarket innerMarket) {
        this(innerMarket.name, innerMarket.attributeNames, innerMarket.attributeValues);
    }

    Market(String name, ArrayList<String> attributeNames, ArrayList<Double[]> values) {
        this.name = name;
        this.attributes = new AttributesMarket(attributeNames, values);
    }

    public String getName(){
        return name;
    }

    public AttributesMarket getAttributes() {
        return attributes;
    }

    @Override
    public ArrayList<ArrayList<String>> doStep() {
        return null;
    }

    @Override
    public String toString() {
        return "Market{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

}
