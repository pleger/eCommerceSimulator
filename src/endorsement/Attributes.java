package endorsement;

import logger.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class Attributes {
    protected final ArrayList<String> names;
    protected final ArrayList<Double[]> values;

    public Attributes(ArrayList<String> names, ArrayList<Double[]> values) {
        this.names = new ArrayList<>(names);
        this.values = new ArrayList<>(values);
    }

    public int size() {
        return names.size();
    }

    public Double[] getValues(int i) {
        return values.get(i);
    }

    public Double[] getValues(String name) {
        return getValues(getIndex(name));
    }

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

        Console.setAssert(index != -1, "Attributes: " + name + " not found");
        return index;
    }

    public void forEach(BiConsumer<String, Double[]> fun) {
        for (int i = 0; i < names.size(); ++i) {
            String name = names.get(i);
            Double[] values = this.values.get(i);
            fun.accept(name, values);
        }
    }

    public boolean contains(String[] names) {
        for (String name: names) {
            boolean isNot = false;
            if (!this.names.contains(name)) {
                Console.error(name + " is not found");
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder valueString = new StringBuilder();
        for (int i = 0; i < values.size(); ++i) {
            Double[] oneValues = values.get(i);
            String name = names.get(i);
            valueString.append(name).append(" [").append(Arrays.toString(oneValues)).append("], ");
        }

        return "Attributes{" +
                valueString +
                '}';
    }
}
