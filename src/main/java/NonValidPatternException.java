/**
 * Exception die geworfen wird, falls kein Pattern an ein
 * KMP-Konstruktor uebergeben wird
 */
public class NonValidPatternException extends Exception {

    public NonValidPatternException(String message) {
        super(message);
    }

}
