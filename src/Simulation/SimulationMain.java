package Simulation;


import java.util.ArrayList;
import java.util.Arrays;

public class SimulationMain {
    public static int TIMES_REPEAT_SIMULATION = 1; //times to do different simulations
    public static double[] BUYER_PROBABILITIES = {0.7, 0.3}; //simulation probabilities for each type. ONLY WORKS FOR 2 BUYERS
    public static int MAX_TIME = 50; //max time for simulation
    public static ArrayList<String> HEADERS = new ArrayList<>(Arrays.asList("Iteration Time", "Buyer Type", "Market",
            "Weight", "Probability", "Status", "Buyer Id")); //headers for excel file
    public static int BUYER_QUANTITY_NETWORK = 50; //how many buyers will be in a simulation
    public static int MAX_NUMBER_OF_MARKETS = 3; //max number of markets that a buyer knows

}
