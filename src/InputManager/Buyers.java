package InputManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Buyers {
    private final static ArrayList<InnerBuyer> INNER_BUYERS = new ArrayList<InnerBuyer>();

    public static void set(HashMap<String, Double> data) {
        InnerBuyer oneKindConsumer = new InnerBuyer();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            String attributeName = entry.getKey();
            double value = entry.getValue();
            oneKindConsumer.addAttribute(attributeName, value);
        }
        INNER_BUYERS.add(oneKindConsumer);
    }

    public static int attributeSize() {
        return getOneConsumer().attributeValues.size();
    }

    public static ArrayList<InnerBuyer> getConsumers() {
        return INNER_BUYERS;
    }

    public static InnerBuyer getOneConsumer() {
        return getConsumers().get(0);
    }

    public static String toStringBuyers() {
        String text = "";
        for (InnerBuyer buyer: INNER_BUYERS) {
            text += buyer + "\n";
        }
        return text;
    }
}

