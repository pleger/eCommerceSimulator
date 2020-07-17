import agent.Buyer;
import agent.BuyerFactory;
import agent.Market;
import agent.MarketFactory;
import inputManager.Configuration;
import inputManager.Loader;
import logger.Console;
import reporter.Reporter;
import simulation.Simulation;

import java.util.List;

public class Main {
    public static final String FILE_NAME = "SCENARIO";

    private static List<Buyer> buyers;
    private static List<Market> markets;

    private static void loadDataFromFile(String file) {
        file = file.equals("") ? FILE_NAME : file;
        Configuration.setPath(file);
        Loader.read();

        buyers = BuyerFactory.createFromInput();
        markets = MarketFactory.createFromInput();
    }

    public static void main(String[] args) {
        loadDataFromFile(args.length > 0 ? args[0] : "");

        Console.info("MAIN: Configuration loaded -> {" + Configuration.toStringConfiguration() + " }");
        Simulation s = new Simulation(buyers, markets, Configuration.PERIODS);
        for (int i = 1; i <= Configuration.REPETITIONS + 1; ++i) {
            Console.info(s);
            s.run();
        }
        Reporter.write();
        Console.info("Main: End.");
    }
}
