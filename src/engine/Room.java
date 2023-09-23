package engine;

import java.util.ArrayList;

public class Room {
    private String description;
    private ArrayList<Interaction> interactions;
    
    public Room(String description) {
        this.description = description;
        interactions = new ArrayList<Interaction>();
    }
    
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    
    public String getDescription() {
        return description;
    }
}
