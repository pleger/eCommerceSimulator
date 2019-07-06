package Simulation;

import Agents.*;

import java.util.ArrayList;

// PL: formatear el codigo

public class Main {


    public static void main(String[] args) {
        System.out.println("Probando....");

        final int buyerQuantity = 10;
        final double type1Probability = 0.3;
        final double type2Probability = 0.7;

        double[] probabilities = {type1Probability, type2Probability};
        ArrayList<Buyer> buyers = BuyerFactory.getBuyerList(buyerQuantity, probabilities);
        ArrayList<Market> markets = MarketFactory.getMarketList();


        Buyer exampleBuyer = buyers.get(0);
        exampleBuyer.addKnownMarket(markets.get(0));


        //PL:DONDE ESTA LA COMPRA QUE RELACIONA COMPRADOR Y TIENDA
        /*
        System.out.println("Experiencia generada: ");
        ArrayList<Integer> experienceMarket=markets.get(0).generateExperience();
        for(int integerIndex:experienceMarket){
            System.out.println(EndorsmentList.getEndorsment(integerIndex));
        }
        System.out.println();
        System.out.println("Endosos del comprador: ");
        System.out.println(buyers.get(0).toString());

        System.out.println();
        System.out.println("Evaluacion comprador: ");
        System.out.println(buyers.get(0).calculateFormula(experienceMarket));

         */
    }
}
