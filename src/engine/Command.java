package engine;

import java.io.PrintStream;
import java.util.ArrayList;

import engine.interactions.DoorInteraction;
import engine.interactions.Interaction;
import engine.interactions.NPCInteraction;

public class Command {
    private static final Command[] COMMANDS = new Command[] {
        new Command("examine", "get more information about something", (player, params, stream) -> {
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
                if(i.getName().equalsIgnoreCase(params)) {
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
        }),
        new Command("take", "pick up an item", (player, params, stream) -> {
            if(params == null) {
                stream.println("What do you want to take?");
                return;
            }
            Item item = player.getCurrentRoom().takeItemIfPresent(params);
            if(item == null) {
                if(player.getCurrentRoom().getInteraction(params) != null) {
                    stream.println("You can't pick that up.");
                } else {
                    stream.println("There is no " + params + " here.");
                }
                return;
            }
            player.addItem(item);
            stream.println("You took the " + params + ".");
        }),
        new Command("inventory", "see the items in your inventory", (player, params, stream) -> {
            ArrayList<Item> inventory = player.getInventory();
            
            if(inventory.isEmpty()) stream.println("You currently have no items.");
            else stream.println("You currently have the following items:");
            
            for(Item i : inventory) {
                stream.println(i.getName());
            }
        }),
        new Command("enter", "go to another room by entering a passage", (player, params, stream) -> {
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
            stream.println("You can't enter that.");
        }),
        new Command("talk", "talk to someone", (player, params, stream) -> {
            if(params == null) {
                stream.println("Who do you want to talk to?");
                return;
            }
            Interaction interaction = player.getCurrentRoom().getInteraction(params);
            if(interaction == null) {
                stream.println(params + " is not here.");
                return;
            }
            if(interaction instanceof NPCInteraction) {
                ((NPCInteraction) interaction).talk(player, stream);
                return;
            }
            stream.println("*no answer*");
        }),
        new Command("help", "get a list of all available commands", (player, params, stream) -> {
            stream.println("You can use the following commands:");
            for(Command c : getCommands()) {
                stream.print(Ansi.bold(c.commandName));
                stream.print(" - ");
                stream.println(c.description);
            }
        })
    };
    
    private final String commandName;
    private final String description;
    private final CommandFunction function;
    
    private Command(String commandName, String description, CommandFunction function) {
        this.commandName = commandName;
        this.description = description;
        this.function = function;
    }
    
    private static Command[] getCommands() {
        return COMMANDS;
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
        function.execute(player, params, stream);
    }
    
    @FunctionalInterface
    private static interface CommandFunction {
        public void execute(Player player, String params, PrintStream stream);
    }
}
