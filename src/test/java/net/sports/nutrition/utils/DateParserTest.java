package net.sports.nutrition.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 25.02.2016 20:54
 */

public class DateParserTest {

    DateTimeFormatter[] possibleFormats;

    @Test
    public void testParseDateTimeWithCorrectDate() throws Exception {
        Map<String, String> correctDates = new HashMap<>();
        correctDates.put("yyyy-MM-dd", "2010-11-15");
        correctDates.put("yyyy/MM/dd", "2010/11/15");
        correctDates.put("yyyy.MM.dd", "2010.11.15");
        correctDates.put("yyyy,MM,dd", "2010,11,15");
        correctDates.put("yyyy,MM,dd,HH,mm", "2010,11,15,08,15");

        for (Map.Entry<String, String> correctDate : correctDates.entrySet()) {
            DateTime dateTime = DateParser.parseDateTime(correctDate.getValue());
            assertNotNull(dateTime);
            assertEquals(correctDate.getValue(), dateTime.toString(correctDate.getKey()));
        }
    }

    @Test
    public void testParseDateTimeWithIncorrectDate() throws Exception {
        String[] incorrectDates = {"2374,10-10", "2012-32-12", "2012-10-33", "2010-10-15-33-40", "2010,10,15,90,70",
                "10-12-2010", "10-2012-10", "2010 11 11", "20101111", "2010 11 11", "2010+11+11", "201011 11",
                "2010 1111"};

        for (String incorrectStrDate : incorrectDates) assertNull(DateParser.parseDateTime(incorrectStrDate));

    }
}