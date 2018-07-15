import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadFile Klasse, die alle zum Einlesen und zurueckgeben von Strings
 * aus einer Datei benoetigt werden
 */

public class ReadFile {

    private String filename = null;
    private ArrayList<String> result = null;

    /**
     * Konstruktor zur Erzeugung eines ReadFile-Objekts
     *
     * @throws NoValidPathException - Exception wird geworfen, wenn der Filename leer ist
     */
    public ReadFile(String filename) throws NoValidPathException {

        this.filename = filename;
        if (this.filename == "") {
            throw new NoValidPathException("Please check the Filepath!");
        }
        this.result = new ArrayList<String>();
    }

    /**
     * Beispiel-Implementation fuer die Verwendung eines ReadFile-Objekts
     *
     * @param args
     */
    public static void main(String[] args) {

        /*
         * Erzeugung eines Strings mit dem Pfadnamen, und eines entsprechenden
         * Readfile-Objekts
         */
        String name = "/home/mark/workspace/ReadFromFile/src/readEmpty.txt";
        ReadFile readFile = null;
        try {
            readFile = new ReadFile(name);
        } catch (NoValidPathException e) {
            e.printStackTrace();
        }

        /*
         * Verwendung des ReadFile-Objektes um die Datei auszulesen
         */
        try {
            readFile.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Beispielweises Ausgeben der gelesenen Datei
         */
        for (int i = 0; i < readFile.result.size(); i++) {
            System.out.print(readFile.result.get(i).toString());
            System.out.println("");
        }
    }

    /**
     * Liest den Inhalt einer Datei, falls diese existiert und fuegt den Inhalt
     * in die Result-ArrayList ein Schließt danach die benuetigten Reader
     *
     * @throws IOException
     */
    public void read() throws IOException, FileNotFoundException {

        String line = "";

        FileReader fileReader = new FileReader(this.filename);
        BufferedReader bufferdReader = new BufferedReader(fileReader);

        while (bufferdReader != null && (line = bufferdReader.readLine()) != null) {
            this.result.add(line);
        }

        bufferdReader.close();
        fileReader.close();
    }

    /**
     * Gibt die Resultate des Einlesens zurueck
     *
     * @return ArrayList<String> die resultierenden zeilenweise Inhalte der
     * Datei
     */
    public ArrayList<String> result() {
        return this.result;
    }

}
