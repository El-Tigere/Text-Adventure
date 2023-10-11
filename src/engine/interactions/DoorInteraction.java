package engine.interactions;

import java.io.PrintStream;

import engine.Player;
import engine.Room;

public class DoorInteraction extends Interaction {
    private final Room room;
    
    public DoorInteraction(String name, Room room) {
        super(name);
        this.room = room;
    }
    
    public Room getRoom() {
        return room;
    }
    
    @Override
    public void printShortDescription(PrintStream stream) {
        stream.print("You see a ");
        stream.print(name);
        stream.println(".");
    }
    
    @Override
    public void examine(Player player, PrintStream stream) {
        stream.print("A ");
        stream.print(name);
        stream.println(".");
        stream.println("It leads to another room.");
    }
}
