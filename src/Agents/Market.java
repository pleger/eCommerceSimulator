package Agents;

import java.util.ArrayList;
import java.util.Random;

public class Market implements Agent {
    double[][] endorsmentProbabilityList;//refer to marketFactory for details on this list

    public Market(double[][] endorsmentProbabilityList) {
        this.endorsmentProbabilityList = endorsmentProbabilityList;
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

    public void action() {
        System.out.println("Inside market's action");
    }

}
