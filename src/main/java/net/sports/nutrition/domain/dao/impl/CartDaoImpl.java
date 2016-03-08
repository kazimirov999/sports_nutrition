package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.dao.ICartDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Cart Data Access Object is the class providing
 * access to cart and cart type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class CartDaoImpl extends GenericDaoImpl<Cart, Long> implements ICartDao {

    public CartDaoImpl() {
        super(Cart.class);
    }

    @Override
    public List<Cart> findAll() {

        return getSession()
                .createCriteria(getType())
                .addOrder(Order.asc("date"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }
}
