package net.sports.nutrition.criteria_filter.impl;

import net.sports.nutrition.criteria_filter.IMainCriteriaFilter;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.utils.FinderByRestriction;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 12:15
 */
@Component
public class MainCriteriaFilterImpl implements IMainCriteriaFilter {

    public MainCriteriaFilterImpl() {
    }

    @Override
    public Criteria mainFilter(Long categoryId, Session session, FormFilterBean filterParams) {


        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("category.id", categoryId));
        if (filterParams == null) return criteria;

        if (filterParams.getBrandIdList() != null)
            criteria.add(FinderByRestriction.disjunction("brand.id", filterParams.getBrandIdList()));
        if (filterParams.getDiscountIdList() != null)
            criteria.add(FinderByRestriction.disjunction("discount.id", filterParams.getDiscountIdList()));
        if (filterParams.getGenderList() != null)
            criteria.add(FinderByRestriction.disjunction("gender", filterParams.getGenderList()));
        if (filterParams.getFormList() != null)
            criteria.add(FinderByRestriction.disjunction("form", filterParams.getFormList()));
        if (filterParams.getHighPrice() != null)
            criteria.add(Restrictions.le("price", filterParams.getHighPrice()));
        if (filterParams.getLowPrice() != null)
            criteria.add(Restrictions.ge("price", filterParams.getLowPrice()));

        if (filterParams.getTasteIdList() != null) {
            Query query = session.getNamedQuery("Product.getProductsIdsByTasteIds")
                    .setParameterList("tasteIds", filterParams.getTasteIdList())
                    .setParameter("id",categoryId);
            criteria.add(FinderByRestriction.disjunction("id", query.list()));
        }
        return criteria;
    }

}
