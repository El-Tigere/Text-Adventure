package engine;

public class Ansi {
    private static final char ESC = (char) 0x1B;
    
    public static String bold(String text) {
        return ESC + "[1m" + text + ESC + "[0m";
    }
    
    public static String italic(String text) {
        return ESC + "[3m" + text + ESC + "[0m";
    }
    
    public static String underline(String text) {
        return ESC + "[4m" + text + ESC + "[0m";
    }
}
