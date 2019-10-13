package Simulation;

public class Experiment {

    private Experiment() {
    }

    public static void runExperiment() throws Exception {
        int timesRepeatSimulation = SimulationMain.TIMES_REPEAT_SIMULATION;
        for (int i = 0; i < timesRepeatSimulation; i++) {
            SimulationFactory.getSimulation().runSimulation();
        }
    }
}
