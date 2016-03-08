package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.dao.IDiscountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 12:33
 */
@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceImplTest {

    @Mock
    private IDiscountDao discountDao;
    @InjectMocks
    private DiscountServiceImpl discountService;
    @Mock
    private Discount discountToCheck;
    @Mock
    private Discount discount;

    @Test
    public void testCheckBeforeUpdateDiscount()  throws Exception {
        when(discount.getId()).thenReturn(new Long(1));
        when(discount.getSize()).thenReturn(new BigDecimal(20));

        when(discountToCheck.getId()).thenReturn(new Long(1));
        when(discountToCheck.getName()).thenReturn("Summer");
        when(discountToCheck.getSize()).thenReturn(new BigDecimal(20));
        when(discountDao.getDiscountByName("Summer")).thenReturn(discount);
        when(discountDao.getDiscountBySize(new BigDecimal(20))).thenReturn(discount);

        Boolean resultTrue = discountService.checkBeforeUpdateDiscount(discountToCheck);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(discount.getId()).thenReturn(new Long(2));
        Boolean resultFalse = discountService.checkBeforeUpdateDiscount(discountToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);

        Discount discount = Mockito.mock(Discount.class);
        when(discount.getId()).thenReturn(new Long(2));
        when(discountDao.getDiscountBySize(new BigDecimal(30))).thenReturn(discount);
        when(discountToCheck.getSize()).thenReturn(new BigDecimal(30));
        when(discountDao.getDiscountByName("Summer")).thenReturn(discount);
        Boolean result = discountService.checkBeforeUpdateDiscount(discountToCheck);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    public void testDiscountIsExist() throws Exception {
        when(discount.getName()).thenReturn("Summer");
        when(discount.getSize()).thenReturn(new BigDecimal(30));
        when(discountDao.getDiscountBySize(new BigDecimal(30))).thenReturn(discount);
        when(discountDao.getDiscountByName("Summer")).thenReturn(discount);

        Boolean resultTrue = discountService.discountIsExist(discount);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(discountDao.getDiscountBySize(new BigDecimal(30))).thenReturn(null);
        when(discountDao.getDiscountByName("Summer")).thenReturn(null);
        Boolean resultFalse = discountService.discountIsExist(discountToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }
}