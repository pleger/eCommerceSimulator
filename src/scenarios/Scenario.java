package scenarios;

import agent.Market;
import agent.MarketFactory;
import endorsement.AttributesMarket;
import logger.Console;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

public class Scenario {
    private final int id;
    private final String from;
    private final String to;
    private final String[] atts;

    Scenario(int id, String from, String to, String @NotNull [] atts) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.atts = atts.clone();
    }

    public void apply() {
        copyAttributes(MarketFactory.getMarket(from), MarketFactory.getMarket(to), atts);
    }

    public int getId() {
        return id;
    }

    private static void copyAttributes(@NotNull Market from, @NotNull Market to, String[] names) {
        AttributesMarket attFrom = from.getAttributes();
        AttributesMarket attTo = to.getAttributes();
        checkAttributes(attFrom, names);

        AttributesMarket newAttTo = attTo.replaceAll(names, attFrom);
        checkDifference(attTo, newAttTo, names);
        to.setAttributes(newAttTo);
    }

    private static void checkAttributes(@NotNull AttributesMarket attm, String[] names) {
        if (!attm.contains(names)) {
            Console.error("SCENARIO X: some attributes not found");
            System.exit(1);
        }
    }

    private static void checkDifference(AttributesMarket oldAtt, AttributesMarket newAtt, String @NotNull [] names) {
        for (String name : names) {
            Double[] oldValues = oldAtt.getValues(name);
            Double[] newValues = newAtt.getValues(name);

            for (int j = 0; j < oldValues.length; ++j) {
                if (oldValues[j].equals(newValues[j])) {
                    String oldTextValues = StringUtils.join(oldValues, "-");
                    String newTextValues = StringUtils.join(newValues, "-");
                    Console.error("No difference for " + name);
                    Console.error("Old values:[" + oldTextValues + "]   New values:[" + newTextValues + "]");
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (String att : atts) {
            text.append(att).append(",");
        }

        return "Scenario{" +
                "ID=" + this.id +
                ", from=" + this.from +
                ", to=" + this.to +
                ", attributes= {" + text + "}" +
                '}';
    }
}
