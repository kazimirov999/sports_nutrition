package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.dao.IDiscountDao;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Discount Data Access Object is the class providing
 * access to discount and discount type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount, Long> implements IDiscountDao {

    public DiscountDaoImpl() {
        super(Discount.class);
    }

    @Override
    public Discount getDiscountByName(String discountName) {

        return (Discount) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("name", discountName))
                .uniqueResult();
    }

    @Override
    public Discount getDiscountBySize(BigDecimal discountSize) {

        return (Discount) getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(getType())
                .add(Restrictions.eq("size", discountSize))
                .uniqueResult();
    }

    @Override
    public Boolean deleteDiscountById(Long id) {
        Discount discount = (Discount) getSession().load(getType(), id);
        Hibernate.initialize(discount.getProductSet());
        for (Product p : discount.getProductSet())
            p.setDiscount(null);

        return delete(discount);
    }

    @Override
    public List<Discount> findAll() {

        return getSession().createCriteria(getType()).list();

    }

    @Override
    public List<Discount> getDiscountsByCategoryId(Long categoryId) {

        return (List<Discount>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Discount.getDiscountsByCategoryId", "id", categoryId);
    }
}
