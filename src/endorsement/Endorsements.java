package endorsement;

import agent.Market;
import inputManager.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Endorsements {
    private final List<Endorsement> endors;

    public Endorsements() {
        endors = new ArrayList<>();
    }

    public Endorsements(List<Endorsement> endors) {
        this.endors = endors;
    }

    public void add(Endorsement endor) {
        endors.add(endor);
    }

    public void clear() {
        endors.clear();
    }

    public void addAll(Endorsements endors) {
        this.endors.addAll(endors.endors);
    }

    private Endorsements filter(Predicate<Endorsement> filter) {
        return new Endorsements(endors.stream().filter(filter).collect(Collectors.toList()));
    }

    public void forEach(Consumer<Endorsement> fun) {
        endors.iterator().forEachRemaining(fun);
    }

    public Endorsements filterByMemory(int period) {
        return filter(endor -> endor.getPeriod() > period - Configuration.MEMORY || Configuration.MEMORY == -1);
    }

    public Endorsements filterByPeriod(int period) {
        return filter(endor -> endor.getPeriod() == period);
    }

    public Endorsements filterByMarket(Market market) {
        return filter(endor -> endor.getMarket().getName().equals(market.getName()));
    }

    public double[] toArray() {
        double[] values = new double[endors.size()];

        for (int i = 0; i < endors.size(); ++i) {
             values[i] = endors.get(i).getValue();
        }
        return values;
    }

    public Market getSelectedMarket(int period){
        List<Endorsement> lastTransaction = filterByPeriod(period).endors;
        return lastTransaction.get(0).getMarket();
    }

    public int size() {
        return endors.size();
    }
}