package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Discount;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Discount Data Access Object is the interface providing
 * access to discount and discount type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IDiscountDao extends IGenericDao<Discount,Long> {

    /**
     * Returns  the discount by name.
     *
     * @param discountName -  name of the discount
     * @return <tt>Discount</tt> if the discount is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Discount getDiscountByName(String discountName)throws HibernateException;

    /**
     * Deletes the discount by id.
     *
     * @param discountId - identifier of the brand
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    public Boolean deleteDiscountById(Long discountId)throws HibernateException;

    /**
     * Returns  the discount by size.
     *
     * @param discountSize -  size of the discount
     * @return <tt>Discount</tt> if the discount is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Discount getDiscountBySize(BigDecimal discountSize)throws HibernateException;

    /**
     * Returns  all discount.
     *
     * @return <tt>list of Discount</tt> if the discounts is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Discount> findAll()throws HibernateException;

    /**
     * Returns the discount by category id.
     *
     * @param categoryId - identifier of the category
     * @return <tt>list of Discount</tt> if the discounts is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Discount> getDiscountsByCategoryId(Long categoryId)throws HibernateException;
}
