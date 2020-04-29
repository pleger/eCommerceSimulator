package agent;

import endorsement.AttributesMarket;
import inputManager.InnerMarket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class Market {
    private static final Logger logger = LogManager.getRootLogger();
    private static int counter = 0;

    private final int ID;
    private final String name;
    private final AttributesMarket attributes;

    Market(@NotNull InnerMarket innerMarket) {
        this.ID = counter++;
        this.name = innerMarket.name;
        this.attributes = new AttributesMarket(innerMarket.attributeNames, innerMarket.attributeValues);
        logger.trace("Market:"+this);
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
