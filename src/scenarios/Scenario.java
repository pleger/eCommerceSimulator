package scenarios;

import agent.Market;
import agent.MarketFactory;
import endorsement.AttributesMarket;
import inputManager.Configuration;
import logger.Console;
import org.apache.commons.lang.StringUtils;

public class Scenario {
    private final static int COPY_ALL_FROM_ALIEXPRESS = 1;
    private final static int COPY_QUALITY_FROM_ALIEXPRESS = 2;
    private final static int COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS = 3;
    private final static int COPY_WEB_FROM_ALIEXPRESS = 4;
    private final static int COPY_WEB_AND_QUALITY_FROM_ALIEXPRESS = 5;
    private final static int COPY_REPUTATION_SIZE_FROM_ALIEXPRESS = 6;
    private final static int COPY_REPUTATION_SIZE_WEB_FROM_ALIEXPRESS = 7;
    private final static int COPY_ALL_FROM_LIGHTINTHEBOX = 8;
    private final static String MARKET_FROM = "ALIEXPRESS";
    private final static String MARKET_TO = "WISH";

    private final static int PERIOD = 100;

    public static void apply(int period) {
        if (Configuration.SCENARIO != -1 && period == PERIOD) {
            switch (Configuration.SCENARIO) {
                case COPY_ALL_FROM_ALIEXPRESS:
                    COPY_ALL_FROM_ALIEXPRESS();
                    break;
                case COPY_QUALITY_FROM_ALIEXPRESS:
                    COPY_QUALITY_FROM_ALIEXPRESS();
                    break;
                case COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS:
                    COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS();
                    break;
                case COPY_WEB_FROM_ALIEXPRESS:
                    COPY_WEB_FROM_ALIEXPRESS();
                    break;
                case COPY_WEB_AND_QUALITY_FROM_ALIEXPRESS:
                    COPY_WEB_AND_QUALITY_FROM_ALIEXPRESS();
                    break;
                case COPY_REPUTATION_SIZE_FROM_ALIEXPRESS:
                    COPY_REPUTATION_SIZE_FROM_ALIEXPRESS();
                    break;
                case COPY_REPUTATION_SIZE_WEB_FROM_ALIEXPRESS:
                    COPY_REPUTATION_SIZE_WEB_FROM_ALIEXPRESS();
                    break;
                case COPY_ALL_FROM_LIGHTINTHEBOX:
                    COPY_ALL_FROM_LIGHTINTHEBOX();
                    break;
                default:
                    Console.error("SCENARIO.apply: Wrong Parameter: " + Configuration.SCENARIO);
            }
        }
    }

    private static void COPY_ALL_FROM_LIGHTINTHEBOX() {
        Market marketAli = MarketFactory.getMarket(MARKET_FROM);
        Market marketLight = MarketFactory.getMarket("LIGHTINTHEBOX");

        Console.info("--- ANTES ALI:" + marketAli);
        marketAli.setAttributes(marketLight.getAttributes());
        Console.info("ALI:" + marketAli);
        Console.info("LIGHTINBOX:" + marketLight);
    }

    private static void COPY_ALL_FROM_ALIEXPRESS() {
        Market marketAli = MarketFactory.getMarket(MARKET_FROM);
        Market marketWish = MarketFactory.getMarket(MARKET_TO);

        Console.info("--- ANTES WISH:" + MarketFactory.getMarket(MARKET_TO));
        marketWish.setAttributes(marketAli.getAttributes());
        Console.info("WISH:" + MarketFactory.getMarket(MARKET_TO));
        Console.info("ALIEXPRESS:" + MarketFactory.getMarket(MARKET_FROM));
    }

    private static void COPY_QUALITY_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"PRODUCT QUALITY", "SERVICE QUALITY","PRODUCTS OFFERING"};

        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
    }

    private static void COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"PRODUCT QUALITY", "SERVICE QUALITY", "COST SAVING"};

        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
    }

    private static void COPY_WEB_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY",
                "SECURITY OF THE WEB"};

        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
    }

    private static void COPY_WEB_AND_QUALITY_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY",
                "SECURITY OF THE WEB", "PRODUCT QUALITY", "SERVICE QUALITY", "PRODUCTS OFFERING"};

        Console.info("ANTES ---- WISH:" + MarketFactory.getMarket(MARKET_TO));
        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
        Console.info("WISH:" + MarketFactory.getMarket(MARKET_TO));
        Console.info("ALIEXPRESS:" + MarketFactory.getMarket(MARKET_FROM));
    }

    private static void COPY_REPUTATION_SIZE_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"REPUTATION OF THE CHINESE B2C MARKETPLACE", "COMPANY SIZE"};

        Console.info("ANTES ---- WISH:" + MarketFactory.getMarket(MARKET_TO));
        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
        Console.info("WISH:" + MarketFactory.getMarket(MARKET_TO));
        Console.info("ALIEXPRESS:" + MarketFactory.getMarket(MARKET_FROM));
    }

    private static void COPY_REPUTATION_SIZE_WEB_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"REPUTATION OF THE CHINESE B2C MARKETPLACE", "COMPANY SIZE", "WEBSITE APPEARANCE",
                "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY", "SECURITY OF THE WEB"};

        Console.info("ANTES ---- WISH:" + MarketFactory.getMarket(MARKET_TO));
        copyAttributes(MarketFactory.getMarket(MARKET_FROM), MarketFactory.getMarket(MARKET_TO), attNames);
        Console.info("WISH:" + MarketFactory.getMarket(MARKET_TO));
        Console.info("ALIEXPRESS:" + MarketFactory.getMarket(MARKET_FROM));
    }


    private static void checkAttributes(AttributesMarket attm, String[] names) {
        if (!attm.contains(names)) {
            Console.error("SCENARIO X: some attributes not found");
            System.exit(1);
        }
    }

    private static void copyAttributes(Market from, Market to, String[] names) {
        AttributesMarket attFrom = from.getAttributes();
        AttributesMarket attTo = to.getAttributes();
        checkAttributes(attFrom, names);

        AttributesMarket newAttTo = attTo.replaceAll(names, attFrom);
        checkDifference(attTo, newAttTo, names);
        to.setAttributes(newAttTo);
    }

    private static void checkDifference(AttributesMarket oldAtt, AttributesMarket newAtt, String[] names) {
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
}
