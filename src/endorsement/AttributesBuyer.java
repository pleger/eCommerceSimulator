package endorsement;

import java.util.ArrayList;

public class AttributesBuyer extends Attributes {
    public AttributesBuyer(ArrayList<String> names, ArrayList<Double[]> values) {
        super(names, values);
    }

    public double getValue(int i) {
        return values.get(i)[0];
    }
}
