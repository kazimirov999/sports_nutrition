package net.sports.nutrition.criteria.filter.impl;

import net.sports.nutrition.criteria.filter.IMainCriteriaFilter;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.02.2016 21:03
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainCriteriaFilterImplTest {

    @Autowired
    private IMainCriteriaFilter mainCriteriaFilter;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Test
    public void testMainFilter() throws Exception {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Set<Long> brandIdList = new HashSet<>();
        Set<Long> tastesId = new HashSet<>();
        Set<Long> discountIdList = new HashSet<>();
        Set<Form> formList = new HashSet<>();
        Set<Gender> genderList = new HashSet<>();

        FormFilterBean filterBean = new FormFilterBean();
        filterBean.setBrandIdList(brandIdList);
        filterBean.setTasteIdList(tastesId);
        filterBean.setDiscountIdList(discountIdList);
        filterBean.setFormList(formList);
        filterBean.setGenderList(genderList);

        List<Product> products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        brandIdList.add(new Long(2));
        brandIdList.add(new Long(3));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        brandIdList.clear();
        tastesId.add(new Long(2));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Geiner", products.get(0).getName());

        products = null;
        tastesId.clear();
        discountIdList.add(new Long(1));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Geiner", products.get(0).getName());

        products = null;
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        genderList.add(Gender.UNISEX);
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        formList.add(Form.TABLETS);
        genderList.add(Gender.UNISEX);
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super MEGAgeiner", products.get(0).getName());

        products = null;
        formList.clear();
        genderList.clear();
        genderList.clear();
        discountIdList.clear();
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        filterBean.setLowPrice(new BigDecimal(500));
        filterBean.setHighPrice(new BigDecimal(700));
        products = mainCriteriaFilter.mainFilter(new Long(3), session, filterBean).list();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());
    }
}