import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Implementierung Aequivalenzklassentest der KMP-Klasse
 */
public class KMPTest {

    /**
     * Test der Aequivalenzklasse leeres Patter expected:
     * NonValidPatternException
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test(expected = NonValidPatternException.class)
    public void testBuildEmptyPatternKMP()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {

        @SuppressWarnings("unused")
        KMP toTest = null;
        toTest = new KMP("", "abc", 0);
    }

    /**
     * Test der Aequivalenzklasse negativer Zeilenindex expected:
     * NonValidIndexException
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test(expected = NonValidIndexException.class)
    public void testBuildNegativIndexKMP()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {

        @SuppressWarnings("unused")
        KMP toTest = null;
        toTest = new KMP("abc", "", -1);
    }

    /**
     * Test der Aequivalenzklasse Valider KMP Build expected: erfolgreiche
     * Instanziierung
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */

    @Test
    public void testBuildValidKMP() throws NonValidIndexException, NonValidPatternException, NonValidTextException {

        @SuppressWarnings("unused")
        KMP toTest = null;
        toTest = new KMP("abc", "abcabc", 0);
    }

    /**
     * Test der Aequivalenzklassen Validen Suchtext setzen expected:
     * erfolgreiches ersetzen des alten Suchtextes
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testSetValidText() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "def", 0);
        toTest.setText("abcabc");
        assertEquals("abcabc", toTest.getText());
    }

    /**
     * Test der Aequivalenzklasse setzen eines validen Zeilenindexes expected
     * erfolgreiches Ersetzen des alten Zeilenindexes
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testSetValidColumn() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "def", 0);
        toTest.setColumn(1);
        assertEquals(1, toTest.getColumn());
    }

    /**
     * Testen der Aequivalenzklasse Setzen eines invaliden Zeilenindexes
     * expected: NonValidIndexException
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test(expected = NonValidIndexException.class)
    public void testSetInvalidColumn() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "def", 0);
        toTest.setColumn(-1);
    }

    /**
     * Testen der Aequivalenzklasse zuruecksetzen der aktuellen Ergebnisse
     * expected: Groesse der Ergebnisliste==0
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testResetResults() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "abcabc", 0);
        toTest.search();
        toTest.resetResult();
        ArrayList<Integer> result = toTest.result();
        assertTrue(0 == result.size());
    }

    /**
     * Test der Aequivalenzklasse valide Suche Pattern nicht enthalten expected:
     * Groesse der Ergebnisliste=0, keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchNoResult()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "def", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(0 == result.size());
    }

    /**
     * Test der Aequivalenzklasse valide Suche ein Ergebnis enthalten expected:
     * Groesse der Ergebnisliste==1 und Index des ersten Vorkommens enthalten,
     * keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchOneResult()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "aabcc", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 1);
    }

    /**
     * Test der Aquivalenzklasse valide Suche, multiple Ergebnisse enthalten
     * expected: Groesse der Ergebnisliste==2 und Indizes der Vorkommen
     * enthalten keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchMultipleResults()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc", "aabccabc", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(2 == result.size() && result.get(0) == 1 && result.get(1) == 5);
    }

    /**
     * Test der Aequivalenzklasse valide Suche ueberlappende Ergebnisse
     * enthalten expected: Groesse der Ergebnisliste==2 und Indizes der
     * Vorkommen enthalten keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchOverlappingResults()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("oto", "ototo", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(2 == result.size() && result.get(0) == 0 && result.get(1) == 2);
    }

    /**
     * Test der Aequivalenzklasse valide Suche durch whitespace unterbrochenes
     * Pattern expected: Groesse der Egebnisliste==1 und index des Vorkommens
     * enthalten keine exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchInterruptedPattern()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abc def", "zabc defz", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 1);
    }

    /**
     * Test der Aequivalenzklasse valide Suche CaseSensitive Unterscheidung
     * expected: groesse der Ergebnisliste==1 und Index des grossgeschrieben
     * pattern keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchCaseSensitivity()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("Abc", "zAbc abcz", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 1);
    }

    /**
     * Test der Aequivalenzklasse valide Suche kein Ergebnis, da groeßere
     * Pattern expected: Groesse der Ergebnisliste==0, keine Exception
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchNoResultBiggerPatternlength()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abcdef", "abc", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(0 == result.size());
    }

    /**
     * Test der Aequivalenzklasse valide Suche, Patternlaenge ==1
     * expected: keine exception,
     *
     * @throws NonValidTextException
     * @throws NonValidPatternException
     * @throws NonValidIndexException
     */
    @Test
    public void testValidSearchOneResultPlEqOne() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("a", "bcdeabc", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 4);

    }

    /**
     * Test der Aequivalenzklasse valide Suche Patternlaenge==2
     * expected: keine exception, 1 ergebnis
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testValidSearchOneResultPlEqTwo() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("aa", "cdefgaabc", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 5);
    }

    /**
     * Test der Aequivalenzklasse valide Suche Patternlaenge==Textlaenge
     * expected: keine exception, 1 ergebnis
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */

    @Test
    public void testValidSearchOneResultPlEqTl() throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = null;
        toTest = new KMP("abcdefghijklmnopqrstuvwxz", "abcdefghijklmnopqrstuvwxz", 0);
        toTest.search();
        ArrayList<Integer> result = toTest.result();
        assertTrue(1 == result.size() && result.get(0) == 0);
    }

    /**
     * Test der Funktionalitaet des Highlightings der Ausgabe expectet:
     * ANSI-Escape-Seq. ist in Ausgabestring enthalten
     *
     * @throws NonValidIndexException
     * @throws NonValidPatternException
     * @throws NonValidTextException
     */
    @Test
    public void testPrintSearchResultsAndHiglighting()
            throws NonValidIndexException, NonValidPatternException, NonValidTextException {
        KMP toTest = new KMP("abc", "abc", 0);
        toTest.search();
        String toPrint = toTest.testPrintResult();
        assertTrue(toPrint.charAt(0) == '0' && toPrint.charAt(2) == '1');
        String toCompare = toPrint.substring(4);
        assertEquals(("" + (char) 27 + "[101m" + "abc" + (char) 27 + "[0m"), toCompare);
    }

}
