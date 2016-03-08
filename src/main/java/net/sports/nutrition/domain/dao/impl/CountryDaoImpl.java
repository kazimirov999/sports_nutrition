package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.dao.ICountryDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Country Data Access Object is the class providing
 * access to country and country type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class CountryDaoImpl extends GenericDaoImpl<Country, Long> implements ICountryDao {

    public CountryDaoImpl() {
        super(Country.class);
    }

    @Override
    public List<Country> findAll() {

        return (List<Country>) getSession().createCriteria(getType()).list();
    }

    @Override
    public Map<Country, Long> getAmountProductsByCategory() {
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().findByNamedQuery("Country.getAmountProducts");
        Map<Country, Long> result = new HashMap<>();
        for (Object[] row : list)
            result.put((Country) row[0], (Long) row[1]);

        return result;
    }
}
