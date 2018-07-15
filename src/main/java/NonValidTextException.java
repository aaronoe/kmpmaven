/**
 * Exception die geworfen wird, falls ein ungueltiger Suchtext an ein
 * KMP-Konstruktor uebergeben wird
 */
public class NonValidTextException extends Exception {

    public NonValidTextException(String message) {
        super(message);
    }

}
