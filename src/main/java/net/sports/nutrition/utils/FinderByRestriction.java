package net.sports.nutrition.utils;

import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

/**
 * This class builds Disjunction object for Hibernate criteria object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FinderByRestriction {

    private FinderByRestriction() {
    }

    /**
     * Builds Disjunction object from collection of element.
     *
     * @param field       - property of restriction
     * @param elementList - elements for disjunction
     * @return <tt>Disjunction object</tt>
     */
    public static <T> Disjunction disjunction(String field, Collection<T> elementList) {
        Property property = Property.forName(field);
        Disjunction disjunction = Restrictions.disjunction();
        for (T id : elementList)
            disjunction.add(property.eq(id));

        return disjunction;
    }
}
