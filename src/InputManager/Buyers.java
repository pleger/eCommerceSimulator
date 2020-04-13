package InputManager;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Buyers {
    private final static ArrayList<InnerBuyer> INNER_BUYERS = new ArrayList<InnerBuyer>();

    public static void set(HashMap<String, ArrayList<Double[]>> data) {
        InnerBuyer oneKindConsumer = new InnerBuyer();

        for (Map.Entry<String, ArrayList<Double[]>> entry : data.entrySet()) {
            String endorName = entry.getKey();
            ArrayList<Double[]> values = entry.getValue();
            oneKindConsumer.addEndorsement(endorName, values.get(0));
        }
        INNER_BUYERS.add(oneKindConsumer);
    }

    public ArrayList<InnerBuyer> getInnerConsumers() {
        return INNER_BUYERS;
    }

    public InnerBuyer getOneConsumer() {
        return getInnerConsumers().get(0);
    }

    public static String consumers() {
        String text = "";
        for (InnerBuyer consumer: INNER_BUYERS) {
            text += consumer + "\n";
        }
        return text;
    }
}

class InnerBuyer {
    public ArrayList<String> endorName;
    public ArrayList<Double[]> endors;

    public void addEndorsement(String name, Double[] values) {
        endorName.add(name);
        endors.add(values);
    }

    @Override
    public String toString() {
        String text = "Consumer";

        for (int i = 0; i < endorName.size(); ++i) {
            String result = ArrayUtils.toString(endors.get(0));
            text += "{" + endorName.get(i) + ":" + result + "}";
        }

        return text;
    }
}