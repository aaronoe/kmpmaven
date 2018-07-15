/**
 * Exception die geworfen wird, falls eine negative Zeile an ein
 * KMP-Konstruktor uebergeben wird
 */
public class NonValidIndexException extends Exception {

    public NonValidIndexException(String message) {
        super(message);
    }
}
