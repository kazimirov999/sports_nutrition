package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.dao.ICountryDao;
import net.sports.nutrition.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service to work with the Country, using ICountryDao.
 * .<p>
 * Implementation of ICountryDao interface is annotated for automatic resource injection.
 * </p>
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryDao countryDao;

    @Transactional(readOnly = true)
    @Override
    public List<Country> findAllCountries() {

        return countryDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Country getCountryById(Long countryId) {

        return countryDao.findById(countryId);
    }

}
