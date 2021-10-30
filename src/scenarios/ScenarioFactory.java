package scenarios;

import agent.MarketFactory;
import inputManager.Configuration;
import logger.Console;

import java.util.ArrayList;
import java.util.List;

public class ScenarioFactory {

    private final static int ALL_FROM_ALIEXPRESS = 0;
    private final static int QUALITY_FROM_ALIEXPRESS = 1;
    private final static int QUALITY_AND_PRICE_FROM_ALIEXPRESS = 2;
    private final static int WEB_FROM_ALIEXPRESS = 3;
    private final static int WEB_AND_QUALITY_FROM_ALIEXPRESS = 4;
    private final static int REPUTATION_SIZE_FROM_ALIEXPRESS = 5;
    private final static int REPUTATION_SIZE_WEB_FROM_ALIEXPRESS = 6;
    private final static int ALL_FROM_LIGHTINTHEBOX = 7;
    private final static int FROM_AMAZON_TO_ALIEXPRESS_1 = 8;
    private final static int FROM_AMAZON_TO_ALIEXPRESS_2 = 9;
    private final static int FROM_AMAZON_TO_ALIEXPRESS_3 = 10;

    private final static List<Scenario> scenarios = new ArrayList<>();

    public static Scenario get(int id) {
        if (scenarios.size() == 0) {
            makeScenarios();
        }
        return getScenario(id);
    }

    private static Scenario getScenario(int id) {
        for (Scenario sc : scenarios) {
            if (sc.getId() == id) {
                return sc;
            }
        }
        Console.error("ScenarioFactory.getScenario: Wrong Scenario: " + Configuration.SCENARIO);
        System.exit(1);
        return null;
    }

    private static void makeScenarios() {
        String from = "ALIEXPRESS";
        String to = "WISH";

        scenarios.add(new Scenario(ALL_FROM_ALIEXPRESS, from, to, MarketFactory.getMarket(to).getAttributes().getNames()));
        scenarios.add(new Scenario(QUALITY_FROM_ALIEXPRESS, from, to, new String[]{"PRODUCT QUALITY", "SERVICE QUALITY", "PRODUCTS OFFERING"}));
        scenarios.add(new Scenario(QUALITY_AND_PRICE_FROM_ALIEXPRESS, from, to, new String[]{"PRODUCT QUALITY", "SERVICE QUALITY", "COST SAVING"}));
        scenarios.add(new Scenario(WEB_FROM_ALIEXPRESS, from, to, new String[]{"WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY",
                "SECURITY OF THE WEB"}));
        scenarios.add(new Scenario(WEB_AND_QUALITY_FROM_ALIEXPRESS, from, to, new String[]{"WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY",
                "SECURITY OF THE WEB", "PRODUCT QUALITY", "SERVICE QUALITY", "PRODUCTS OFFERING"}));
        scenarios.add(new Scenario(REPUTATION_SIZE_FROM_ALIEXPRESS, from, to, new String[]{"REPUTATION OF THE CHINESE B2C MARKETPLACE", "COMPANY SIZE"}));
        scenarios.add(new Scenario(REPUTATION_SIZE_WEB_FROM_ALIEXPRESS, from, to, new String[]{"REPUTATION OF THE CHINESE B2C MARKETPLACE", "COMPANY SIZE", "WEBSITE APPEARANCE",
                "WEBSITE CONTENT QUALITY", "WEBSITE TECHNICAL QUALITY", "SECURITY OF THE WEB"}));
        scenarios.add(new Scenario(ALL_FROM_LIGHTINTHEBOX, "LIGHTINTHEBOX", to, MarketFactory.getMarket(to).getAttributes().getNames()));
        scenarios.add(new Scenario(FROM_AMAZON_TO_ALIEXPRESS_1, "AMAZON", "ALIEXPRESS", new String[]{"PRODUCTS OFFERING", "PRODUCT QUALITY", "SERVICE QUALITY"}));
        scenarios.add(new Scenario(FROM_AMAZON_TO_ALIEXPRESS_2, "AMAZON", "ALIEXPRESS", new String[]{"WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY",
                "WEBSITE TECHNICAL QUALITY", "SECURITY OF THE WEB", "PRIVACY"}));
        scenarios.add(new Scenario(FROM_AMAZON_TO_ALIEXPRESS_3, "AMAZON", "ALIEXPRESS", new String[]{"PRODUCTS OFFERING", "PRODUCT QUALITY", "SERVICE QUALITY","WEBSITE APPEARANCE", "WEBSITE CONTENT QUALITY",
                "WEBSITE TECHNICAL QUALITY", "SECURITY OF THE WEB", "PRIVACY"}));
    }
}
