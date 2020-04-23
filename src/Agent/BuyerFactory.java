package Agent;

import InputManager.Configuration;
import InputManager.InnerBuyer;
import InputManager.Buyers;

import java.util.ArrayList;
import java.util.List;

public class BuyerFactory {

    public static List<Buyer> createFromInput() {
        ArrayList<Buyer> buyers = new ArrayList<>();
        InnerBuyer innerBuyer =  Buyers.getOneConsumer();

        for (int i = 0; i < Configuration.AGENTS; i++) {
            buyers.add(new Buyer(innerBuyer));
        }
        return buyers;
    }
}
