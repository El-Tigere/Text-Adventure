package engine;

public class Game {
    private Room entryRoom;
    
    public Game(Room entryRoom) {
        this.entryRoom = entryRoom;
    }
    
    public Room getEntryRoom() {
        return entryRoom;
    }
}
