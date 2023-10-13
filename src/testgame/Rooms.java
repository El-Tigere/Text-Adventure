package testgame;

import engine.Room;
import engine.interactions.DoorInteraction;
import engine.interactions.ItemInteraction;

public class Rooms {
    public static final Room ENTRY_ROOM = new Room("You are in an empty room.");
    public static final Room LIBRARY = new Room("You are in a large library with bookshelves that reach up to the cieling.");
    
    static {
        ENTRY_ROOM.addInteraction(new ItemInteraction(Items.BRICK));
        ENTRY_ROOM.addInteraction(new DoorInteraction("wide wooden door", LIBRARY));
        
        LIBRARY.addInteraction(new DoorInteraction("wide wooden door", ENTRY_ROOM));
    }
}
