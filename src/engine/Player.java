package engine;

import java.io.PrintStream;

public class Player {
    public Game game;
    private Room currentRoom;
    
    public Player(Game game) {
        this.game = game;
        currentRoom = game.getEntryRoom();
    }
    
    public void printInfo(PrintStream stream) {
        stream.println(currentRoom.getDescription());
    }
    
    public void executeCommand(String commandString, PrintStream stream) {
        Command.executeString(this, commandString, stream);
    }
}