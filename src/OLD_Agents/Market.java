package OLD_Agents;

import java.util.ArrayList;
import java.util.Random;

public class Market implements Agent {
    private double[][] endorsementProbabilityList;//refer to marketFactory for details on this list
    String name;

    public Market(double[][] endorsementProbabilityList, String name) {
        this.endorsementProbabilityList = endorsementProbabilityList;
        this.name = name;
    }

    public ArrayList<Integer> generateExperience() {
        ArrayList<Integer> experience = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < endorsementProbabilityList.length; i++) {
            double probabilityNumber = random.nextDouble();
            double positiveProb = endorsementProbabilityList[i][1];
            double negativeProb = positiveProb + endorsementProbabilityList[i][3];
            if (probabilityNumber <= positiveProb) {
                //adds to a "positive" experience
                experience.add((int) endorsementProbabilityList[i][0]);
            } else if (probabilityNumber <= negativeProb) {
                //"negative" experience
                experience.add((int) endorsementProbabilityList[i][2]);
            }//note that in any other case, the attribute will not be added to the experience
        }
        return experience;
    }

    public ArrayList<ArrayList<String>> action() {
        System.out.println("Inside market's action");
        return null;
    }

    public int getNumber() {
        return MarketFactory.getMarketNumber(this.name);
    }

    @Override
    public String toString() {
        String state = "";
        state += this.name;
        state += "\n";
        for (int i = 0; i < endorsementProbabilityList.length; i++) {
            state += EndorsementList.getEndorsement((int) endorsementProbabilityList[i][0]);
            state += "\n";
            state += Double.toString(endorsementProbabilityList[i][1]);
            state += "\n";
        }
        return state;
    }

}
