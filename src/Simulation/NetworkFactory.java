package Simulation;
import Agents.Buyer;
import Agents.Market;
import Agents.MarketFactory;
import Agents.BuyerFactory;
import java.util.ArrayList;
import java.util.Random;

public class NetworkFactory {
    private static ArrayList<Buyer> randomBuyerFriendList(ArrayList<Buyer> buyerList,int buyerID, int numberOfFriends,
                                                          Random randomGenerator, int buyerQuantity){
        ArrayList<Buyer> friendList = new ArrayList<>();
        ArrayList<Integer> friendID = new ArrayList<>();
        int nextFriendId;
        for(int i=0;i<numberOfFriends;i++){
            nextFriendId= randomGenerator.nextInt(buyerQuantity);
            //makes sure a buyer doesn't have itself in it's friend list or a friend is already on list
            while(nextFriendId==buyerID || friendID.contains(nextFriendId)){
                nextFriendId=randomGenerator.nextInt(buyerQuantity);
            }
            friendID.add(nextFriendId);
            friendList.add(buyerList.get(nextFriendId));
        }
        return friendList;
    }

    public static Network getNetwork(int networkType,double[]buyerProbability){
        ArrayList<Market> marketList=MarketFactory.getMarketList();
        ArrayList<Buyer> buyerList;
        int numberOfFriends;
        int buyerQuantity;
        int maxNumberOfMarkets;

        //fixed number of friends,random friends; random number of markets
        if(networkType==1){
            numberOfFriends=5;
            buyerQuantity=30;
            maxNumberOfMarkets=3;

            Random randomGenerator=new Random();
            buyerList=BuyerFactory.getBuyerList(buyerQuantity,buyerProbability);
            ArrayList<Buyer> friendList;

            int buyerID=0;
            //populates every buyer with required parameters
            for(Buyer buyer:buyerList){
                //generates and adds friends
                friendList=randomBuyerFriendList(buyerList,buyerID,numberOfFriends,randomGenerator,buyerQuantity);
                for(Buyer friend:friendList){
                    buyer.addFriend(friend);
                }
                //TODO:generate initial known markets
                buyerID++;
            }

            return new Network(marketList,buyerList);
        }
        return null;
    }
}
