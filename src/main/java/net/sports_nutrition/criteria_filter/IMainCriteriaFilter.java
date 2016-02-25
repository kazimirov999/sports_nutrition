package net.sports_nutrition.criteria_filter;

import net.sports_nutrition.form.beans.FormFilterBean;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 12:13
 */
public interface IMainCriteriaFilter {



    Criteria mainFilter(Long categoryId, Session session, FormFilterBean filterParams);
}
