import java.util.ArrayList;

/**
 * KMP Klasse zur Durchfuehrung des KMP-Algorithmus und formatierter Aufgabe
 */
public class KMP {

    private ArrayList<Integer> result = null;
    private int[] prefixTable = null;
    private String pattern;
    private String text;
    private int column;

    public KMP(String pattern, String text, int column)
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {

        if (column < 0) {
            throw new NonValidPatternException("Column Index must be bigger than or equal to 0");
        }

        if (pattern.length() <= 0) {
            throw new NonValidPatternException("Patternlength must be bigger than 0");
        }

        if (text.length() <= 0) {
            throw new NonValidTextException("Textlength must be bigger or equal to 0");
        }
        this.result = new ArrayList<Integer>();
        this.pattern = pattern;
        this.text = text;
        this.column = column;
        this.buildPrefixTable();
    }

    /**
     * Beispielimplementation eines KMPs mit direkter String-Uebergabe
     *
     * @param args
     */

    public static void main(String[] args) {

        /*
         * Initialisierung eines Beispielpatterns und Suchtextes
         */
        String text = "es war einmal ein wald, der war gross und dunkel";
        String pattern = "es";

        /*
         * Initialisierung eines TestKMP zur Suche des Patters im Text
         */
        KMP testKMP = null;
        try {
            testKMP = new KMP(pattern, text, 0);
        } catch (NonValidIndexException | NonValidPatternException | NonValidTextException e) {
            e.printStackTrace();
        }

        /*
         * Ausgabe der ermittelten Suchergebnisse
         */
        System.out.println(text);
        System.out.println(pattern);
        testKMP.search();
        testKMP.printResult();
    }

    /**
     * Fuehrt das aus der Beschreibung des KMP-Algorithmmus bekannte
     * Preprocessing aus. Ermittelt hierbei die echten Praefixe innerhalb des
     * Pattern
     */

    private void buildPrefixTable() {
        this.prefixTable = new int[this.pattern.length()];
        this.prefixTable[0] = 0;

        for (int i = 1; i < this.pattern.length(); i++) {
            int j = this.prefixTable[i - 1];
            while (j > 0 && this.pattern.charAt(i) != this.pattern.charAt(j)) {
                j = this.prefixTable[j - 1];
            }
            if (this.pattern.charAt(i) == this.pattern.charAt(j)) {
                j++;
            }
            this.prefixTable[i] = j;
        }

    }

    /**
     * Implementation des KMP-Suchalgorithmus. Einige Anpassungen um das Finden
     * von ueberlappenden Vorkommen eines Patterns im Suchtext zu realisieren
     */

    public void search() {

        int j = 0;
        for (int i = 0; i < this.text.length(); i++) {
            while (j > 0 && this.text.charAt(i) != this.pattern.charAt(j)) {
                j = this.prefixTable[j - 1];
            }
            if (this.text.charAt(i) == this.pattern.charAt(j)) {
                j++;
                if (j == this.pattern.length()) {
                    this.result.add((i - (j - 1)));
                    j = 0;
                    if (this.pattern.length() > 2) {
                        i = i - j - 2;
                    } else if (this.pattern.length() == 2) {
                        i = i - j - 1;
                    }
                }
            }
        }

    }

    /**
     * formatierte Ausgabe der Resultate des KMP
     */

    public void printResult() {
        for (int i = 0; i < result.size(); i++) {
            System.out.print(this.column + ":" + (result.get(i) + 1) + " ");
            System.out.print(text.substring(0, result.get(i)));
            System.out.print((char) 27 + "[101m");
            System.out.print(text.substring(result.get(i), (result.get(i) + pattern.length())));
            System.out.print((char) 27 + "[0m");
            System.out.print(text.substring((result.get(i) + pattern.length()), text.length()));
            System.out.println("");
        }

    }

    /**
     * Anpassung der Formatierten Ausgabe als return-Wert um einen JUnit-Test
     * zur Formatierung ausfuehren zu koennen
     *
     * @return String testbare Rueckgabe des Ausgabestrings
     */

    public String testPrintResult() {
        String toTest = "";
        for (int i = 0; i < result.size(); i++) {
            toTest += this.column + ":" + (result.get(i) + 1) + " ";
            toTest += text.substring(0, result.get(i));
            toTest += (char) 27 + "[101m";
            toTest += text.substring(result.get(i), (result.get(i) + pattern.length()));
            toTest += (char) 27 + "[0m";
            toTest += text.substring((result.get(i) + pattern.length()), text.length());

        }
        return toTest;

    }

    /**
     * gibt den aktuellen Suchtext zurueck, notwendig zum Testen der
     * setText-Funktion
     *
     * @return String Aktueller Suchtext
     */

    public String getText() {
        return this.text;

    }

    public void setText(String Text) {

        this.text = Text;

    }

    /**
     * gibt den aktuellen Zeilenindex zurueck
     *
     * @return column int Zeilenindex
     */

    public int getColumn() {
        return this.column;

    }

    /**
     * setzt den aktuellen Zeilenindex auf den angegebenen Wert, muss groesser
     * gleich 0 sein
     *
     * @param column int neuer Zeilenindex
     * @throws NonValidIndexException wird geworfen, wenn neuer Zeilenindex kleiner 0 ist
     */

    public void setColumn(int column) throws NonValidIndexException {
        if (column < 0) {
            throw new NonValidIndexException("Column Index must be bigger than or equal to 0");
        }

        this.column = column;

    }

    /**
     * setzt die Result-ArrayList zurueck, sodass keine Werte mehr enthalten
     * sind
     */

    public void resetResult() {
        this.result = new ArrayList<Integer>();
    }

    /**
     * gibt den aktuellen Inhalt der Ergebnisliste zurueck
     *
     * @return ArrayList <Integer> result, aktueller Inhalt der Ergebnisliste
     */

    public ArrayList<Integer> result() {
        return this.result;
    }

}
