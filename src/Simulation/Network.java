package Simulation;

import Agents.Buyer;
import Agents.Market;
import java.util.ArrayList;

public class Network {
    ArrayList<Buyer> buyersInNetwork;
    ArrayList<Market> marketsInNework;

    public Network(ArrayList<Market> marketsInNework, ArrayList<Buyer> buyersInNetwork){
        this.marketsInNework=marketsInNework;
        this.buyersInNetwork=buyersInNetwork;
    }
}
