package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.repositories.IBrandRepository;
import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:50
 */

@Transactional
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandRepository brandRepository;

    @Transactional(readOnly = true)
    @Override
    public Brand getBrandById(Long id) {

        return brandRepository.findById(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {

        return brandRepository.saveOrUpdate(brand);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Brand> findAllBrands() {

        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Brand getBrandByName(String brandName) {

        return brandRepository.getBrandByName(brandName);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean brandIsExist(Brand brand) {
        Boolean isExist = false;
        if (getBrandByName(brand.getName()) != null) {
            isExist = true;
        }

        return isExist;
    }

    @Override
    public Brand updateBrand(Brand brand) {

        return brandRepository.edit(brand);
    }

    @Override
    public Integer deleteById(Long brandId) {

        return brandRepository.deleteById(brandId);
    }

    @Override
    public Boolean checkBeforeUpdateBrand(Brand brand) {
        Boolean check = true;
        Brand b = getBrandByName(brand.getName());
        if (b != null && !b.getId().equals(brand.getId())) {
            check = false;
        }
        return check;
    }

    public void setBrandRepository(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
}
