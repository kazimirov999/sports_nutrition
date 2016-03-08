package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Country;

import java.util.List;

/**
 * Service to work with the Country.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICountryService {

    /**
     * Returns  all countries.
     *
     * @return <tt>list of Country</tt> if the countries is exist, <tt>null</tt> otherwise
     */
    List<Country> findAllCountries();

    /**
     * Returns the country by id.
     *
     * @param countryId - identifier of the country
     * @return <tt>list of Country</tt> if the discounts is exist, <tt>null</tt> otherwise
     */
    Country getCountryById(Long countryId);
}
