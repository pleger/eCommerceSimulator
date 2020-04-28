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
    private static final String FILE_IN = "input";

    private static List<Buyer> buyers;
    private static List<Market> markets;

    private static void loadDataFromFile() {
        Loader.read(FILE_IN);
        buyers = BuyerFactory.createFromInput();
        markets = MarketFactory.createFromInput();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LogManager.getRootLogger();
        logger.setLevel(Level.TRACE);

        loadDataFromFile();

        logger.trace(Configuration.toStringConfiguration());
        Simulation s = new Simulation(buyers, markets, Configuration.PERIODS);
        for (int i = 0; i < Configuration.REPETITIONS + 1; ++i) {
            logger.trace("REPETITION:" + i);
            s.run();
        }
        Reporter.write();
    }
}
