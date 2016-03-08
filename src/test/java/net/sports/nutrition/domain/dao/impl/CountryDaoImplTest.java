package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.dao.ICategoryDao;
import net.sports.nutrition.domain.dao.ICountryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.02.2016 21:08
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryDaoImplTest {

    @Autowired
    private ICountryDao countryDao;
    @Autowired
    private ICategoryDao categoryDao;

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Country> countries = countryDao.findAll();
        assertNotNull(countries);
        assertEquals(3, countries.size());
    }

    @Test
    @Rollback(true)
    public void testGetAmountProductsByCategory() throws Exception {
        Map<Country, Long> result = countryDao.getAmountProductsByCategory();

        assertNotNull(result);
        int i = 0;
        for (Map.Entry<Country, Long> item : result.entrySet()) {
            if ("Ukraine".equals(item.getKey().getName())) {
                assertEquals(new Long(1), item.getValue());
                i++;
            }else if ("China".equals(item.getKey().getName())) {
                assertEquals(new Long(1), item.getValue());
                i++;
            }else if ("Germany".equals(item.getKey().getName())) {
                assertEquals(new Long(2), item.getValue());
                i++;
            }
        }
        assertEquals(i, result.size());
    }
}