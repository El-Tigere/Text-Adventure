package engine.interactions;

import java.io.PrintStream;

import engine.Ansi;
import engine.Item;
import engine.Player;

public class NPCInteraction extends Interaction {
    final INPCInteractionDialogue dialogue;
    final INPCInteractionGive giveReaction;
    
    public NPCInteraction(String name, INPCInteractionDialogue dialogue, INPCInteractionGive giveReaction) {
        super(name);
        this.dialogue = dialogue;
        this.giveReaction = giveReaction;
    }
    
    @Override
    public void printShortDescription(PrintStream stream) {
        stream.print("You see ");
        stream.print(Ansi.bold(name));
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
    
    public boolean give(Player player, Item item, PrintStream stream) {
        return giveReaction.give(player, item, stream);
    }
    
    @FunctionalInterface
    public interface INPCInteractionDialogue {
        public void talk(Player player, PrintStream stream);
    }
    
    @FunctionalInterface
    public interface INPCInteractionGive {
        public boolean give(Player player, Item item, PrintStream stream);
    }
}
