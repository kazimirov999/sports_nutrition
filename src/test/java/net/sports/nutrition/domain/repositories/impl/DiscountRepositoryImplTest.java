package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.repositories.IDiscountRepository;
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
public class DiscountRepositoryImplTest {

    @Autowired
    private IDiscountRepository discountRepository;

    @Test
    @Rollback(true)
    public void testGetDiscountByName() throws Exception {
        Discount discount = discountRepository.getDiscountByName("Spring");
        assertNotNull(discount);
        assertEquals("Spring", discount.getName());
    }

    @Test
    @Rollback(true)
    public void testSave() {
        discountRepository.save(new Discount("Winter", new BigDecimal(40), DateTime.parse("2016-02-25"), null));
        Discount discount = discountRepository.getDiscountByName("Winter");
        assertNotNull(discount);
        assertEquals("Winter", discount.getName());
        assertEquals(new BigDecimal(40), discount.getSize());
    }

    @Test
    @Rollback(true)
    public void testGetDiscountBySize() throws Exception {
        Discount discount = discountRepository.getDiscountBySize(new BigDecimal(25));
        assertNotNull(discount);
        assertEquals(new BigDecimal(25).setScale(2), discount.getSize());
    }

    @Test
    @Rollback(true)
    public void testDeleteDiscountById() throws Exception {
        Boolean result = discountRepository.deleteDiscountById(new Long(1));
        assertTrue(result);
        assertNull(discountRepository.getDiscountByName("Summer"));
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
       List<Discount> discounts = discountRepository.findAll();
        assertNotNull(discounts);
        assertEquals(2, discounts.size());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetDiscountsByCategoryId() throws Exception {
        List<Discount> discounts = discountRepository.getDiscountsByCategoryId(new Long(1));
        assertNotNull(discounts);
        assertEquals(1, discounts.size());
        assertEquals("Summer", discounts.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindById() throws Exception{
        Discount discount = discountRepository.findById(new Long(1));
        assertEquals("Summer", discount.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testEdit() throws Exception{
        Discount discount = discountRepository.getDiscountByName("Summer");
        discount.setName("Test name");
        Discount discountEdit = discountRepository.edit(discount);
        assertNotNull(discountEdit);
        assertEquals("Test name", discountEdit.getName());
    }
}