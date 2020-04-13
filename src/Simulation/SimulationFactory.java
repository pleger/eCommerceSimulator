package Simulation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SimulationFactory {

    public static @NotNull
    Simulation getSimulation() {
        System.out.println("Creando Simulación...");

        int maxTime = SimulationMain.MAX_TIME;
        ArrayList<String> headers = SimulationMain.HEADERS;

        Simulation simulation = new Simulation(maxTime);
        simulation.enableLog(headers);

        System.out.println("Simulacion creada");
        return simulation;
    }
}
