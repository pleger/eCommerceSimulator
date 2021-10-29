import agent.BuyerFactory;
import agent.MarketFactory;
import inputManager.Configuration;
import inputManager.Loader;
import logger.Console;
import reporter.Reporter;
import simulation.Simulation;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static final String FILE_NAME = "AMAZON_SCENARIO_8";

    private static String getInputFileName(String[] args) {
        String inputFileName;
        String potentialName = "";

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            potentialName = br.readLine();
        } catch (Exception e) {
            System.out.println("MAIN: input.txt not found");
        }

        inputFileName = args.length > 0? args[0]: potentialName.equals("")? FILE_NAME: potentialName;
        return inputFileName;
    }

    private static void loadDataFromFile(String file) {
        Configuration.setPath(file);
        Console.info("Reading input from:" + file);
        Loader.read("input");
    }

    public static void main(String[] args) {
        loadDataFromFile(getInputFileName(args));

        Console.info("MAIN: Configuration loaded -> {" + Configuration.toStringConfiguration() + " }");
        Simulation s = new Simulation(BuyerFactory.createFromInput(), MarketFactory.createFromInput(), Configuration.PERIODS);
        for (int i = 1; i <= Configuration.REPETITIONS + 1; ++i) {
            Console.info(s);
            s.run();
        }

        Reporter.write();
        Console.end("Main: End.");
    }
}
