import java.util.Scanner;

import engine.Game;
import engine.Player;
import testgame.Rooms;

public class App {
    
    public static void main(String[] args) throws Exception {
        Game game = new Game(Rooms.ENTRY_ROOM);
        Player player =new Player(game);
        player.printInfo(System.out);
        
        Scanner inputScanner = new Scanner(System.in);
        String input;
        while(true) {
            System.out.println("What do you want to do?");
            input = inputScanner.nextLine();
            if(input.equals("exit")) break;
            player.executeCommand(input, System.out);
        }
        
        inputScanner.close();
    }
}
