package net.sports_nutrition.utils;

import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 14:16
 */
public class FinderByRestriction {

    private FinderByRestriction() {
    }

    public static <T> Disjunction disjunction(String field, Collection<T> elementList) {
        Property property = Property.forName(field);
        Disjunction disjunction = Restrictions.disjunction();
        for (T id : elementList)
            disjunction.add(property.eq(id));
        return disjunction;
    }
}
