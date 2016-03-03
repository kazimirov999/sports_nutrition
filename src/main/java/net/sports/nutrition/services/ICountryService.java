package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Country;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.02.2016 14:28
 */
public interface ICountryService {

    List<Country> findAllCountries();

    Country getCountryById(Long countryId);
}
