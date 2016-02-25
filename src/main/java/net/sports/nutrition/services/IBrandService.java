package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Brand;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:51
 */
public interface IBrandService {

    Brand getBrandById(Long id);

    Brand saveBrand(Brand brand);

    List<Brand> findAllBrands();

    Brand getBrandByName(String brandName);

    Boolean brandIsExist(Brand brand);

    Brand updateBrand(Brand brand);

    Integer deleteById(Long brandId);

    Boolean checkBeforeUpdateBrand(Brand brand);
}
