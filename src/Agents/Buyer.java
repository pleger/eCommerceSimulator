package Agents;

import java.util.ArrayList;
public class Buyer implements Agent {
    ArrayList<Buyer> friends;
    ArrayList<Market> knownMarkets;
    public void Buyer(){

    }
    public void addFriend(Buyer newFriend){
        friends.add(newFriend);
    }
    public void addKnownMarket(Market newMarket){
        knownMarkets.add(newMarket);
    }
    public void action(){
        System.out.println("Inside buyer's action");
    }
}
