package net.sports.nutrition.utils;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 09.02.2016 22:46
 */
public class StringUtil {

    private StringUtil() {
    }

    public static String replaceString(StringBuilder string, String wordForReplace, String insertString) {

        StringBuilder word = new StringBuilder("");
        word.append("{").append(wordForReplace).append("}");

        int indexFirst = string.indexOf(word.toString());
        int indexLast = string.indexOf("}",indexFirst);

        string.delete(indexFirst, indexLast + 1);

        return string.insert(indexFirst, insertString).toString();
    }
}
