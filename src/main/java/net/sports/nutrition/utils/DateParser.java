package net.sports.nutrition.utils;

import net.sports.nutrition.controllers.CartController;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This class parse DateTime from String by setting formats.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class DateParser {

    private static final Logger log = Logger.getLogger(CartController.class);

    private final static DateTimeFormatter[] possibleFormats = new DateTimeFormatter[]{
            DateTimeFormat.forPattern("yyyy-MM-dd"),
            DateTimeFormat.forPattern("yyyy/MM/dd"),
            DateTimeFormat.forPattern("yyyy.MM.dd"),
            DateTimeFormat.forPattern("yyyy,MM,dd"),
            DateTimeFormat.forPattern("yyyy,MM,dd,HH,mm")};

    /**
     * Returns DateTime
     *
     * @param date - date for parse
     * @return <tt>DateTime</tt>if the action is successful, <tt>null</tt> otherwise.
     */
    public static DateTime parseDateTime(String date) {

        DateTime resultDate = null;
        for (DateTimeFormatter format : possibleFormats) {
            try {
                resultDate = DateTime.parse(date, format);
            } catch (IllegalArgumentException e) {
                log.error("Parse time from string", e);
            }
        }

        return resultDate;
    }
}
