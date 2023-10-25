package engine.interactions;

import java.io.PrintStream;

import engine.Ansi;
import engine.Item;
import engine.Player;

public class ItemInteraction extends Interaction {
    private final Item item;
    
    public ItemInteraction(Item item) {
        super(item.getName());
        this.item = item;
    }
    
    public Item getItem() {
        return item;
    }
    
    @Override
    public void printShortDescription(PrintStream stream) {
        stream.print("You see a ");
        stream.print(Ansi.bold(name));
        stream.println(" lying on the floor.");
    }
    
    @Override
    public void examine(Player player, PrintStream stream) {
        stream.println(item.getDescription());
    }
}
