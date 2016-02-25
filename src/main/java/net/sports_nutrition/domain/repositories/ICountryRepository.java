package net.sports_nutrition.domain.repositories;

import net.sports_nutrition.domain.entities.Country;

import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 20.01.2016 22:43
 */
public interface ICountryRepository extends IGenericRepository<Country,Long> {

    public List<Country> findAll();

    Map<Country, Long> getAmountProductsByCategory();
}
