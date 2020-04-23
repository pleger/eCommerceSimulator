import OLD_Simulation.*;

// PL: formatear el codigo

public class OLD_Main {
    public static int TIMES_REPEAT_SIMULATION = 9;
    public static double[] PROBABILITIES = {0.7,0.3};

    public static void main(String[] args) throws Exception {
        System.out.println("Inicializando....");
        Experiment.runExperiment();
    }
}
