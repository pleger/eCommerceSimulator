package Agents;

import java.util.ArrayList;

public class InteractionList {
    private ArrayList<Interaction> interactions;
    public InteractionList(){
        interactions=new ArrayList<>();
    }

    public void addInteraction(Interaction interaction){
        interactions.add(interaction);
    }
    public void forgetInteractions(int minTime){
        interactions.removeIf(interaction-> (interaction.getTime()<minTime));
    }
    public ArrayList<Integer> getKnownMarketAttributes(int marketNumber){
        ArrayList<Integer> marketAttributes=new ArrayList<>();
        for(Interaction interaction:interactions){
            if(interaction.getMarket()==marketNumber){
                ArrayList<Integer> generatedExperience=interaction.getGeneratedExperience();
                for(int attribute:generatedExperience){
                    if(!marketAttributes.contains(attribute)){
                        //System.out.println(EndorsementList.getEndorsement(attribute));
                        marketAttributes.add(attribute);
                    }
                }
            }
        }
        return marketAttributes;
    }
}
