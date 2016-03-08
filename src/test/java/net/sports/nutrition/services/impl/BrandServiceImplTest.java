package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.dao.IBrandDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 11:16
 */
@RunWith(MockitoJUnitRunner.class)
public class BrandServiceImplTest {

    @Mock
    private IBrandDao brandDao;
    @InjectMocks
    private BrandServiceImpl brandService;
    @Mock
    private Brand brandToCheck;
    @Mock
    private Brand brand;

    @Test
    public void testCheckBeforeUpdateBrand() throws Exception {
        when(brand.getId()).thenReturn(new Long(1));
        when(brandToCheck.getId()).thenReturn(new Long(1));
        when(brandToCheck.getName()).thenReturn("Olimp");
        when(brandDao.getBrandByName("Olimp")).thenReturn(brand);

        Boolean resultTrue = brandService.checkBeforeUpdateBrand(brandToCheck);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(brand.getId()).thenReturn(new Long(2));
        Boolean resultFalse = brandService.checkBeforeUpdateBrand(brandToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }

    @Test
    public void testBrandIsExist() {
        when(brand.getName()).thenReturn("Olimp");
        when(brandDao.getBrandByName("Olimp")).thenReturn(brand);
        Boolean resultTrue = brandService.brandIsExist(brand);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(brandDao.getBrandByName("Olimp")).thenReturn(null);
        Boolean resultFalse = brandService.brandIsExist(brand);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }
}