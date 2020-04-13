package InputManager;

import java.util.HashMap;

public class Configuration {

    private final static int D_ITERATIONS = 30;
    private final static int D_AGENTS = 10;
    private final static int D_CONTACTS = 17;
    private final static double D_FRIENDS = .7;
    private final static int D_MARKETS = 5;
    private final static int D_ENDORSEMENTS = 13;
    private final static int D_LEVELS = 2; //2 or 3
    private final static int D_EXPERIMENTS = 0;
    private final static boolean D_GUI = false;

    public static int ITERATIONS = D_ITERATIONS;
    public static int AGENTS = D_AGENTS;
    public static int CONTACTS = D_CONTACTS;
    public static double FRIENDS = D_FRIENDS;
    public static int MARKETS = D_MARKETS;
    public static int ENDORSEMENTS = D_ENDORSEMENTS;
    public static int LEVELS = D_LEVELS; //2 or 3
    public static int EXPERIMENTS = D_EXPERIMENTS;
    public static boolean GUI = D_GUI;

    public static void set(HashMap<String, Double> conf) {
        ITERATIONS = conf.get("ITERATIONS") != null? conf.get("ITERATIONS").intValue() : D_ITERATIONS;
        AGENTS = conf.get("ITERATIONS") != null? conf.get("AGENTS").intValue() : D_AGENTS;
        CONTACTS = conf.get("CONTACTS") != null? conf.get("CONTACTS").intValue() : D_CONTACTS;
        FRIENDS = conf.get("FRIENDS") != null? conf.get("FRIENDS") : D_FRIENDS;
        MARKETS = conf.get("MARKETS") != null? conf.get("MARKETS").intValue() : D_MARKETS;
        ENDORSEMENTS = conf.get("ENDORSEMENTS") != null? conf.get("ENDORSEMENTS").intValue() : D_ENDORSEMENTS;
        LEVELS = conf.get("LEVELS") != null? conf.get("LEVELS").intValue() : D_LEVELS;
        EXPERIMENTS = conf.get("EXPERIMENTS") != null? conf.get("EXPERIMENTS").intValue() : D_EXPERIMENTS;
        GUI = conf.get("GUI") != null? conf.get("GUI") == 1 : D_GUI;
    }

    public static void setDefault() {
        ITERATIONS = D_ITERATIONS;
        AGENTS = D_AGENTS;
        CONTACTS = D_CONTACTS;
        FRIENDS = D_FRIENDS;
        MARKETS = D_MARKETS;
        ENDORSEMENTS = D_ENDORSEMENTS;
        LEVELS = D_LEVELS;
        EXPERIMENTS = D_EXPERIMENTS;
        GUI = D_GUI;
    }

    public static String configuration() {
        String text = "";
        text += "ITERATIONS:" + ITERATIONS + "\n";
        text += "AGENTS:" + AGENTS + "\n";
        text += "CONTACTS:" + CONTACTS + "\n";
        text += "FRIENDS:" + FRIENDS + "\n";
        text += "MARKETS:" + MARKETS + "\n";
        text += "ENDORSEMENTS:" + ENDORSEMENTS + "\n";
        text += "LEVELS:" + LEVELS + "\n";
        text += "EXPERIMENTS:" + EXPERIMENTS + "\n";
        text += "GUI:" + GUI + "\n";

        return text;
    }
}
