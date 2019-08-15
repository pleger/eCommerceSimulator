package Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class SimulationFactory {
    public static int SIMULATION_TYPE_1 = 1;


    public static Simulation getSimulation(int simulationType) {
        System.out.println("Creando Simulación...");
        Simulation simulation;
        if (simulationType == SIMULATION_TYPE_1) {
            double probabilityType1 = 0.3;
            double probabilityType2 = 0.7;
            int maxTime = 30;
            double[] probabilities = {probabilityType1, probabilityType2};
            simulation = new Simulation(probabilities, maxTime);
            ArrayList<String> headers = new ArrayList<>(Arrays.asList("Iteration Time", "Buyer Type", "Market",
                    "Endorsement1", "Endorsement2", "Endorsment3", "Endorsement4", "Endorsement5", "Calculated Weight"
                    , "Probability", "Buyer ID"));
            simulation.enableLog(headers);
            System.out.println("Simulacion creada");
            return simulation;
        }
        return null;
    }
}
