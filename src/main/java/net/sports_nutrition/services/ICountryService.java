package net.sports_nutrition.services;

import net.sports_nutrition.domain.entities.Country;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.02.2016 14:28
 */
public interface ICountryService {

    List<Country> getAllCountries();

    Country getCountryById(Long countryId);
}
