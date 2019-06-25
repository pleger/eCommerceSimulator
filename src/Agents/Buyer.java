package Agents;

//PL:base debería ser constantes!
//PL:knownMarket -> market!!

import java.util.ArrayList;
public class Buyer implements Agent {
    ArrayList<Buyer> friends;
    ArrayList<Market> knownMarkets;
    double base;
    int [][] endorsmentList;
    public Buyer(int [][] endorsmentList,double base){
        
        this.base=base;
        //PL: por que no se realiza una copia!
        this.endorsmentList=endorsmentList;
    }
    //maybe this won't be needed here. does a buyer add friends after the initialization?
    public void addFriend(Buyer newFriend) {
        friends.add(newFriend);
    }
    public void addKnownMarket(Market newMarket){
        knownMarkets.add(newMarket);

    }
    private int getEndorsmentValue(int endorsment){
        for(int i=0;i<endorsmentList.length;i++){
            if(endorsmentList[i][0]==endorsment){
                return endorsmentList[i][1];
            }
        }
        throw new IllegalArgumentException ("No existe el endoso");
    }
    private boolean existsEndorsment(int endorsment){
        for(int i=0;i<endorsmentList.length;i++){
            if(endorsmentList[i][0]==endorsment){
                return true;
            }
        }
        return false;
    }
    public double calculateFormula(ArrayList<Integer>experience){
        double positiveValues=0;
        double negativeValues=0;
        for(int endorsmentIndex:experience){
            if(existsEndorsment(endorsmentIndex)){
                int endorsmentValue=getEndorsmentValue(endorsmentIndex);
                if(endorsmentValue>=0){
                    positiveValues+=Math.pow(base,endorsmentValue);
                }else{
                    negativeValues+=Math.pow(base,Math.abs(endorsmentValue));
                }
            }
        }
        return (positiveValues-negativeValues);
    }
    public void action(){
        System.out.println("Inside buyer's action");
    }

     //Use this to test the correct assignation of a buyer's endorsments - Brian
    //PL: toString!
    public void displayEndorsments(){
        for(int i=0;i<endorsmentList.length;i++){
            System.out.print(EndorsmentList.getEndorsment(endorsmentList[i][0]));
            System.out.print(" ");
            System.out.print(endorsmentList[i][1]);
            System.out.println();
        }
    }
}