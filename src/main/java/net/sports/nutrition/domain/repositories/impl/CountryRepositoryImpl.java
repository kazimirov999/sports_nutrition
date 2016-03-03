package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.repositories.ICountryRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 20.01.2016 23:03
 */
@Repository
public class CountryRepositoryImpl extends GenericRepositoryImpl<Country, Long> implements ICountryRepository {

    public CountryRepositoryImpl() {
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