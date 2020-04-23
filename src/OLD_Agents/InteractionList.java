package OLD_Agents;

import java.util.ArrayList;

public class InteractionList {
    private ArrayList<Interaction> interactions;

    public InteractionList() {
        interactions = new ArrayList<>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    public void forgetInteractions(int minTime) {
        interactions.removeIf(interaction -> (interaction.getTime() < minTime));
    }

    public void deleteInteraction(int marketNumber, int iterationTime) {
        int interactionsSize = interactions.size();
        for (int i = interactionsSize - 1; i >= 0; i--) {
            if (interactions.get(i).getMarket() == marketNumber && interactions.get(i).getTime() == iterationTime) {
                interactions.remove(i);
                break;
            }
        }
    }

    public ArrayList<Integer> getKnownMarketAttributes(int marketNumber) {
        ArrayList<Integer> marketAttributes = new ArrayList<>();
        for (Interaction interaction : interactions) {
            if (interaction.getMarket() == marketNumber) {
                ArrayList<Integer> generatedExperience = interaction.getGeneratedExperience();
                for (int attribute : generatedExperience) {
                    if (!marketAttributes.contains(attribute)) {
                        //System.out.println(EndorsementList.getEndorsement(attribute));
                        marketAttributes.add(attribute);
                    }
                }
            }
        }
        return marketAttributes;
    }
}
