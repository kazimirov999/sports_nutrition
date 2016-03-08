package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.dao.IBrandDao;
import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to work with the Brand, using IBrandDao.
 * .<p>
 * Implementation of IBrandDao interface is annotated for automatic resource injection.
 * </p>
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandDao brandDao;

    @Transactional(readOnly = true)
    @Override
    public Brand getBrandById(Long id) {

        return brandDao.findById(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {

        return brandDao.saveOrUpdate(brand);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Brand> findAllBrands() {

        return brandDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Brand getBrandByName(String brandName) {

        return brandDao.getBrandByName(brandName);
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

        return brandDao.edit(brand);
    }

    @Override
    public Integer deleteById(Long brandId) {

        return brandDao.deleteById(brandId);
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

    public void setBrandDao(IBrandDao brandDao) {
        this.brandDao = brandDao;
    }
}
