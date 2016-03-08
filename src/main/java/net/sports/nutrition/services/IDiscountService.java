package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Discount;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service to work with the Discount, using IDiscountDao.
 * .<p>
 * Implementation of IDiscountDao interface is annotated for automatic resource injection.
 * </p>
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IDiscountService {

    /**
     * Saves discount.
     *
     * @param discount - discount for save.
     * @return <tt>Discount<tt/> if the action is successful, throw exception  otherwise.
     */
    Discount saveDiscount(Discount discount);

    /**
     * Updates discount.
     * This method should be edit discount but if discount doesn't exist, save discount as new Entity.
     *
     * @param discount - discount for update.
     * @return updated <tt>Discount</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Discount updateDiscount(Discount discount);

    /**
     * Returns discount by identifier.
     *
     * @param id -  identifier of the discount.
     * @return <tt>Discount</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Discount getDiscountById(Long id);

    /**
     * Returns  all discounts.
     *
     * @return <tt>list of Discount</tt> if the discounts is exist, <tt>null</tt> otherwise
     */
    List<Discount> findAllDiscounts();

    /**
     * Returns  the discount by name.
     *
     * @param discountName -  name of the discount
     * @return <tt>Discount</tt> if the discount is exist, <tt>null</tt> otherwise
     */
    Discount getDiscountByName(String discountName);

    /**
     * Deletes the discount by id.
     *
     * @param discountId - identifier of the discount
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    Boolean deleteDiscountById(Long discountId);

    /**
     * Returns  the discount by size.
     *
     * @param discountSize -  size of the discount
     * @return <tt>Discount</tt> if the discount is exist, <tt>null</tt> otherwise
     */
    Discount getDiscountBySize(BigDecimal discountSize);

    /**
     * Checks discount of the presence in the database.
     *
     * @param discount - discount for check
     * @return <tt>true</tt> if discount is exist, <tt>false</tt> otherwise
     */
    Boolean discountIsExist(Discount discount);

    /**
     * Checks discount before update.
     *
     * @param discount - discount for check
     * @return <tt>true</tt> if the discount of the same name or size doesn't exist, <tt>null</tt>  otherwise.
     */
    Boolean checkBeforeUpdateDiscount(Discount discount);
}
