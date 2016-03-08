package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Cart;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * The Cart Data Access Object is the interface providing
 * access to cart and cart type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICartDao extends IGenericDao<Cart,Long> {

    /**
     * Returns  all carts.
     *
     * @return <tt>list of Cart</tt> if the carts is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Cart> findAll()throws HibernateException;
}
