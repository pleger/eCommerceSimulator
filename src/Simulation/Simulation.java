package Simulation;

import Log.Logger;

import java.util.ArrayList;

public class Simulation {
    int iterationTime;
    Network network;
    final int maxTime;
    Logger logger;

    public Simulation(double[] probabilitiesBuyer, int maxTime) {
        iterationTime = 0;
        network = NetworkFactory.getNetwork(NetworkFactory.NETWORK_TYPE_1, probabilitiesBuyer);
        this.maxTime = maxTime;
    }

    public void enableLog(ArrayList<String> headers) {
        logger = new Logger(headers);
    }

    public void runSimulation() {
        while (iterationTime < maxTime) {
            ArrayList<ArrayList<String>> experiences = network.doStep();
            for (ArrayList<String> record : experiences) {
                logger.addLog(record);
            }
            iterationTime++;
        }
        logger.writeLog();
    }
}
