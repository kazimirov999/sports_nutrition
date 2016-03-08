package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.dao.IDiscountDao;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.02.2016 1:51
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountDaoImplTest {

    @Autowired
    private IDiscountDao discountDao;

    @Test
    @Rollback(true)
    public void testGetDiscountByName() throws Exception {
        Discount discount = discountDao.getDiscountByName("Spring");
        assertNotNull(discount);
        assertEquals("Spring", discount.getName());
    }

    @Test
    @Rollback(true)
    public void testSave() {
        discountDao.save(new Discount("Winter", new BigDecimal(40), DateTime.parse("2016-02-25"), null));
        Discount discount = discountDao.getDiscountByName("Winter");
        assertNotNull(discount);
        assertEquals("Winter", discount.getName());
        assertEquals(new BigDecimal(40), discount.getSize());
    }

    @Test
    @Rollback(true)
    public void testGetDiscountBySize() throws Exception {
        Discount discount = discountDao.getDiscountBySize(new BigDecimal(25));
        assertNotNull(discount);
        assertEquals(new BigDecimal(25).setScale(2), discount.getSize());
    }

    @Test
    @Rollback(true)
    public void testDeleteDiscountById() throws Exception {
        Boolean result = discountDao.deleteDiscountById(new Long(1));
        assertTrue(result);
        assertNull(discountDao.getDiscountByName("Summer"));
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
       List<Discount> discounts = discountDao.findAll();
        assertNotNull(discounts);
        assertEquals(2, discounts.size());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetDiscountsByCategoryId() throws Exception {
        List<Discount> discounts = discountDao.getDiscountsByCategoryId(new Long(1));
        assertNotNull(discounts);
        assertEquals(1, discounts.size());
        assertEquals("Summer", discounts.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindById() throws Exception{
        Discount discount = discountDao.findById(new Long(1));
        assertEquals("Summer", discount.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testEdit() throws Exception{
        Discount discount = discountDao.getDiscountByName("Summer");
        discount.setName("Test name");
        Discount discountEdit = discountDao.edit(discount);
        assertNotNull(discountEdit);
        assertEquals("Test name", discountEdit.getName());
    }
}