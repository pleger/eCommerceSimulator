import Agent.Buyer;
import Agent.BuyerFactory;
import Agent.Market;
import Agent.MarketFactory;
import InputManager.Configuration;
import InputManager.Loader;
import Reporter.Reporter;
import Simulation.Simulation;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {
    private static final String FILE_IN = "input";

    private static List<Buyer> buyers;
    private static List<Market> markets;

    private static void loadDataFromInput() {
        Loader.read(FILE_IN); //load from file
        buyers = BuyerFactory.createFromInput();
        markets = MarketFactory.createFromInput();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LogManager.getRootLogger();
        logger.setLevel(Level.TRACE);

        loadDataFromInput();

        logger.trace(Configuration.toStringConfiguration());
        Simulation s = new Simulation(buyers, markets, Configuration.PERIODS);
        for (int i = 0; i < Configuration.REPETITIONS + 1; ++i) {
            logger.trace("REPETITION:" + i);
            s.run();
        }
        Reporter.write();
    }
}
