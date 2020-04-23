package InputManager;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;

public class InnerMarket {
    public String name;
    public ArrayList<String> attributeNames;
    public ArrayList<Double[]> attributeValues;

    InnerMarket(String name) {
        this.name = name;
        attributeNames = new ArrayList<>();
        attributeValues = new ArrayList<>();
    }

    void addAttribute(String name, Double[] values) {
        attributeNames.add(name);
        attributeValues.add(values);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder(name);

        for (int i = 0; i < attributeNames.size(); ++i) {
            String result = ArrayUtils.toString(attributeValues.get(i));
            text.append("{").append(attributeNames.get(i)).append(":").append(result).append("}");
        }

        return text.toString();
    }
}