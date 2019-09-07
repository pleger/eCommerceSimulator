import Simulation.*;

// PL: formatear el codigo

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Inicializando....");




        Simulation simulation = SimulationFactory.getSimulation(SimulationFactory.SIMULATION_TYPE_1);
        simulation.runSimulation();
    }
}
