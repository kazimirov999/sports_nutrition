package net.sports.nutrition.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 04.02.2016 11:28
 */
public class DateParser {

    private final static  DateTimeFormatter[] possibleFormats = new DateTimeFormatter[] {
            DateTimeFormat.forPattern("yyyy-MM-dd"),
            DateTimeFormat.forPattern("yyyy/MM/dd"),
            DateTimeFormat.forPattern("yyyy.MM.dd"),
            DateTimeFormat.forPattern("yyyy,MM,dd"),
            DateTimeFormat.forPattern("yyyy,MM,dd,HH,mm") };

    private DateParser(){}

    public static DateTime parseDateTime(String date){

        DateTime resultDate = null;
        for (DateTimeFormatter format: possibleFormats) {
           try {
               resultDate = DateTime.parse(date, format);
           }catch (IllegalArgumentException e){}
        }

        return resultDate;
    }
}
