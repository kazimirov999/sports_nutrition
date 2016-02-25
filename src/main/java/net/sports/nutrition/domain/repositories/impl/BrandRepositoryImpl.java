package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.repositories.IBrandRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 20.01.2016 12:27
 */
@Repository
public class BrandRepositoryImpl extends GenericRepositoryImpl<Brand, Long> implements IBrandRepository {

    public BrandRepositoryImpl() {
        super(Brand.class);
    }

    @Override
    public List<Brand> findAll() {

        return getSession().createCriteria(getType()).list();
    }

    @Override
    public List<Brand> getBrandByCategoryId(Long categoryId) {

        return (List<Brand>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Brand.getAllByCategoryIdSortByName", "id", categoryId);
    }

    @Override
    public Map<Brand, Long> getAmountProductByBrand() {
        List<Object[]> resultList = (List<Object[]>) getHibernateTemplate().findByNamedQuery("Brand.getCountProducts");
        Map<Brand, Long> result = new HashMap<>();
        for (Object[] aRow : resultList)
            result.put((Brand) aRow[0], (Long) aRow[1]);

        return result;
    }

    @Override
    public List<Brand> getBrandByCountryId(Long countryId) {

        return (List<Brand>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Brand.getByCountry", "id", countryId);
    }

    @Override
    public Brand getBrandByName(String brandName) {

        return (Brand) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("name", brandName)).uniqueResult();
    }

    @Override
    public Integer deleteById(Long brandId) {

        return getSession()
                .getNamedQuery("Brand.deleteById")
                .setLong("id", brandId)
                .executeUpdate();
    }


}
