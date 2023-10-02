package engine;

import java.io.PrintStream;

public class Player {
    public final Game game;
    private final String name;
    private Room currentRoom;
    
    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
        currentRoom = game.getEntryRoom();
    }
    
    public String getName() {
        return name;
    }
    
    public void printInfo(PrintStream stream) {
        currentRoom.printDescription(stream);
    }
    
    public void executeCommand(String commandString, PrintStream stream) {
        Command.executeString(this, commandString, stream);
    }
}
