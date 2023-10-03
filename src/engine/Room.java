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
        
        for(Interaction i : interactions) {
            stream.println();
            stream.println(i.getName());
        }
    }
}
