package engine.interactions;

import java.io.PrintStream;

import engine.Player;

public class NPCInteraction extends Interaction {
    final INPCInteractionDialogue dialogue;
    
    public NPCInteraction(String name, INPCInteractionDialogue dialogue) {
        super(name);
        this.dialogue = dialogue;
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
    
    public void talk(Player player, PrintStream stream) {
        dialogue.talk(player, stream);
    }
    
    @FunctionalInterface
    public interface INPCInteractionDialogue {
        public void talk(Player player, PrintStream stream);
    }
}
