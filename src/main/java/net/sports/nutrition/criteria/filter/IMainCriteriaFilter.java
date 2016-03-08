package net.sports.nutrition.criteria.filter;

import net.sports.nutrition.form.beans.FormFilterBean;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * This interface provides  construction Hibernate criteria object,
 * for access to a group of products.  .
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IMainCriteriaFilter {

    /**
     * Builds Hibernate criteria object by filter parameters
     *
     * @param categoryId   -  identifier of the category
     * @param session      - session is used to get a physical connection with a database
     * @param filterParams - the search options
     * @return <tt>Criteria</tt>
     */
    Criteria mainFilter(Long categoryId, Session session, FormFilterBean filterParams);
}
