package OLD_Simulation;

import OLD_Agents.Buyer;
import OLD_Agents.Market;
import OLD_Agents.MarketFactory;
import OLD_Agents.BuyerFactory;

import java.util.ArrayList;
import java.util.Random;

public class NetworkFactory {

    public static final int CUSTOM_NETWORK_TYPE = 1;
    public static final int TEST_TYPE = 2;

    private static ArrayList<Buyer> randomBuyerFriendList(ArrayList<Buyer> buyerList, int buyerID, int numberOfFriends,
                                                          Random randomGenerator, int buyerQuantity) {
        ArrayList<Buyer> friendList = new ArrayList<>();
        ArrayList<Integer> friendID = new ArrayList<>();
        int nextFriendId;
        for (int i = 0; i < numberOfFriends; i++) {
            nextFriendId = randomGenerator.nextInt(buyerQuantity);
            //makes sure a buyer doesn't have itself in it's friend list or a friend is already on list
            while (nextFriendId < 0 || nextFriendId == buyerID || friendID.contains(nextFriendId)) {
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

        randomGenerator = new Random();
        //determines a random number of markets to be added to the list
        int randomNumberOfMarketsBound = maxNumberOfMarkets + 1;
        int numberOfMarkets = randomGenerator.nextInt(randomNumberOfMarketsBound);//workaround to exclusive upper bound
        while (numberOfMarkets <= 0) {
            numberOfMarkets = randomGenerator.nextInt(randomNumberOfMarketsBound);
        }

        int nextMarketId;
        for (int i = 0; i < numberOfMarkets; i++) {
            nextMarketId = randomGenerator.nextInt(maxNumberOfMarkets);
            while (nextMarketId < 0 || marketIds.contains(nextMarketId)) {
                nextMarketId = randomGenerator.nextInt(maxNumberOfMarkets);
            }
            buyerMarketList.add(marketList.get(nextMarketId));
            marketIds.add(nextMarketId);
        }

        return buyerMarketList;
    }

    public static Network getNetwork(int networkType) {
        ArrayList<Market> marketList = MarketFactory.getMarketList();
        ArrayList<Buyer> buyerList;

        System.out.println("Creando Network...");
        //fixed number of friends,random friends; random number of markets
        if (networkType == CUSTOM_NETWORK_TYPE) {
            int numberOfFriends = 0; //not implemented yet
            int buyerQuantity = SimulationMain.BUYER_QUANTITY_NETWORK;
            int maxNumberOfMarkets = SimulationMain.MAX_NUMBER_OF_MARKETS;
            double[] buyerProbability = SimulationMain.BUYER_PROBABILITIES;

            Random randomGenerator = new Random();
            buyerList = BuyerFactory.getBuyerList(buyerQuantity, buyerProbability);
            ArrayList<Buyer> friendList;
            ArrayList<Market> knownMarketsList;

            int buyerID = 0;
            //populates every buyer with required parameters
            for (Buyer buyer : buyerList) {
                //generates and adds friends
                buyer.setBuyerId(buyerID);
                System.out.println("Creando lista de compradores... " + buyerID);
                friendList = randomBuyerFriendList(buyerList, buyerID, numberOfFriends, randomGenerator, buyerQuantity);
                for (Buyer friend : friendList) {
                    buyer.addFriend(friend);
                }
                System.out.println("Creando lista de mercados... " + buyerID);
                //generates and adds markets
                knownMarketsList = randomMarketList(marketList, randomGenerator, maxNumberOfMarkets);
                for (Market knownMarket : knownMarketsList) {
                    buyer.addKnownMarket(knownMarket);
                }
                buyerID++;
            }
            Network network = new Network(marketList, buyerList);
            System.out.println("Network creada");
            return network;
        } else if (networkType == TEST_TYPE) {
            buyerList = BuyerFactory.dGetBuyer(BuyerFactory.TYPE1);
            Buyer debugBuyer = buyerList.get(0);
            for (Market knownMarket : marketList) {
                debugBuyer.addKnownMarket(knownMarket);
            }
            Network network = new Network(marketList, buyerList);
            System.out.println("Network creada");
            return network;
        }
        return null;
    }
}
