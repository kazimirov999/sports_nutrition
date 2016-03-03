package net.sports.nutrition.domain.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 22:57
 */
public class ProductTest {

    Product product;

    @Before
    public void init() {
        product = new Product();
    }

    @Test
    public void testGetRealPrice() throws Exception {
        BigDecimal zeroDec = new BigDecimal(0).setScale(2);
        product.setPrice(zeroDec);
        Discount discount = Mockito.mock(Discount.class);
        when(discount.getSize()).thenReturn(zeroDec);

        assertEquals(zeroDec, product.getRealPrice());

        product.setDiscount(discount);
        assertEquals(zeroDec, product.getRealPrice());

        product.setPrice(new BigDecimal(100));
        assertEquals(new BigDecimal(100).setScale(2), product.getRealPrice());

        when(discount.getSize()).thenReturn(new BigDecimal(55.55));
        product.setPrice(zeroDec);
        assertEquals(zeroDec, product.getRealPrice());

        product.setPrice(new BigDecimal(88.50));
        assertEquals(new BigDecimal(39.34).setScale(2, BigDecimal.ROUND_HALF_EVEN), product.getRealPrice());
    }
}