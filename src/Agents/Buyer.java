package Agents;

import java.util.ArrayList;
public class Buyer implements Agent {
    ArrayList<Buyer> friends;
    ArrayList<Market> knownMarkets;
    int [][] endorsmentList;
    public Buyer(int [][] endorsmentList){
        this.endorsmentList=endorsmentList;
    }
    //maybe this won't be needed here. does a buyer add friends after the initialization?
    public void addFriend(Buyer newFriend) {
        friends.add(newFriend);
    }
    public void addKnownMarket(Market newMarket){
        knownMarkets.add(newMarket);
    }
    public void action(){
        System.out.println("Inside buyer's action");
    }

    /*
     Use this to test the correct assignation of a buyer's endorsments - Brian
    public void displayEndorsments(){
        for(int i=0;i<endorsmentList.length;i++){
            System.out.print(EndorsmentList.getEndorsment(endorsmentList[i][0]));
            System.out.print(" ");
            System.out.print(endorsmentList[i][1]);
            System.out.println();
        }
    }
     */
}
