package engine;

import java.io.PrintStream;
import java.util.ArrayList;

public class Room {
    private final String description;
    private ArrayList<Interaction> interactions;
    
    public Room(String description) {
        this.description = description;
        interactions = new ArrayList<Interaction>();
    }
    
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    
    public void removeInteraction(Interaction interaction) {
        interactions.remove(interaction);
    }
    
    public Item takeItemIfPresent(String name) {
        Interaction target = null;
        for(Interaction i : interactions) {
            if(i instanceof ItemInteraction && i.name.equalsIgnoreCase(name)) {
                target = i;
                break;
            }
        }
        if(target == null) return null;
        interactions.remove(target);
        return ((ItemInteraction) target).getItem();
    }
    
    public void printDescription(PrintStream stream) {
        stream.println(description);
        
        if(interactions.size() > 0) stream.println();
        for(Interaction i : interactions) {
            i.printShortDescription(stream);
        }
    }
}
