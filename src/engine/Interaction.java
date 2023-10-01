package engine;

import java.io.PrintStream;

public class Interaction {
    protected Room room;
    private String name;
    
    public Interaction(String description) {
        this.name = description;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public String getDescription() {
        return name;
    }
    
    public void examine(Player player, PrintStream stream) {
        stream.println(name);
    }
}
