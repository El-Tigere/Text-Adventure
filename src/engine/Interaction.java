package engine;

import java.io.PrintStream;

public class Interaction {
    protected Room room;
    public final String name;
    
    public Interaction(String name) {
        this.name = name;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void printShortDescription(PrintStream stream) {
        stream.println(name);
    }
    
    public void examine(Player player, PrintStream stream) {
        stream.println(name);
    }
}
