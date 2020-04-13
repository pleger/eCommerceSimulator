package InputManager;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Markets {
    private final static ArrayList<InnerMarket> innerMarkets = new ArrayList<>();

    public static void set(HashMap<String,ArrayList<Double[]>> data, ArrayList<String> names) {
        for (String name : names) {
            innerMarkets.add(new InnerMarket(name));
        }

        for (Map.Entry<String, ArrayList<Double[]>> entry : data.entrySet()) {
            String endorName = entry.getKey();
            ArrayList<Double[]> values = entry.getValue();

            for (int i = 0; i < values.size(); ++i) {
                if (i > 0) {
                    innerMarkets.get(i - 1).addEndorsement(endorName, values.get(i));
                }
            }
        }
    }

    public ArrayList<InnerMarket> getMarkets() {
        return innerMarkets;
    }

    public static String endorsementName() {
        InnerMarket market = innerMarkets.get(0);
        StringBuilder text = new StringBuilder();
        for (String endorName: market.endorName)  {
            text.append(endorName).append(" ");
        }
        return text.toString();
    }

    public static String marketNames () {
        StringBuilder text = new StringBuilder();
        for (InnerMarket market: innerMarkets)  {
            text.append(market.name).append(" ");
        }
        return text.toString();
    }

    public static String markets() {
        String text = "";
        for (InnerMarket market: innerMarkets) {
            text += market + "\n";
        }
        return text;
    }

}

class InnerMarket {
    public String name;
    public ArrayList<Double[]> endors;
    public ArrayList<String> endorName;

    InnerMarket(String name) {
        this.name = name;
    }

    void addEndorsement(String name, Double[] values) {
        endorName.add(name);
        endors.add(values);
    }

    @Override
    public String toString() {
        String text = name;

        for (int i = 0; i < endorName.size(); ++i) {
            String result = ArrayUtils.toString(endors.get(0));
            text += "{" + endorName.get(i) + ":" + result + "}";
        }

        return text;
    }
}
