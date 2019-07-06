package Simulation;

public class SimulationFactory {
    public static int SIMULATION_TYPE_1=1;


    public static Simulation getSimulation(int simulationType){
            System.out.println("Creando Simulación...");
            Simulation simulation;
            if(simulationType==SIMULATION_TYPE_1){
                double probabilityType1=0.3;
                double probabilityType2=0.7;
                int maxTime=10;
                double[] probabilities={probabilityType1,probabilityType2};
                simulation=new Simulation(probabilities,maxTime);
                System.out.println("Simulacion creada");
                return simulation;
            }
            return null;
    }
}
