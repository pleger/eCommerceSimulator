import Agents.Buyer;
import Agents.BuyerFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Probando....");
        double [] probabilities={0.3,0.7};
        ArrayList<Buyer> buyersInNetwork= BuyerFactory.getBuyers(10,probabilities);

        //Buyer buyer1= BuyerFactory.getBuyer(1);
        //Buyer buyer2= BuyerFactory.getBuyer(2);
    }
}
