package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.dao.IBrandDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.02.2016 15:08
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BrandDaoImplTest {

    @Autowired
    private IBrandDao brandDao;

    @Test
    @Rollback(true)
    public void testGetBrandByName() throws Exception {
        Brand brand = brandDao.getBrandByName("ActivLab");
        assertNotNull(brand);
        assertEquals("ActivLab", brand.getName());
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Brand> brands = brandDao.findAll();
        assertNotNull(brands);
        assertEquals(6, brands.size());
    }

    @Test
    @Rollback(true)
    public void testGetBrandsByCategoryId() throws Exception {
        List<Brand> brands = brandDao.getBrandsByCategoryId(new Long(3));
        assertNotNull(brands);
        assertEquals(2, brands.size());
    }

    @Test
    @Rollback(true)
    public void testGetAmountProductByBrand() throws Exception {
        Map<Brand, Long> result = brandDao.getAmountProductsByBrand();
        assertNotNull(result);
        int i = 0;
        for (Map.Entry<Brand, Long> item : result.entrySet()) {
            if ("ActivLab".equals(item.getKey().getName())) {
                assertEquals(new Long(1), item.getValue());
                i++;
            }else if ("Allmax Nutrition".equals(item.getKey().getName())) {
                assertEquals(new Long(2), item.getValue());
                i++;
            }else if ("Ultimate Nutrition".equals(item.getKey().getName())) {
                assertEquals(new Long(1), item.getValue());
                i++;
            }
        }
        assertEquals(i, result.size());
    }

    @Test
    @Rollback(true)
    public void testGetBrandsByCountryId() throws Exception {
        List<Brand> brands = brandDao.getBrandsByCountryId(new Long(1));
        assertNotNull(brands);
        assertEquals(3, brands.size());
        assertEquals("ActivLab", brands.get(0).getName());
        assertEquals("USP labs", brands.get(1).getName());
        assertEquals("Universal Nutrition", brands.get(2).getName());
    }

    @Test
    @Rollback(true)
    public void testDeleteById() throws Exception {
        Integer result = brandDao.deleteById(new Long(25));
        assertEquals(1, result.intValue());
        assertNull(brandDao.findById(new Long(25)));
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteByIdWidthConstraint() throws Exception {
        Integer result = brandDao.deleteById(new Long(1));
    }

    @Test
    @Rollback(true)
    public void testDelete() {
        Brand brand = brandDao.findById(new Long(25));
        Boolean result = brandDao.delete(brand);
        assertTrue(result);
        assertNull(brandDao.findById(new Long(25)));
    }

    @Test
    @Rollback(true)
    public void testDeleteWidthConstraint() {
        Brand brand = brandDao.findById(new Long(1));
        Boolean result = brandDao.delete(brand);
        assertNotNull(result);
        assertFalse(result);
        assertNull(brandDao.findById(new Long(1)));
    }

    @Test
    @Rollback(true)
    public void testFindById() {
        Brand brand = brandDao.findById(new Long(1));
        assertNotNull(brand);
        assertEquals("ActivLab", brand.getName());
        assertNull(brandDao.findById(new Long(100)));
    }

    @Test
    @Rollback(true)
    public void testEdit() {
        Brand brand = brandDao.getBrandByName("ActivLab");
        brand.setName("Test name");
        assertEquals("Test name", brandDao.edit(brand).getName());
    }
}