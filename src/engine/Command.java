package engine;

import java.io.PrintStream;

public class Command {
    private static final Command[] COMMANDS = new Command[] {
        new Command("examine", 0) {
            @Override
            protected void execute(Player player, String[] params, PrintStream stream) {
                player.printInfo(stream);
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
