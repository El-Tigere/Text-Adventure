package engine;

import java.io.PrintStream;
import java.util.ArrayList;

public class Player {
    public final Game game;
    private final String name;
    private ArrayList<Item> inventory;
    private Room currentRoom;
    
    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
        inventory = new ArrayList<>();
        currentRoom = game.getEntryRoom();
    }
    
    public String getName() {
        return name; 
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    public void addItem(Item item) {
        inventory.add(item);
    }
    
    public void printInfo(PrintStream stream) {
        currentRoom.printDescription(stream);
    }
    
    public void executeCommand(String commandString, PrintStream stream) {
        Command.executeString(this, commandString, stream);
    }
}
