package Agents;

public class Market implements Agent {
    double [][] endorsmentProbabilityList;
    public Market(double [][] endorsmentProbabilityList){
        this.endorsmentProbabilityList=endorsmentProbabilityList;
    }
    public void action(){
        System.out.println("Inside market's action");
    }
}
