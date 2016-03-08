package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.dao.IProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 13:48
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private IProductDao productDao;
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private Product productToCheck;
    @Mock
    private Product product;

    @Test
    public void testCheckBeforeUpdateProduct() throws Exception {
        when(product.getId()).thenReturn(new Long(1));
        when(productToCheck.getId()).thenReturn(new Long(1));
        when(productToCheck.getArticleNumber()).thenReturn(new Long(5555));
        when(productDao.getProductByArticleNumber(new Long(5555))).thenReturn(product);

        Boolean resultTrue = productService.checkBeforeUpdateProduct(productToCheck);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(product.getId()).thenReturn(new Long(2));
        Boolean resultFalse = productService.checkBeforeUpdateProduct(productToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }

    @Test
    public void testProductIsExist() throws Exception {
        when(productDao.getProductByArticleNumber(new Long(5555))).thenReturn(product);
        when(product.getArticleNumber()).thenReturn(new Long(5555));
        Boolean resultTrue = productService.productIsExist(product);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(productDao.getProductByArticleNumber(new Long(5555))).thenReturn(null);
        Boolean resultFalse = productService.productIsExist(product);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }
}