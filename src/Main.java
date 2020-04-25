import Agent.Buyer;
import Agent.BuyerFactory;
import Agent.Market;
import Agent.MarketFactory;
import GUI.InformationPanel;
import InputManager.Configuration;
import InputManager.Loader;
import Simulation.Simulation;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.util.List;

public class Main {
    private static final String FILE = "input.xlsx";

    private static List<Buyer> buyers;
    private static List<Market> markets;

    private static void createFromInput() {
        Loader.read(FILE); //load from file
        buyers = BuyerFactory.createFromInput();
        markets = MarketFactory.createFromInput();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LogManager.getRootLogger();
        logger.setLevel(Level.TRACE);

        createFromInput();

        logger.trace(Configuration.toStringConfiguration());
        Simulation s = new Simulation(buyers, markets, Configuration.PERIODS);
        s.reinit();
        for (int i = 0; i < Configuration.REPETITIONS + 1; ++i) {
            logger.trace("REPETITION:" + i);
            s.run();
        }
    }
}
