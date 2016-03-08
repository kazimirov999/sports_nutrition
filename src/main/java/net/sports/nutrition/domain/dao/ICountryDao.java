package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Country;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

/**
 * The Country Data Access Object is the interface providing
 * access to country and country type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICountryDao extends IGenericDao<Country, Long> {

    /**
     * Returns  all countries.
     *
     * @return <tt>list of Country</tt> if the countries is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    public List<Country> findAll() throws HibernateException;

    /**
     * Returns the products amount by each country.
     *
     * @return map where key - country, value - count
     * @throws HibernateException
     */
    Map<Country, Long> getAmountProductsByCategory() throws HibernateException;
}
