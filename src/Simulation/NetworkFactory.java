package Simulation;

import Agents.Buyer;
import Agents.Market;
import Agents.MarketFactory;
import Agents.BuyerFactory;

import java.util.ArrayList;
import java.util.Random;

public class NetworkFactory {

    public static final int NETWORK_TYPE_1 = 1;

    private static ArrayList<Buyer> randomBuyerFriendList(ArrayList<Buyer> buyerList, int buyerID, int numberOfFriends,
                                                          Random randomGenerator, int buyerQuantity) {
        ArrayList<Buyer> friendList = new ArrayList<>();
        ArrayList<Integer> friendID = new ArrayList<>();
        int nextFriendId;
        for (int i = 0; i < numberOfFriends; i++) {
            nextFriendId = randomGenerator.nextInt(buyerQuantity);
            //makes sure a buyer doesn't have itself in it's friend list or a friend is already on list
            while (nextFriendId == buyerID || friendID.contains(nextFriendId)) {
                nextFriendId = randomGenerator.nextInt(buyerQuantity);
            }
            friendID.add(nextFriendId);
            friendList.add(buyerList.get(nextFriendId));
        }
        return friendList;
    }

    private static ArrayList<Market> randomMarketList(ArrayList<Market> marketList, Random randomGenerator,
                                                      int maxNumberOfMarkets) {
        ArrayList<Market> buyerMarketList = new ArrayList<>();
        ArrayList<Integer> marketIds = new ArrayList<>();

        //determines a random number of markets to be added to the list
        int numberOfMarkets = randomGenerator.nextInt(maxNumberOfMarkets + 1);//workaround to exclusive upper bound
        while (numberOfMarkets == 0) {
            numberOfMarkets = randomGenerator.nextInt();
        }

        int nextMarketId;
        for (int i = 0; i < numberOfMarkets; i++) {
            nextMarketId = randomGenerator.nextInt(maxNumberOfMarkets);
            while (marketIds.contains(nextMarketId)) {
                nextMarketId = randomGenerator.nextInt(maxNumberOfMarkets);
            }
            buyerMarketList.add(marketList.get(nextMarketId));
            marketIds.add(nextMarketId);
        }

        return buyerMarketList;
    }

    public static Network getNetwork(int networkType, double[] buyerProbability) {
        ArrayList<Market> marketList = MarketFactory.getMarketList();
        ArrayList<Buyer> buyerList;
        int numberOfFriends;
        int buyerQuantity;
        int maxNumberOfMarkets;

        //fixed number of friends,random friends; random number of markets
        if (networkType == NETWORK_TYPE_1) {
            numberOfFriends = 5;
            buyerQuantity = 30;
            maxNumberOfMarkets = 3;

            Random randomGenerator = new Random();
            buyerList = BuyerFactory.getBuyerList(buyerQuantity, buyerProbability);
            ArrayList<Buyer> friendList;
            ArrayList<Market> knownMarketsList;

            int buyerID = 0;
            //populates every buyer with required parameters
            for (Buyer buyer : buyerList) {
                //generates and adds friends
                friendList = randomBuyerFriendList(buyerList, buyerID, numberOfFriends, randomGenerator, buyerQuantity);
                for (Buyer friend : friendList) {
                    buyer.addFriend(friend);
                }

                //generates and adds markets
                knownMarketsList = randomMarketList(marketList, randomGenerator, maxNumberOfMarkets);
                for (Market knownMarket : knownMarketsList) {
                    buyer.addKnownMarket(knownMarket);
                }
                buyerID++;
            }
            return new Network(marketList, buyerList);
        }
        return null;
    }
}
