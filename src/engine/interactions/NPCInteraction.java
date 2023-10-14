package engine.interactions;

import java.io.PrintStream;

import engine.Player;

public class NPCInteraction extends Interaction {
    public NPCInteraction(String name) {
        super(name);
    }
    
    @Override
    public void printShortDescription(PrintStream stream) {
        stream.print("You see ");
        stream.print(name);
        stream.println(".");
    }
    
    @Override
    public void examine(Player player, PrintStream stream) {
        stream.print("You see ");
        stream.print(name);
        stream.println(".");
    }
}
