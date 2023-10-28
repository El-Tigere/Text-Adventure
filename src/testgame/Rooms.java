package testgame;

import engine.Ansi;
import engine.Room;
import engine.interactions.DoorInteraction;
import engine.interactions.ItemInteraction;
import engine.interactions.NPCInteraction;

public class Rooms {
    public static final Room ENTRY_ROOM = new Room("You are in an empty room.");
    public static final Room LIBRARY = new Room("You are in a large library with bookshelves that reach up to the cieling.");
    
    static {
        ENTRY_ROOM.addInteraction(new ItemInteraction(Items.BRICK));
        ENTRY_ROOM.addInteraction(new DoorInteraction("wide wooden door", LIBRARY));
        
        LIBRARY.addInteraction(new DoorInteraction("wide wooden door", ENTRY_ROOM));
        LIBRARY.addInteraction(new NPCInteraction("Carl the librarian", (player, stream) -> {
            stream.println("Hello. Please Take this book.\n" + Ansi.italic("Carl gives you a ") + Ansi.bold("book") + Ansi.italic("."));
            player.addItem(Items.BOOK);
        }, (player, item, stream) -> {
            if(item.equals(Items.BRICK)) {
                stream.println("Thak you! That's exactly what I need.");
                return true;
            }
            stream.println("I don't need that.");
            return false;
        }));
    }
}
