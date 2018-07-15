/**
 * Exception die geworfen wird, falls kein gueltiger Pfad an ein
 * ReadFile-Konstruktor uebergeben wird
 */
public class NoValidPathException extends Exception {
    public NoValidPathException(String message) {
        super(message);
    }
}
