package engine;

import java.io.PrintStream;

public class Interaction {
    protected Room room;
    private String description;
    
    public Interaction(String description) {
        this.description = description;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void examine(Player player, PrintStream stream) {
        stream.println(description);
    }
}
