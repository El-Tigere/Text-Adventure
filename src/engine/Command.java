package engine;

import java.io.PrintStream;
import java.util.ArrayList;

public class Command {
    private static final Command[] COMMANDS = new Command[] {
        new Command("examine", 0) {
            @Override
            protected void execute(Player player, String[] params, PrintStream stream) {
                if(params.length < 1) {
                    player.printInfo(stream);
                    return;
                }
                Interaction interaction = player.getCurrentRoom().getInteraction(params[0]);
                if(interaction != null) {
                    interaction.examine(player, stream);
                    return;
                }
                stream.println("You can't find a " + params[0] + " here.");
                return;
            }
        },
        new Command("take", 0) {
            @Override
            protected void execute(Player player, String[] params, PrintStream stream) {
                if(params.length < 1) {
                    stream.println("What do you want to take?");
                    return;
                }
                Item item = player.getCurrentRoom().takeItemIfPresent(params[0]);
                if(item == null) {
                    stream.println("There is no " + params[0] + " here.");
                    return;
                }
                player.addItem(item);
                stream.println("You took the " + params[0] + ".");
            }
        },
        new Command("inventory", 0) {
            @Override
            protected void execute(Player player, String[] params, PrintStream stream) {
                ArrayList<Item> inventory = player.getInventory();
                
                if(inventory.isEmpty()) stream.println("You currently have no items.");
                else stream.println("You currently have the following items:");
                
                for(Item i : inventory) {
                    stream.println(i.getName());
                }
            }
        }
    };
    
    private final String commandName;
    protected final int paramCount;
    
    private Command(String commandName, int paramCount) {
        this.commandName = commandName;
        this.paramCount = paramCount;
    }
    
    public static void executeString(Player player, String commandString, PrintStream stream) {
        String[] parts = commandString.split(" ");
        if(parts.length <= 0) return;
        
        String[] params = new String[parts.length - 1];
        System.arraycopy(parts, 1, params, 0, parts.length - 1);
        
        for(Command c : COMMANDS) {
            if(c.commandName.equals(parts[0])) {
                c.execute(player, params, stream);
                return;
            }
        }
        
        stream.println("Unknown command.");
    }
    
    protected void execute(Player player, String[] params, PrintStream stream) {
        stream.println(commandName + " was executed with these parameters:");
        for(String s : params) {
            stream.println(s);
        }
    }
}
