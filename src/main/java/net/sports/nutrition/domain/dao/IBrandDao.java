package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Brand;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

/**
 * The Brand Data Access Object is the interface providing
 * access to brand and brand type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IBrandDao extends IGenericDao<Brand, Long> {

    /**
     * Returns  all brands.
     *
     * @return <tt>list of Brand</tt> if the brands is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Brand> findAll() throws HibernateException;

    /**
     * Returns  the brand by name.
     *
     * @param brandName -  name of the brand
     * @return <tt>Brand</tt> if the brand is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Brand getBrandByName(String brandName) throws HibernateException;

    /**
     * Returns the brands by category id.
     *
     * @param categoryId - identifier of the category
     * @return <tt>list of Brand</tt> if the brands is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    public List<Brand> getBrandsByCategoryId(Long categoryId) throws HibernateException;

    /**
     * Returns the products amount by each brand.
     *
     * @return map where key - brand, value - count
     * @throws HibernateException
     */
    Map<Brand, Long> getAmountProductsByBrand() throws HibernateException;

    /**
     * Returns the brands by country id.
     *
     * @param countryId -  identifier of the country
     * @return <tt>list of Brand</tt> if the brands is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Brand> getBrandsByCountryId(Long countryId) throws HibernateException;

    /**
     * Deletes the brand by id.
     *
     * @param brandId - identifier of the brand
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    Integer deleteById(Long brandId) throws HibernateException;
}
