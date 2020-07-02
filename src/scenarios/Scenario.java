package scenarios;

import agent.Market;
import agent.MarketFactory;
import endorsement.Attributes;
import endorsement.AttributesMarket;
import inputManager.Configuration;
import logger.Console;

public class Scenario {
    private final static int COPY_ALL_FROM_ALIEXPRESS = 1;
    private final static int COPY_QUALITY_FROM_ALIEXPRESS = 2;
    private final static int COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS = 3;
    private final static String MARKET_TO_MODIFY = "WISH";

    private final static int PERIOD = 100;

    public static void apply(int period) {
        if (Configuration.SCENARIO != -1 && period == PERIOD)  {
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
                default: Console.error("SCENARIO.apply: Wrong Parameter: "+Configuration.SCENARIO);
            }
        }
    }

    private static void COPY_ALL_FROM_ALIEXPRESS() {
      Market marketAli = MarketFactory.getMarket("ALIEXPRESS");
      Market marketWish = MarketFactory.getMarket(MARKET_TO_MODIFY);

      marketWish.setAttributes(marketAli.getAttributes());
    }

    private static void COPY_QUALITY_FROM_ALIEXPRESS() {
        String[] attNames = new String[]{"PRODUCT QUALITY", "SERVICE QUALITY"};
        AttributesMarket attMarket = MarketFactory.getMarket("ALIEXPRESS").getAttributes();
        Market marketWish = MarketFactory.getMarket(MARKET_TO_MODIFY);

        Double[] productQualityValues = attMarket.getValues(attNames[0]);
        Double[] serviceQualityValues = attMarket.getValues(attNames[1]);

        Attributes newAttributes = marketWish.getAttributes().replace(attNames[0], productQualityValues).
                replace(attNames[0], serviceQualityValues);

        marketWish.setAttributes((AttributesMarket) newAttributes);
    }

    private static void COPY_QUALITY_AND_PRICE_FROM_ALIEXPRESS() {
        COPY_QUALITY_FROM_ALIEXPRESS();

        String attrName = "COST SAVING";
        AttributesMarket attMarket = MarketFactory.getMarket("ALIEXPRESS").getAttributes();

        Double[] costSavingValues = attMarket.getValues(attrName);
        Market marketWish = MarketFactory.getMarket(MARKET_TO_MODIFY);

        Attributes newAttributes = marketWish.getAttributes().replace(attrName, costSavingValues);
        marketWish.setAttributes((AttributesMarket) newAttributes);
    }
}
