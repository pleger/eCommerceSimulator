package inputManager;

import logger.Console;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private final static int D_PERIODS = 30;
    private final static int D_AGENTS = 10;
    private final static int D_CONTACTS = 17;
    private final static double D_FRIENDS = .7;
    private final static int D_LEVELS = 2; //2 or 3
    private final static int D_REPETITIONS = 0;
    private final static boolean D_GUI = false; //could be removed
    private final static double D_BASE = 1.2;
    private final static int D_MEMORY = -1;    //-1 infinite
    private final static boolean D_SAVED_ENDORSEMENTS = false;

    public static String FILE_NAME;
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
    public static boolean SAVED_ENDORSEMENTS = D_SAVED_ENDORSEMENTS;

    public static void set(HashMap<String, Double> conf) {
        checkConfigurationInput(conf);

        PERIODS = conf.get("PERIODS") != null ? conf.get("PERIODS").intValue() : D_PERIODS;
        AGENTS = conf.get("AGENTS") != null ? conf.get("AGENTS").intValue() : D_AGENTS;
        CONTACTS = conf.get("CONTACTS") != null ? conf.get("CONTACTS").intValue() : D_CONTACTS;
        FRIENDS = conf.get("FRIENDS") != null ? conf.get("FRIENDS") : D_FRIENDS;
        LEVELS = conf.get("LEVELS") != null ? conf.get("LEVELS").intValue() : D_LEVELS;
        REPETITIONS = conf.get("REPETITIONS") != null ? conf.get("REPETITIONS").intValue() : D_REPETITIONS;
        GUI = conf.get("GUI") != null ? conf.get("GUI") == 1 : D_GUI;
        BASE = conf.get("BASE") != null ? conf.get("BASE") : D_BASE;
        MEMORY = conf.get("MEMORY") != null ? conf.get("MEMORY").intValue() : D_MEMORY;
        SAVED_ENDORSEMENTS = conf.get("SAVED_ENDORSEMENTS") != null ? conf.get("SAVED_ENDORSEMENTS") == 1 : D_SAVED_ENDORSEMENTS;
    }

    public static void setFile(String name) {
        FILE_NAME = name;
    }

    public static void setAttributes(int markets, int buyers) {
        set("ATTRIBUTES_M", markets);
        set("ATTRIBUTES_B", buyers);
    }

    public static void setMarkets(int markets) {
        set("MARKETS", markets);
    }

    private static void set(String name, double value) {
        switch (name.toUpperCase()) {
            case "PERIODS":
                PERIODS = (int) value;
                break;
            case "AGENTS":
                AGENTS = (int) value;
                break;
            case "CONTACTS":
                CONTACTS = (int) value;
                break;
            case "FRIENDS":
                FRIENDS = value;
                break;
            case "ATTRIBUTES_M":
                ATTRIBUTES_M = (int) value;
                break;
            case "ATTRIBUTES_B":
                ATTRIBUTES_B = (int) value;
                break;
            case "MARKETS":
                MARKETS = (int) value;
                break;
            case "REPETITIONS":
                REPETITIONS = (int) value;
                break;
            case "LEVELS":
                LEVELS = (int) value;
                break;
            case "GUI":
                GUI = value == 1;
                break;
            case "BASE":
                BASE = value;
                break;
            case "MEMORY":
                MEMORY = (int) value;
                break;
            case "SAVED_ENDORSEMENT":
                SAVED_ENDORSEMENTS = value == 1;
                break;
            default:
                Console.error("CONFIGURATOR.SET: Wrong Parameter");
        }
    }

    private static void checkConfigurationInput(HashMap<String, Double> conf) {
        if (conf.get("PERIODS") == null) {
            Console.warn("PERIODS is missing.");
        }
        if (conf.get("AGENTS") == null) {
            Console.warn("AGENTS is missing.");
        }
        if (conf.get("CONTACTS") == null) {
            Console.warn("CONTACTS is missing.");
        }
        if (conf.get("FRIENDS") == null) {
            Console.warn("LEVELS is missing.");
        }
        if (conf.get("MARKETS") == null) {
            Console.warn("MARKETS is missing.");
        }
        if (conf.get("LEVELS") == null) {
            Console.warn("LEVELS is missing.");
        }
        if (conf.get("REPETITIONS") == null) {
            Console.warn("REPETITIONS is missing.");
        }
        if (conf.get("GUI") == null) {
            Console.warn("GUI is missing.");
        }
        if (conf.get("BASE") == null) {
            Console.warn("BASE is missing.");
        }
        if (conf.get("MEMORY") == null) {
            Console.warn("MEMORY is missing.");
        }
        if (conf.get("SAVED_ENDORSEMENTS") == null) {
            Console.warn("SAVED_ENDORSEMENTS is missing.");
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
        SAVED_ENDORSEMENTS = D_SAVED_ENDORSEMENTS;
    }

    public static Map<String, Double> toMap() {
        Map<String, Double> conf = new HashMap<>();
        conf.put("PERIODS", (double) PERIODS);
        conf.put("AGENTS", (double) AGENTS);
        conf.put("CONTACTS", (double) CONTACTS);
        conf.put("FRIENDS", FRIENDS);
        conf.put("LEVELS", (double) LEVELS);
        conf.put("REPETITIONS", (double) REPETITIONS);
        conf.put("GUI", GUI ? 1.0 : 0.0);
        conf.put("BASE", BASE);
        conf.put("MEMORY", (double) MEMORY);
        conf.put("SAVED_ENDORSEMENTS", SAVED_ENDORSEMENTS ? 1.0 : 0.0);

        return conf;
    }

    public static String toStringConfiguration() {
        String text = "";
        text += "FILE_NAME:" + FILE_NAME + "\n";
        text += "PERIODS: " + PERIODS + "\n";
        text += "AGENTS: " + AGENTS + "\n";
        text += "BASE: " + BASE + "\n";
        text += "MEMORY: " + MEMORY + "\n";
        text += "CONTACTS: " + CONTACTS + "\n";
        text += "FRIENDS: " + FRIENDS + "\n";
        text += "MARKETS: " + MARKETS + "\n";
        text += "ATTRIBUTES_M: " + ATTRIBUTES_M + "\n";
        text += "ATTRIBUTES_B: " + ATTRIBUTES_B + "\n";
        text += "LEVELS: " + LEVELS + "\n";
        text += "REPETITIONS: " + REPETITIONS + "\n";
        text += "SAVED_ENDORSEMENTS: " + SAVED_ENDORSEMENTS + "\n";
        text += "GUI: " + GUI + "\n";

        return text;
    }
}