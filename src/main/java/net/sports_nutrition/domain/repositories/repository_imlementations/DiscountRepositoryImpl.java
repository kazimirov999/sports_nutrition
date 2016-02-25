package net.sports_nutrition.domain.repositories.repository_imlementations;

import net.sports_nutrition.domain.entities.Discount;
import net.sports_nutrition.domain.entities.Product;
import net.sports_nutrition.domain.repositories.IDiscountRepository;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 0:08
 */
@Repository
public class DiscountRepositoryImpl extends GenericRepositoryImpl<Discount, Long> implements IDiscountRepository {

    public DiscountRepositoryImpl() {
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
