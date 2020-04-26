package Agent;

import Endorsement.AttributesMarket;
import InputManager.InnerMarket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Market {
    private static final Logger logger = LogManager.getRootLogger();
    private static int counter = 0;

    private final int ID;
    private final String name;
    private final AttributesMarket attributes;

    Market(InnerMarket innerMarket) {
        this(innerMarket.name, innerMarket.attributeNames, innerMarket.attributeValues);
    }

    Market(String name, ArrayList<String> attributeNames, ArrayList<Double[]> values) {
        this.ID = ++counter;
        this.name = name;
        this.attributes = new AttributesMarket(attributeNames, values);
        logger.trace("market:"+this);
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public AttributesMarket getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id="+ID+"," +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

}
