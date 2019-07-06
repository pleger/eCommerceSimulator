package Agents;

//PL:knownMarket -> market!!

import java.util.ArrayList;

public class Buyer implements Agent {
    ArrayList<Buyer> friends;
    ArrayList<Market> knownMarkets;
    final String type;
    final double base;
    int[][] endorsementList;
    int iterationTime;

    public Buyer(int[][] endorsmentList, double base,String type) {
        this.base = base;
        //PL: por que no se realiza una copia!
        this.endorsementList = endorsmentList;
        this.type=type;
        friends=new ArrayList<>();
        knownMarkets=new ArrayList<>();
        iterationTime=0;
    }

    //maybe this won't be needed here. does a buyer add friends after the initialization?
    public void addFriend(Buyer newFriend) {
        friends.add(newFriend);
    }

    public void addKnownMarket(Market newMarket) {
        knownMarkets.add(newMarket);

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
        for (int endorsmentIndex : experience) {
            if (existsEndorsement(endorsmentIndex)) {
                int endorsmentValue = getEndorsementValue(endorsmentIndex);
                if (endorsmentValue >= 0) {
                    positiveValues += Math.pow(base, endorsmentValue);
                } else {
                    negativeValues += Math.pow(base, Math.abs(endorsmentValue));
                }
            }
        }
        return (positiveValues - negativeValues);
    }

    public ArrayList<ArrayList<String>> action() {
        ArrayList<ArrayList<String>>experiences=new ArrayList<>();
        double endorsementWeight;
        for (Market market : knownMarkets) {
            ArrayList<Integer> generatedExperience=market.generateExperience();
            endorsementWeight = this.calculateWeight(generatedExperience);
            ArrayList<String>experience=new ArrayList<>();
            experience.add(Integer.toString(this.iterationTime));
            experience.add(this.type);
            for(int i=0;i<endorsementList.length;i++){
                experience.add(EndorsementList.getEndorsement(endorsementList[i][0]));
                experience.add(Integer.toString(endorsementList[i][1]));
            }
            experience.add(market.name);
            for(int marketEndorsement:generatedExperience){
                experience.add(EndorsementList.getEndorsement(marketEndorsement));
            }
            experience.add(Double.toString(endorsementWeight));
            experiences.add(experience);
        }
        iterationTime++;
        return experiences;
    }
    //Use this to test the correct assignation of a buyer's endorsments - Brian
    @Override
    public String toString() {
        String state = "";
        for (int i = 0; i < endorsementList.length; i++) {
            state += EndorsementList.getEndorsement((endorsementList[i][0]));
            state += " ";
            state += endorsementList[i][1];
            state += "\n";
        }
        return state;
    }
}
