package Simulation;

import java.util.ArrayList;

public class SimulationFactory {


    public static Simulation getSimulation() {
        System.out.println("Creando Simulación...");

        int maxTime = SimulationMain.MAX_TIME;
        ArrayList<String> headers = SimulationMain.HEADERS;

        Simulation simulation = new Simulation(maxTime);
        simulation.enableLog(headers);

        System.out.println("Simulacion creada");
        return simulation;
    }
}
