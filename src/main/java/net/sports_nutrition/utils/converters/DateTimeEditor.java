package net.sports_nutrition.utils.converters;

import net.sports_nutrition.utils.DateParser;
import org.joda.time.DateTime;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 04.02.2016 11:46
 */
public class DateTimeEditor extends PropertyEditorSupport {

    public DateTimeEditor() {    }

    @Override
    public String getAsText() {
        DateTime dateTime = (DateTime) this.getValue();
        if (dateTime == null) return null;
        StringBuilder strBuilder = new StringBuilder("");
        strBuilder.append(dateTime.getYear())
                .append("-")
                .append(dateTime.getMonthOfYear())
                .append("-")
                .append(dateTime.getDayOfMonth());
        return strBuilder.toString();
    }

    @Override
    public void setAsText(String date) throws IllegalArgumentException {
        System.out.println("befour parse date====> " + date);
        DateTime dateTime = DateParser.parseDateTime(date);
        this.setValue(dateTime);

    }
}
