package net.sports.nutrition.utils;

/**
 * This class for working with String.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * Replace word in a string.
     *
     * @param string         - string which makes replacement
     * @param wordForReplace - word is replaced
     * @param insertString   - string for insert
     */
    public static String replaceString(StringBuilder string, String wordForReplace, String insertString) {

        StringBuilder word = new StringBuilder("");
        word.append("{").append(wordForReplace).append("}");

        int indexFirst = string.indexOf(word.toString());
        int indexLast = string.indexOf("}", indexFirst);

        string.delete(indexFirst, indexLast + 1);

        return string.insert(indexFirst, insertString).toString();
    }
}
