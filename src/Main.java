import Agents.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Probando....");
        double [] probabilities={0.3,0.7};
        ArrayList<Buyer> buyersInNetwork= BuyerFactory.getBuyerList(10,probabilities);
        ArrayList<Market> marketsInNetwork= MarketFactory.getMarketList();

        System.out.println("Experiencia generada: ");
        ArrayList<Integer> experienceMarket=marketsInNetwork.get(0).generateExperience();
        for(int integerIndex:experienceMarket){
            System.out.println(EndorsmentList.getEndorsment(integerIndex));
        }
        System.out.println();
        System.out.println("Endosos del comprador: ");
        buyersInNetwork.get(0).displayEndorsments();

        System.out.println();
        System.out.println("Evaluacion comprador: ");
        System.out.println(buyersInNetwork.get(0).calculateFormula(experienceMarket));

    }
}
