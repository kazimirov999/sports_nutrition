package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Brand;

import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 20.01.2016 12:25
 */
public interface IBrandRepository extends IGenericRepository<Brand,Long> {

    List<Brand> findAll();

    public List<Brand> getBrandByCategoryId(Long categoryId);

    Map<Brand, Long> getAmountProductByBrand();

    List<Brand> getBrandByCountryId(Long countryId);

    Brand getBrandByName(String brandName);

    Integer deleteById(Long brandId);
}
