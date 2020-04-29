import agent.Buyer;
import agent.BuyerFactory;
import agent.Market;
import agent.MarketFactory;
import inputManager.Configuration;
import inputManager.Loader;
import reporter.Reporter;
import simulation.Simulation;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {
    public static final String FILE_NAME = "TEST";

    private static List<Buyer> buyers;
    private static List<Market> markets;

    private static void loadDataFromFile() {
        Loader.read(FILE_NAME);
        buyers = BuyerFactory.createFromInput();
        markets = MarketFactory.createFromInput();

    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LogManager.getRootLogger();
        logger.setLevel(Level.TRACE);

        loadDataFromFile();

        logger.trace("MAIN: Configuration loaded -> {" + Configuration.toStringConfiguration() + " }");
        Simulation s = new Simulation(buyers, markets, Configuration.PERIODS);
        for (int i = 1; i <= Configuration.REPETITIONS + 1; ++i) {
            logger.trace(s);
            s.run();
        }
        Reporter.write();
        logger.trace("Main: End.");
    }
}
