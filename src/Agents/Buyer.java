package Agents;

//PL:knownMarket -> market!!

import java.util.ArrayList;
import GUI.XChartDriver;

public class Buyer implements Agent {
    private ArrayList<Buyer> friends;
    private ArrayList<Market> knownMarkets;
    private ArrayList<Interaction> interactions;
    private final String type;
    private final double base;
    private int[][] endorsementList;
    private int iterationTime;
    private int buyerId;
    private int previousPreferredMarket;



    public Buyer(int[][] endorsmentList, double base, String type) {
        this.base = base;
        this.endorsementList = endorsmentList;
        this.type = type;
        friends = new ArrayList<>();
        knownMarkets = new ArrayList<>();
        interactions = new ArrayList<>();
        iterationTime = 0;
    }
    public void setBuyerId(int buyerId){
        this.buyerId=buyerId;
    }
    public int getBuyerId(){
        return this.buyerId;
    }
    public void addFriend(Buyer newFriend) {
        friends.add(newFriend);
    }

    public void addKnownMarket(Market newMarket) {
        knownMarkets.add(newMarket);

    }
    public void registerChartSeries(){
        ArrayList<Integer> xData=new ArrayList<>();
        xData.add(-1);
        ArrayList<Integer> yData=new ArrayList<>();
        yData.add(-1);
        XChartDriver.registerNewSeries(buyerId,Integer.toString(this.buyerId),xData,yData);
    }
    private int getEndorsementValue(int endorsement) {
        for (int i = 0; i < endorsementList.length; i++) {
            if (endorsementList[i][0] == endorsement) {
                return endorsementList[i][1];
            }
        }
        throw new IllegalArgumentException("No existe el endoso");
    }

    private boolean existsEndorsement(int endorsement) {
        for (int i = 0; i < endorsementList.length; i++) {
            if (endorsementList[i][0] == endorsement) {
                return true;
            }
        }
        return false;
    }

    public double calculateWeight(ArrayList<Integer> experience) {
        double positiveValues = 0;
        double negativeValues = 0;
        for (int endorsementIndex : experience) {
            if (existsEndorsement(endorsementIndex)) {
                int endorsementValue = getEndorsementValue(endorsementIndex);
                if (endorsementValue >= 0) {
                    positiveValues += Math.pow(base, endorsementValue);
                } else {
                    negativeValues += Math.pow(base, Math.abs(endorsementValue));
                }
            }
        }
        return (positiveValues - negativeValues);
    }

    public ArrayList<ArrayList<String>> action() {
        ArrayList<ArrayList<String>> experiences = new ArrayList<>();
        double endorsementWeight;
        double[]individualEndorsements=new double[knownMarkets.size()];
        int individualEndorsementsIndex=0;
        double endorsementsSum=0;
        for (Market market : knownMarkets) {
            //creates a new experience to contain all the data created in one market-buyer interaction
            ArrayList<String> experience = new ArrayList<>();
            ArrayList<Integer> generatedExperience = market.generateExperience();
            endorsementWeight = this.calculateWeight(generatedExperience);
            experience.add(Integer.toString(this.iterationTime));
            experience.add(this.type);
            experience.add(market.name);
            for (int marketEndorsement : generatedExperience) {
                experience.add(EndorsementList.getEndorsement(marketEndorsement));
            }
            if(endorsementWeight>0) {
                endorsementsSum = endorsementsSum + endorsementWeight;
                individualEndorsements[individualEndorsementsIndex]=endorsementWeight;
            }else{
                individualEndorsements[individualEndorsementsIndex]=0;
            }
            individualEndorsementsIndex++;
            experience.add(Double.toString(endorsementWeight));
            //registers a new interaction
            interactions.add(new Interaction(market, generatedExperience,endorsementWeight, iterationTime));
            experiences.add(experience);
        }

        //now for the probability
        individualEndorsementsIndex=0;
        int interactionsIndex=interactions.size()-knownMarkets.size();
        double preferredMarketProbability=-1;
        int preferredMarket=-1;
        for(ArrayList<String> experience: experiences){
            double probability;
            if(endorsementsSum>0){
                probability=individualEndorsements[individualEndorsementsIndex]/endorsementsSum;
                if(probability>preferredMarketProbability) {
                    preferredMarketProbability=probability;
                    preferredMarket=MarketFactory.getMarketNumber(experience.get(2));
                }
            }
            else{
                probability=0;
            }
            experience.add(Double.toString(probability));
            interactions.get(interactionsIndex).setProbability(probability);
            individualEndorsementsIndex++;
            interactionsIndex++;
        }
        if(endorsementsSum==0) preferredMarket=previousPreferredMarket; //ATENCIÓN!!! si no hay nueva eleccion, elige lo anterior
        XChartDriver.addSeriesData(this.buyerId,iterationTime,preferredMarket);
        previousPreferredMarket=preferredMarket;
        iterationTime++;
        return experiences;
    }

    //Use this to test the correct assignation of a buyer's endorsments - Brian
    @Override
    public String toString() {
        String state = "";
        state += this.type;
        state += "\n";
        for (int i = 0; i < endorsementList.length; i++) {
            state += EndorsementList.getEndorsement((endorsementList[i][0]));
            state += " ";
            state += endorsementList[i][1];
            state += "\n";
        }
        return state;
    }
}
