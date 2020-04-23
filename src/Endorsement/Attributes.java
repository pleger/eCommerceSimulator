package Endorsement;

import java.util.ArrayList;

public class Attributes {
    protected final ArrayList<String> names;
    protected final ArrayList<Double[]> values;

    public Attributes(ArrayList<String> names, ArrayList<Double[]> values) {
        //todo: is it necessary to clone this?
        this.names = new ArrayList<>(names);
        this.values = new ArrayList<>(values);
    }

    public int size () {
        return names.size();
    }

    public Double[] getValues(int i) {
        return values.get(i);
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
        return index;
    }

    @Override
    public String toString() {
        return "Endorsements{" +
                "values=" + values +
                ", names=" + names +
                '}';
    }
}
