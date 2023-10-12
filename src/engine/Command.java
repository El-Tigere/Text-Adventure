package engine;

import java.io.PrintStream;
import java.util.ArrayList;

import engine.interactions.DoorInteraction;
import engine.interactions.Interaction;

public class Command {
    private static final Command[] COMMANDS = new Command[] {
        new Command("examine") {
            @Override
            protected void execute(Player player, String params, PrintStream stream) {
                // examine room
                if(params == null) {
                    player.printInfo(stream);
                    return;
                }
                
                // examine interaction
                Interaction interaction = player.getCurrentRoom().getInteraction(params);
                if(interaction != null) {
                    interaction.examine(player, stream);
                    return;
                }
                
                // examine item in inventory
                Item item = null;
                for(Item i : player.getInventory()) {
                    if(i.getName().equals(params)) {
                        item = i;
                        break;
                    }
                }
                if(item != null) {
                    stream.println(item.getDescription());
                    return;
                }
                
                stream.println("You can't find a " + params + " here.");
                return;
            }
        },
        new Command("take") {
            @Override
            protected void execute(Player player, String params, PrintStream stream) {
                if(params == null) {
                    stream.println("What do you want to take?");
                    return;
                }
                Item item = player.getCurrentRoom().takeItemIfPresent(params);
                if(item == null) {
                    stream.println("There is no " + params + " here.");
                    return;
                }
                player.addItem(item);
                stream.println("You took the " + params + ".");
            }
        },
        new Command("inventory") {
            @Override
            protected void execute(Player player, String params, PrintStream stream) {
                ArrayList<Item> inventory = player.getInventory();
                
                if(inventory.isEmpty()) stream.println("You currently have no items.");
                else stream.println("You currently have the following items:");
                
                for(Item i : inventory) {
                    stream.println(i.getName());
                }
            }
        },
        new Command("enter") {
            @Override
            protected void execute(Player player, String params, PrintStream stream) {
                if(params == null) {
                    stream.println("Where do you want to go?");
                    return;
                }
                Interaction interaction = player.getCurrentRoom().getInteraction(params);
                if(interaction == null) {
                    stream.println("There is no " + params + " here.");
                    return;
                }
                if(interaction instanceof DoorInteraction) {
                    Room targetRoom = ((DoorInteraction) interaction).getRoom();
                    player.setCurrentRoom(targetRoom);
                    stream.println("You entered the " + params + ".");
                    targetRoom.printDescription(stream);
                    return;
                }
                stream.println("You can't enter the " + params + ".");
            }
        }
    };
    
    private final String commandName;
    
    private Command(String commandName) {
        this.commandName = commandName;
    }
    
    public static void executeString(Player player, String commandString, PrintStream stream) {
        String[] parts = commandString.split(" ", 2);
        if(parts.length <= 0) return;
        
        String params = parts.length > 1 ? parts[1] : null;
        
        for(Command c : COMMANDS) {
            if(c.commandName.equals(parts[0])) {
                c.execute(player, params, stream);
                return;
            }
        }
        
        stream.println("Unknown command.");
    }
    
    protected void execute(Player player, String params, PrintStream stream) {
        stream.println(commandName + " was executed with these parameters:");
        stream.println(params);
    }
}
