package Simulation;

import Agents.Buyer;
import Agents.Market;

import java.util.ArrayList;

public class Network {
    ArrayList<Buyer> buyers;
    ArrayList<Market> markets;

    public Network(ArrayList<Market> markets, ArrayList<Buyer> buyers) {
        this.markets = markets;
        this.buyers = buyers;
    }
}
