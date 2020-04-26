package InputManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private static final Logger logger = LogManager.getRootLogger();

    private final static int D_PERIODS = 30;
    private final static int D_AGENTS = 10;
    private final static int D_CONTACTS = 17;
    private final static double D_FRIENDS = .7;
    private final static int D_LEVELS = 2; //2 or 3
    private final static int D_REPETITIONS = 0;
    private final static boolean D_GUI = false; //could be removed
    private final static double D_BASE = 1.2;
    private final static int D_MEMORY = -1;    //-1 infinite
    private final static String D_OUTPUT_FILE = "output";

    public static int MARKETS;
    public static int ATTRIBUTES_M;
    public static int ATTRIBUTES_B;

    public static int PERIODS = D_PERIODS;
    public static int AGENTS = D_AGENTS;
    public static int CONTACTS = D_CONTACTS;
    public static double FRIENDS = D_FRIENDS;
    public static int LEVELS = D_LEVELS; //2 or 3
    public static int REPETITIONS = D_REPETITIONS;
    public static boolean GUI = D_GUI;
    public static double BASE = D_BASE;
    public static int MEMORY = D_MEMORY;
    public static String OUTPUT_FILE = D_OUTPUT_FILE;

    public static void set(HashMap<String, Double> conf) {
        checkConfigurationInput(conf);

        PERIODS = conf.get("PERIODS") != null ? conf.get("PERIODS").intValue() : D_PERIODS;
        AGENTS = conf.get("AGENTS") != null ? conf.get("AGENTS").intValue() : D_AGENTS;
        CONTACTS = conf.get("CONTACTS") != null ? conf.get("CONTACTS").intValue() : D_CONTACTS;
        FRIENDS = conf.get("FRIENDS") != null ? conf.get("FRIENDS") : D_FRIENDS;
        LEVELS = conf.get("LEVELS") != null ? conf.get("LEVELS").intValue() : D_LEVELS;
        REPETITIONS = conf.get("REPETITIONS") != null ? conf.get("REPETITIONS").intValue() : D_REPETITIONS;
        GUI = conf.get("GUI") != null ? conf.get("GUI") == 1 : D_GUI;
        BASE = conf.get("BASE") != null ? conf.get("BASE")  : D_BASE;
        MEMORY = conf.get("MEMORY") != null ? conf.get("MEMORY").intValue() : D_MEMORY;
        OUTPUT_FILE = conf.get("OUTPUT_FILE") != null ? conf.get("OUTPUT_FILE").toString() : OUTPUT_FILE;
    }

    public static void setAttributes(int markets, int buyers) {
        set("ATTRIBUTES_M", markets);
        set("ATTRIBUTES_B", buyers);
    }

    public static void setMarkets(int markets) {
        set("MARKETS", markets);
    }

    private static void set(String name, double value)  {
        switch (name.toUpperCase()) {
            case "PERIODS": PERIODS = (int) value; break;
            case "AGENTS": AGENTS = (int) value; break;
            case "CONTACTS": CONTACTS = (int) value; break;
            case "FRIENDS": FRIENDS =  value; break;
            case "ATTRIBUTES_M": ATTRIBUTES_M = (int) value; break;
            case "ATTRIBUTES_B": ATTRIBUTES_B = (int) value; break;
            case "MARKETS": MARKETS = (int) value; break;
            case "REPETITIONS": REPETITIONS = (int) value; break;
            case "LEVELS": LEVELS = (int) value; break;
            case "GUI": GUI = value == 1; break;
            case "BASE": BASE = value; break;
            case "MEMORY": MEMORY = (int) value; break;
            default: logger.error("CONFIGURATOR.SET: Wrong Parameter");
        }
    }

    private static void checkConfigurationInput(HashMap<String, Double> conf) {
        if (conf.get("PERIODS") == null) {
            logger.warn("PERIODS is missing.");
        }
        if (conf.get("AGENTS") == null) {
            logger.warn("AGENTS is missing.");
        }
        if (conf.get("CONTACTS") == null) {
            logger.warn("CONTACTS is missing.");
        }
        if (conf.get("FRIENDS") == null) {
            logger.warn("LEVELS is missing.");
        }
        if (conf.get("MARKETS") == null) {
            logger.warn("MARKETS is missing.");
        }
        if (conf.get("LEVELS") == null) {
            logger.warn("LEVELS is missing.");
        }
        if (conf.get("REPETITIONS") == null) {
            logger.warn("REPETITIONS is missing.");
        }
        if (conf.get("GUI") == null) {
            logger.warn("GUI is missing.");
        }
        if (conf.get("BASE") == null) {
            logger.warn("BASE is missing.");
        }
        if (conf.get("MEMORY") == null) {
            logger.warn("MEMORY is missing.");
        }
        if (conf.get("OUTPUT_FILE") == null) {
            logger.warn("OUTPUT_FILE is missing.");
        }
    }
    
    public static void setDefault() {
        PERIODS = D_PERIODS;
        AGENTS = D_AGENTS;
        CONTACTS = D_CONTACTS;
        FRIENDS = D_FRIENDS;
        LEVELS = D_LEVELS;
        REPETITIONS = D_REPETITIONS;
        GUI = D_GUI;
        BASE = D_BASE;
        MEMORY = D_MEMORY;
        OUTPUT_FILE= D_OUTPUT_FILE;
    }

    public static Map<String, Double> toMap() {
        Map<String,Double> conf = new HashMap<>();
        conf.put("Periods",(double) PERIODS);
        conf.put("AGENT",(double) AGENTS);
        conf.put("CONTACTS",(double) CONTACTS);
        conf.put("FRIENDS", FRIENDS);
        conf.put("LEVELS", (double) LEVELS);
        conf.put("REPETITIONS", (double) REPETITIONS);
        conf.put("GUI", GUI? 1.0:0.0);
        conf.put("BASE", BASE);
        conf.put("MEMORY", (double) MEMORY);

        return conf;
    }

    public static String toStringConfiguration() {
        String text = "";
        text += "PERIODS: " + PERIODS + "\n";
        text += "AGENTS: " + AGENTS + "\n";
        text += "CONTACTS: " + CONTACTS + "\n";
        text += "FRIENDS: " + FRIENDS + "\n";
        text += "MARKETS: " + MARKETS + "\n";
        text += "ATTRIBUTES_M: " + ATTRIBUTES_M + "\n";
        text += "ATTRIBUTES_B: " + ATTRIBUTES_B + "\n";
        text += "LEVELS: " + LEVELS + "\n";
        text += "REPETITIONS: " + REPETITIONS + "\n";
        text += "GUI: " + GUI + "\n";
        text += "BASE: " + BASE + "\n";
        text += "MEMORY: " + MEMORY + "\n";
        text += "OUTPUT_FILE: " + OUTPUT_FILE + "\n";

        return text;
    }
}
