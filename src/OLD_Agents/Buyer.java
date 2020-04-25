package OLD_Agents;

//PL:knownMarket -> market!!

import java.util.ArrayList;
import java.util.Random;

import GUI.InformationPanel;
import GUI.XChartDriver;

/*
public class Buyer implements Agent {
    private ArrayList<Buyer> friends;
    private ArrayList<Market> knownMarkets;
    private ArrayList<Double> marketProbabilities;
    private Market chosenMarket;
    private boolean isInitialized;//used to create elections of markets
    private InteractionList interactions;
    private final String type;
    private final double base;
    private int[][] endorsementList;
    private int iterationTime;
    private int buyerId;

    public Buyer(int[][] endorsementList, double base, String type) {
        this.base = base;
        this.endorsementList = endorsementList;
        this.type = type;
        friends = new ArrayList<>();
        knownMarkets = new ArrayList<>();
        interactions = new InteractionList();
        iterationTime = 0;
        isInitialized = false;
        marketProbabilities = new ArrayList<>();
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getBuyerId() {
        return this.buyerId;
    }

    public void addFriend(Buyer newFriend) {
        friends.add(newFriend);
    }

    public void addKnownMarket(Market newMarket) {
        knownMarkets.add(newMarket);
    }

    public void registerChartSeries() {
        ArrayList<Integer> xData = new ArrayList<>();
        xData.add(-1);
        ArrayList<Integer> yData = new ArrayList<>();
        yData.add(-1);
        XChartDriver.registerNewSeries(buyerId, Integer.toString(this.buyerId), xData, yData);
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

    private double calculateWeight(ArrayList<Integer> experience) {
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
    private Market chooseMarketByHighest(){
        int cantProb=marketProbabilities.size();
        double max=-1;
        int maxIndex=0;
        for(int i=0;i<cantProb;i++){
            if(marketProbabilities.get(i)>max){
                max=marketProbabilities.get(i);
                maxIndex=i;
            }
        }
        chosenMarket=knownMarkets.get(maxIndex);
        System.out.println(" Chosen Market: " + chosenMarket.getNumber());
        return chosenMarket;
    }

    private Market chooseMarketByProbability() {
        //creates intervals
        int cantProb = marketProbabilities.size();
        double[] intervals = new double[cantProb];
        intervals[0] = marketProbabilities.get(0);
        for (int i = 1; i < cantProb; i++) {
            intervals[i] = marketProbabilities.get(i) + intervals[i - 1];
        }
        Random randomNum = new Random();
        double electedNum = randomNum.nextDouble();

        //election of a market
        Market chosenMarket = knownMarkets.get(knownMarkets.size() - 1);//superior limit can be 0.999999
        for (int i = 0; i < cantProb; i++) {
            if (electedNum <= intervals[i]) {
                chosenMarket = knownMarkets.get(i);
                break;
            }
        }
        System.out.println("ElectedNum: " + electedNum + " Chosen Market: " + chosenMarket.getNumber());
        return chosenMarket;
    }

    private ArrayList<Double> calculateEveryWeight(){
        ArrayList<Double> weights=new ArrayList<>();
        for (Market market : knownMarkets) {
            ArrayList<Integer> allAttributes = interactions.getKnownMarketAttributes(market.getNumber());
            System.out.print("NonInitial");
            System.out.print(" / " + market.getNumber());
            System.out.print(" / " + iterationTime);
            for (int attribute : allAttributes) {
                System.out.print(" / " + EndorsementList.getEndorsement(attribute));

            }
            System.out.println("/");
            double weight = calculateWeight(allAttributes);
            weights.add(weight);
        }
        return weights;
    }
    private ArrayList<Double> calculateProbabilities(ArrayList<Double> weights) {
        ArrayList<Double> individualProbabilities = new ArrayList<>();
        double sumWeight = 0;
        for (double weight:weights) {
            if (weight >= 0) {
                sumWeight += weight;
                individualProbabilities.add(weight);
            } else {
                individualProbabilities.add(0.0);
            }
        }
        int probIndex = 0;
        for (double weight : individualProbabilities) {
            double prob = weight / sumWeight;
            individualProbabilities.set(probIndex, prob);
            probIndex++;
        }

        return individualProbabilities;
    }
    /**
     * Creates "prejudices" about all the markets, with the intention of creating initial probabilities
     *
     * @return experiences ArrayList<ArrayList<String>> to be recorded in logger
     */
    private ArrayList<ArrayList<String>> initialAction() {
        ArrayList<ArrayList<String>> experiences = new ArrayList<>();
        double probabilitySum = 0;
        ArrayList<Double> initialProbabilities = new ArrayList<>();
        ArrayList<Interaction> initialInteractions = new ArrayList<>();
        for (Market market : knownMarkets) {
            ArrayList<String> experience = new ArrayList<>();
            ArrayList<Integer> generatedExperience = market.generateExperience();
            System.out.print("Initial");
            System.out.print(" / " + market.getNumber());
            System.out.print(" / " + iterationTime);
            for (int attribute : generatedExperience) {
                System.out.print(" / " + EndorsementList.getEndorsement(attribute));
            }
            System.out.println("/");
            double experienceWeight = this.calculateWeight(generatedExperience);
            experience.add(Integer.toString(this.iterationTime));
            experience.add(this.type);
            experience.add(market.name);
            experience.add(Double.toString(experienceWeight));
            if (experienceWeight < 0) {
                initialProbabilities.add(0.0);
            } else {
                initialProbabilities.add(experienceWeight);
                probabilitySum += experienceWeight;
            }
            initialInteractions.add(new Interaction(market.getNumber(), generatedExperience, iterationTime));
            experiences.add(experience);
        }
        if (probabilitySum != 0 && !initialProbabilities.contains(0.0)) {//if the action is valid->returns valid probabilities to choose after
            int probabilityIndex = 0;

            for (Market market : knownMarkets) {
                double probability = initialProbabilities.get(probabilityIndex) / probabilitySum;
                initialProbabilities.set(probabilityIndex, probability);
                experiences.get(probabilityIndex).add(Double.toString(probability));//uses index because it's one experience per market
                experiences.get(probabilityIndex).add("INITIAL CHOICE");
                probabilityIndex++;
            }
            //sets the initial values, as they're now valid
            marketProbabilities = initialProbabilities;
            for (Interaction interaction : initialInteractions) {
                interactions.addInteraction(interaction);
            }
            //chooses a market
            //chosenMarket = chooseMarketByProbability();
            isInitialized = true;
        } else {
            int probabilityIndex = 0;
            for (Market market : knownMarkets) {
                experiences.get(probabilityIndex).add("NO PROB");
                experiences.get(probabilityIndex).add("NO CHOICE");
                probabilityIndex++;
            }
            chosenMarket = null;
        }
        System.out.print("Preferred Market: ");
        System.out.println(chosenMarket != null ? chosenMarket.getNumber() : 0);
        XChartDriver.addSeriesData(this.buyerId, iterationTime, chosenMarket != null ? chosenMarket.getNumber() : 0);
        InformationPanel.addInfo(chosenMarket != null ? chosenMarket.getNumber() : 0, Integer.toString(this.buyerId));
        return experiences;
    }

    /**
     * Chooses a market based on the probabilities, and then recalculates based on the result
     *
     * @return
     */
    private ArrayList<ArrayList<String>> nonInitialAction() {
        ArrayList<ArrayList<String>> experiences = new ArrayList<>();
        //recalculates the probability
        ArrayList<Double> endorsementWeights=calculateEveryWeight();
        ArrayList<Double> individualProbabilities = calculateProbabilities(endorsementWeights);
        if (individualProbabilities != null) {//no probability should be negative
            marketProbabilities = individualProbabilities;
            //election of a market
            chosenMarket = chooseMarketByProbability();
            //chosenMarket=chooseMarketByHighest();
            //buyer buys
            ArrayList<Integer> generatedExperience = chosenMarket.generateExperience();
            interactions.addInteraction(new Interaction(chosenMarket.getNumber(), generatedExperience, this.iterationTime));
        } else {
            interactions.deleteInteraction(chosenMarket.getNumber(), iterationTime - 1);
        }
        int cantProb = marketProbabilities.size();

        for (int i = 0; i < cantProb; i++) {
            ArrayList<String> experience = new ArrayList<>();
            experience.add(Integer.toString(iterationTime));
            experience.add(type);
            experience.add(MarketFactory.getMarketName(knownMarkets.get(i).getNumber()));
            experience.add(Double.toString(endorsementWeights.get(i)));
            experience.add(Double.toString(marketProbabilities.get(i)));
            if(chosenMarket.equals(knownMarkets.get(i))){
                experience.add("CHOSEN");
            }else{
                experience.add("NOT CHOSEN");
            }
            experiences.add(experience);
        }
        //adds experience
        XChartDriver.addSeriesData(this.buyerId, iterationTime, chosenMarket.getNumber());
        InformationPanel.addInfo(chosenMarket.getNumber(), Integer.toString(this.buyerId));
        return experiences;
    }

    public ArrayList<ArrayList<String>> action() {
        ArrayList<ArrayList<String>> experience;
        if (!isInitialized) {
            experience = initialAction();
        } else {
            experience = nonInitialAction();
        }
        iterationTime++;
        return experience;
        /*
        ArrayList<ArrayList<String>> experiences = new ArrayList<>();
        double endorsementWeight;
        double[] individualEndorsements = new double[knownMarkets.size()];
        int individualEndorsementsIndex = 0;
        double endorsementsSum = 0;
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
            if (endorsementWeight > 0) {
                endorsementsSum = endorsementsSum + endorsementWeight;
                individualEndorsements[individualEndorsementsIndex] = endorsementWeight;
            } else {
                individualEndorsements[individualEndorsementsIndex] = 0;
            }
            individualEndorsementsIndex++;
            experience.add(Double.toString(endorsementWeight));
            //registers a new interaction
            interactions.addInteraction(new Interaction(market.getNumber(), generatedExperience, iterationTime));
            experiences.add(experience);
        }
        */
        //now for the probability
        /*
        individualEndorsementsIndex = 0;
        int interactionsIndex = interactions.size() - knownMarkets.size();
        double preferredMarketProbability = -1;
        int preferredMarket = -1;
        for (ArrayList<String> experience : experiences) {
            double probability;
            if (endorsementsSum > 0) {
                probability = individualEndorsements[individualEndorsementsIndex] / endorsementsSum;
                if (probability > preferredMarketProbability) {
                    preferredMarketProbability = probability;
                    preferredMarket = MarketFactory.getMarketNumber(experience.get(2));
                }
            } else {
                probability = 0;
            }
            experience.add(Double.toString(probability));
            interactions.get(interactionsIndex).setProbability(probability);
            individualEndorsementsIndex++;
            interactionsIndex++;
        }
        if (endorsementsSum == 0)
            preferredMarket = previousPreferredMarket; //ATENCIÓN!!! si no hay nueva eleccion, elige lo anterior
        XChartDriver.addSeriesData(this.buyerId, iterationTime, preferredMarket);
        InformationPanel.addInfo(preferredMarket, Integer.toString(this.buyerId));
        previousPreferredMarket = preferredMarket;
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
*/
