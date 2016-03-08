package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.dao.IBrandDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Brand Data Access Object is the class providing
 * access to brand and brand type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Long> implements IBrandDao {

    public BrandDaoImpl() {
        super(Brand.class);
    }

    @Override
    public List<Brand> findAll() {

        return getSession().createCriteria(getType()).list();
    }

    @Override
    public List<Brand> getBrandsByCategoryId(Long categoryId) {

        return (List<Brand>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Brand.getAllByCategoryIdSortByName", "id", categoryId);
    }

    @Override
    public Map<Brand, Long> getAmountProductsByBrand() {
        List<Object[]> resultList = (List<Object[]>) getHibernateTemplate().findByNamedQuery("Brand.getCountProducts");
        Map<Brand, Long> result = new HashMap<>();
        for (Object[] aRow : resultList)
            result.put((Brand) aRow[0], (Long) aRow[1]);

        return result;
    }

    @Override
    public List<Brand> getBrandsByCountryId(Long countryId) {

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
