package engine;

import java.io.PrintStream;

public class ItemInteraction extends Interaction {
    private final Item item;
    
    public ItemInteraction(Item item) {
        super(item.getName());
        this.item = item;
    }
    
    @Override
    public void printShortDescription(PrintStream stream) {
        stream.print("You see a ");
        stream.print(name);
        stream.println(" lying on the floor.");
    }
    
    @Override
    public void examine(Player player, PrintStream stream) {
        stream.print(item.getDescription());
    }
}
