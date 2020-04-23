package OLD_Agents;

import java.util.ArrayList;

public class Interaction {
    private final int market;
    private final int time;
    private final ArrayList<Integer> generatedExperience;

    public Interaction(int market, ArrayList<Integer> generatedExperience, int time) {
        this.market = market;
        this.time = time;
        this.generatedExperience = generatedExperience;
    }

    public int getMarket() {
        return this.market;
    }

    public int getTime() {
        return this.time;
    }

    public ArrayList<Integer> getGeneratedExperience() {
        return this.generatedExperience;
    }
}
