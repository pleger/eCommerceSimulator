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
    public ArrayList<ArrayList<String>> doStep(){
        ArrayList<ArrayList<String>> allExperiences=new ArrayList<>();
        for(Buyer buyer: buyers){
            ArrayList<ArrayList<String>> experiences=buyer.action();
            for(ArrayList<String> singleExperience:experiences){
                //adds the id of the buyer
                singleExperience.add(Integer.toString(buyers.indexOf(buyer)));
                allExperiences.add(singleExperience);
            }
        }
        return allExperiences;
    }
}
