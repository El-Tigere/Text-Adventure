package engine.interactions;

import java.io.PrintStream;

import engine.Player;
import engine.Room;

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
