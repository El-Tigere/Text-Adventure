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
    
    public void printDescription(PrintStream stream) {
        stream.println(description);
        
        if(interactions.size() > 0) stream.println();
        for(Interaction i : interactions) {
            i.printShortDescription(stream);
        }
    }
}
