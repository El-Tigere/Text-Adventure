package testgame;

import engine.Interaction;
import engine.ItemInteraction;
import engine.Room;

public class Rooms {
    public static final Room ENTRY_ROOM = new Room("You are in an empty room.");
    static {
        ENTRY_ROOM.addInteraction(new Interaction("abc"));
        ENTRY_ROOM.addInteraction(new ItemInteraction(Items.BRICK));
    }
}
