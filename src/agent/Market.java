package agent;

import endorsement.AttributesMarket;
import inputManager.InnerMarket;
import logger.Console;
import org.jetbrains.annotations.NotNull;

public class Market {
    private static int counter = 0;

    private final int ID;
    private final String name;
    private final double quota;
    private final AttributesMarket attributes;
    
    Market(@NotNull InnerMarket innerMarket) {
        this.ID = counter++;
        this.name = innerMarket.name;
        this.quota = innerMarket.quota;
        this.attributes = new AttributesMarket(innerMarket.attributeNames, innerMarket.attributeValues);
        Console.info("Market:" + this);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getQuota() {
        return quota;
    }

    public AttributesMarket getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id=" + ID + "," +
                "name='" + name + '\'' + "," +
                "quota='" + quota + '\'' + "," +
                "attributes=" + attributes +
                '}';
    }
}
