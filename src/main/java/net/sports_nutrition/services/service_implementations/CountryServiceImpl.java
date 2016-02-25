package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.domain.entities.Country;
import net.sports_nutrition.domain.repositories.ICountryRepository;
import net.sports_nutrition.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.02.2016 14:28
 */

@Transactional
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Country> getAllCountries() {

        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Country getCountryById(Long countryId) {

        return countryRepository.findById(countryId);
    }

}
