package engine;

import java.io.PrintStream;

public class Interaction {
    protected Room room;
    private final String name;
    
    public Interaction(String name) {
        this.name = name;
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
