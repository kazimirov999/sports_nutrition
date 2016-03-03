package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.repositories.ICartRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 16:12
 */

@Repository
public class CartRepositoryImpl extends GenericRepositoryImpl<Cart, Long> implements ICartRepository {

    public CartRepositoryImpl() {
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
