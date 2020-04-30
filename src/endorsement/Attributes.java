package endorsement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class Attributes {
    Logger logger = LogManager.getRootLogger();

    protected final ArrayList<String> names;
    protected final ArrayList<Double[]> values;

    public Attributes(ArrayList<String> names, ArrayList<Double[]> values) {
        this.names = new ArrayList<>(names);
        this.values = new ArrayList<>(values);
    }

    public int size () {
        return names.size();
    }

    public Double[] getValues(int i) {
        return values.get(i);
    }

    public Double[] getValues(String name) {return getValues(getIndex(name));}

    public String getName(int i) {
        return this.names.get(i);
    }

    public int getIndex(String name) {
        int index = -1;
        for (int i = 0; i < names.size(); ++i) {
            if (names.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }
        
        logger.assertLog(index != -1, "Attributes: " + name + " not found");
        return index;
    }

    @Override
    public String toString() {
        StringBuilder valueString = new StringBuilder();
        for(int i = 0; i < values.size(); ++i) {
            Double[] oneValues = values.get(i);
            String name = names.get(i);
            valueString.append(name).append(" [").append(Arrays.toString(oneValues)).append("], ");
        }

        return "Attributes{" +
                 valueString +
                '}';
    }
}
