import java.io.IOException;
import java.util.ArrayList;

/**
 * Beispielklasse zur Demonstration einer moeglichen Implementation
 * eines vollstaendigen Programmes welches Vorkommen eines Patterns in
 * einem Suchtext ermittelt. Hierzu wird zunaechst der entsprechende
 * Text aus einer Datei ausgelesen. Pattern und Dateipfad werden als
 * Kommandozeilenargumente dem Programm uebergeben
 */

public class SubstringSearch {

    public static void main(String[] args) {

        /*
         * Zunaechst wird ueberprueft, ob sowohl ein Dateipfad als auch ein
         * Pattern-String dem Programm uebergeben wurden.
         */

        if (args.length == 2) {
            String filename = args[1];
            String pattern = args[0];

            /*
             * Erzeugung eines ReadFile-Objektes, welches dazu genutzt wird den
             * Dateiinhalt auszulesen
             */
            ReadFile readFile = null;
            try {
                readFile = new ReadFile(filename);
            } catch (NoValidPathException e) {
                e.printStackTrace();
            }
            /*
             * Auslesen der Datei und Speicherung der jeweiligen Zeilen in einer
             * String-ArrayList
             */
            try {
                readFile.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
             * Rueckgabe des ausgelesenen Dateiinhaltes zur zeilenweise
             * Uebergabe an ein KMP-Objekt
             */
            ArrayList<String> read = readFile.result();

            /*
             * Erzeugung eines KMP-Objektes zur Durchfuehrung des
             * KMP-Algorithmus zur Einhaltung der asympthotischen Laufzeit vorab
             * Initialisierung einer KMP-Instanz und durchfuehrung des
             * Pre-Processings
             */
            KMP kmpAlgorithm = null;
            try {
                kmpAlgorithm = new KMP(pattern, read.get(0), (1));
            } catch (NonValidIndexException | NonValidPatternException | NonValidTextException e) {
                e.printStackTrace();
            }

            /*
             * Iteration ueber die Anzahl der Liste der eingelesenen Zeilen
             * durchfuehrung eines KMP-Algorithmus pro Zeile. Anschließend
             * formatierte Ausgabe der Resultate
             */
            for (int i = 0; i < read.size(); i++) {
                kmpAlgorithm.resetResult();
                try {
                    kmpAlgorithm.setColumn(i + 1);
                } catch (NonValidIndexException e) {
                    e.printStackTrace();
                }

                kmpAlgorithm.setText(read.get(i));

                kmpAlgorithm.search();
                kmpAlgorithm.printResult();

            }

        } else {
            /*
             * falls entweder kein Pattern oder kein Text uebergeben wurde,
             * Ausgabe einer Information an den Nutzer
             */
            System.out.println("Bitte geben Sie nur Text und Pattern an");
        }
    }

}
