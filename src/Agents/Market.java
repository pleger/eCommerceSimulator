package Agents;

import java.util.ArrayList;
import java.util.Random;

public class Market implements Agent {
    private double[][] endorsmentProbabilityList;//refer to marketFactory for details on this list
    String name;

    public Market(double[][] endorsmentProbabilityList, String name) {
        this.endorsmentProbabilityList = endorsmentProbabilityList;
        this.name = name;
    }

    public ArrayList<Integer> generateExperience() {
        ArrayList<Integer> experience = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < endorsmentProbabilityList.length; i++) {
            double probabilityNumber = random.nextDouble();
            if (probabilityNumber <= endorsmentProbabilityList[i][1]) {
                //adds to a "positive" experience
                experience.add((int) endorsmentProbabilityList[i][0]);
            } else {
                //"negative" experience
                experience.add((int) endorsmentProbabilityList[i][2]);
            }
        }
        return experience;
    }

    public ArrayList<ArrayList<String>> action() {
        System.out.println("Inside market's action");
        return null;
    }

    @Override
    public String toString() {
        String state = "";
        state += this.name;
        state += "\n";
        for (int i = 0; i < endorsmentProbabilityList.length; i++) {
            state += EndorsementList.getEndorsement((int) endorsmentProbabilityList[i][0]);
            state += "\n";
            state += Double.toString(endorsmentProbabilityList[i][1]);
            state += "\n";
        }
        return state;
    }

}
