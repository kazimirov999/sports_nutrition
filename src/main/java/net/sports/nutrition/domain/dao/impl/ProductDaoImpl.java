package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.criteria.filter.IMainCriteriaFilter;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.domain.dao.IProductDao;
import net.sports.nutrition.form.beans.FormFilterBean;
import net.sports.nutrition.utils.FinderByRestriction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * The Product Data Access Object is the class providing
 * access to product and product type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements IProductDao {

    @Autowired
    IMainCriteriaFilter mainCriteriaFilter;

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public Product getProductByName(String name) {

        return (Product) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public Product getProductByArticleNumber(Long articleNumber) {

        return (Product) getSession().createCriteria(getType())
                .add(Restrictions.eq("articleNumber", articleNumber)).uniqueResult();
    }

    @Override
    public Integer deleteProductById(Long id) {

        return getSession()
                .getNamedQuery("Product.deleteById")
                .setLong("id", id).executeUpdate();

    }

    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType) {

        return getProductsByCriteria(categoryId, filterParams, sortType, null, null);
    }

    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType, Integer firstResult, Integer maxFetchSize) {

        Session session = getSession();
        Criteria criteria = mainCriteriaFilter.mainFilter(categoryId, session, filterParams);

        switch (sortType) {
            case PRICE_ASC:
                criteria.addOrder(Order.asc("price"));
                break;
            case PRICE_DESC:
                criteria.addOrder(Order.desc("price"));
                break;
            case NAME_ASC:
                criteria.addOrder(Order.asc("name"));
                break;
            case NAME_DESC:
                criteria.addOrder(Order.desc("name"));
                break;
        }

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (maxFetchSize != null) criteria.setMaxResults(maxFetchSize);
        if (firstResult != null) criteria.setFirstResult(firstResult);


        return criteria.list();
    }

    @Override
    public Long getProductsAmountByCategoryId(Long categoryId) {

        return (Long) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Product.CountByCategory", "id", categoryId)
                .iterator()
                .next();
    }

    @Override
    public <T> Map<T, Long> countProductsByProperty(String field, Long categoryId, List<T> propertyList, FormFilterBean filterParams) {
        Session session = getSession();
        Map<T, Long> resultPropertyMap = new LinkedHashMap<>();
        for (T property : propertyList) {
            resultPropertyMap.put(property, (Long) mainCriteriaFilter.mainFilter(categoryId, session, filterParams)
                    .add(Restrictions.eq(field, property))
                    .setProjection(Projections.rowCount()).uniqueResult());
        }

        return resultPropertyMap;
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {

        return (List<Product>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Product.getAllByCategory", "id", categoryId);

    }

    @Override
    @Transactional
    public Integer deleteAllProductByCategoryId(Long categoryId) {
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .getNamedQuery("Product.deleteByCategoryId").setLong("id", categoryId);
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public Map<Taste, Long> countProductsByTasteAndCriteria(Long categoryId, List<Taste> tasteList, FormFilterBean filterParams) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Map<Taste, Long> resultTasteMap = new LinkedHashMap<>();
        for (Taste taste : tasteList) {
            List<Long> productIds = getProductIdsByTaste(categoryId, taste);
            resultTasteMap.put(taste, (Long) mainCriteriaFilter.mainFilter(categoryId, session, filterParams)
                    .add(FinderByRestriction.disjunction("id", productIds))
                    .setProjection(Projections.rowCount()).uniqueResult());
        }
        return resultTasteMap;
    }

    @Override
    public List<Long> getProductIdsByTaste(Long categoryId, Taste taste) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        return session.getNamedQuery("Product.getProductIdsByTaste")
                .setParameter("tasteId", taste.getId())
                .setParameter("categoryId", categoryId).list();

    }

    public void setMainCriteriaFilter(IMainCriteriaFilter mainCriteriaFilter) {
        this.mainCriteriaFilter = mainCriteriaFilter;
    }
}
