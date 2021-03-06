package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.*;
import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.domain.dao.*;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.02.2016 10:52
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoImplTest {

    @Autowired
    private IProductDao productDao;
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private ITasteDao tasteDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IBrandDao brandDao;

    @Test
    @Rollback(true)
    public void testGetProductByName() throws Exception {
        Product product = productDao.getProductByName("Super Amino");
        assertNotNull(product);
        assertEquals("Super Amino", product.getName());
    }

    @Test
    @Rollback(true)
    public void testFindById() throws Exception {
        Product product = productDao.findById(new Long(1));
        assertNotNull(product);
        assertEquals("Super Amino", product.getName());
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        Product product = new Product();
        Taste taste = tasteDao.getTasteByName("Malina");
        List<Taste> tastes = new ArrayList<>();
        tastes.add(taste);
        product.setName("Test name");
        product.setArticleNumber(new Long(45464465));
        product.setTasteList(tastes);
        product.setPrice(new BigDecimal(100));
        product.setQuantityInPackage("300g");
        product.setStockAmount(30);
        product.setGender(Gender.MAN);
        product.setForm(Form.CAPSULE);
        product.setCategory(categoryDao.getCategoryByName("Amino"));
        product.setBrand(brandDao.getBrandByName("ActivLab"));

        Product productSave = productDao.save(product);
        assertNotNull(productSave);
        assertNotNull(productSave.getBrand());
        assertNotNull(productSave.getCategory());
        assertEquals("Test name", productSave.getName());
        assertEquals("ActivLab", productSave.getBrand().getName());
        assertEquals("Malina", productSave.getTasteList().iterator().next().getName());
        assertEquals("Amino", productSave.getCategory().getName());
    }

    @Test
    @Rollback(true)
    public void testEdit() throws Exception {
        Product product = productDao.getProductByName("Super Amino");
        Taste taste = tasteDao.getTasteByName("Malina");
        Brand brand = brandDao.getBrandByName("NotLabs");
        Category category = categoryDao.getCategoryByName("Geiners");
        product.setName("Test name");
        product.setCategory(category);
        product.setBrand(brand);
        product.getTasteList().clear();
        product.getTasteList().add(taste);

        Product productEdit = productDao.edit(product);
        assertNotNull(productEdit);
        assertNotNull(productEdit.getTasteList());
        assertNotNull(productEdit.getBrand());
        assertNotNull(productEdit.getCategory());
        assertEquals("Test name", productEdit.getName());
        assertEquals("Malina", productEdit.getTasteList().iterator().next().getName());
        assertEquals("Geiners", productEdit.getCategory().getName());
    }

    @Test
    @Rollback(true)
    public void testGetProductByArticleNumber() throws Exception {
        Product product = productDao.getProductByArticleNumber(new Long(5520193));
        assertNotNull(product);
        assertEquals(new Long(5520193), product.getArticleNumber());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)//product can not delete, CART_ITEMS FOREIGN KEY(PRODUCT_ID) REFERENCES PUBLIC.PRODUCTS(ID)
    public void testDeleteProductByIdWidthConstraint() throws Exception {
        Integer result = productDao.deleteProductById(new Long(1));
    }

    @Test
    @Rollback(true)
    public void testDeleteProductById() throws Exception {
        Integer result = productDao.deleteProductById(new Long(4));
        assertNotNull(result);
        assertEquals(1, result.intValue());
        assertNull(productDao.findById(new Long(4)));
    }

    @Test
    @Rollback(true)
    public void testDeleteWithConstraint() throws Exception {
        Product product = productDao.findById(new Long(3));
        Boolean result = productDao.delete(product);
        assertNotNull(result);
        assertFalse(result);
        assertNull(productDao.findById(new Long(3)));
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        Product product = productDao.findById(new Long(4));
        Boolean result = productDao.delete(product);
        assertNotNull(result);
        assertTrue(result);
        assertNull(productDao.findById(new Long(4)));
    }

    @Test
    @Rollback(true)//product can not delete, CART_ITEMS FOREIGN KEY(PRODUCT_ID) REFERENCES PUBLIC.PRODUCTS(ID)
    public void testGetProductsAmountByCategoryId() throws Exception {
        Long count = productDao.getProductsAmountByCategoryId(new Long(3));
        assertNotNull(count);
        assertEquals(new Long(2), count);
    }

    @Test
    @Rollback(true)
    public void testCountProductsByProperty() throws Exception {
        Set<Long> brandIdList = new HashSet<>();
        brandIdList.add(new Long(1));
        brandIdList.add(new Long(2));
        brandIdList.add(new Long(3));
        Set<Gender> genderList = new HashSet<>();
        genderList.add(Gender.UNISEX);
        genderList.add(Gender.WOMAN);
        genderList.add(Gender.MAN);
        FormFilterBean filterBean = new FormFilterBean();
        filterBean.setBrandIdList(brandIdList);
        filterBean.setGenderList(genderList);

        List<Brand> propertyList = brandDao.getBrandsByCategoryId(new Long(1));
        Map<Brand, Long> result = productDao.countProductsByProperty("brand", new Long(1), propertyList, filterBean);
        assertNotNull(result);
        assertEquals("ActivLab", result.entrySet().iterator().next().getKey().getName());
        assertEquals(1, result.entrySet().iterator().next().getValue().intValue());
    }

    @Test
    @Rollback(true)
    public void testGetProductByCategoryId() throws Exception {
        List<Product> products = productDao.getProductsByCategoryId(new Long(3));
        assertNotNull(products);
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteAllProductsByCategoryId() throws Exception {
        Cart cart = cartDao.findById(new Long(3));
        cartDao.delete(cart);
        Integer result = productDao.deleteProductById(new Long(1));
        assertNotNull(result);
        assertEquals(1, result.intValue());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteAllProductsByCategoryIdWidthConstraint() throws Exception {
        Integer result = productDao.deleteProductById(new Long(1));
    }

    @Test
    @Rollback(true)
    public void testCountProductsByTasteAndCriteria() throws Exception {
        Set<Long> brandIdList = new HashSet<>();
        brandIdList.add(new Long(1));
        brandIdList.add(new Long(2));
        brandIdList.add(new Long(3));
        Set<Gender> genderList = new HashSet<>();
        genderList.add(Gender.UNISEX);
        genderList.add(Gender.WOMAN);
        genderList.add(Gender.MAN);
        FormFilterBean filterBean = new FormFilterBean();
        filterBean.setBrandIdList(brandIdList);
        filterBean.setGenderList(genderList);
        List<Taste> tasteList = tasteDao.getAllTastesByCategoryId(new Long(1));
        Map<Taste, Long> result = productDao.countProductsByTasteAndCriteria(new Long(1), tasteList, filterBean);
        assertNotNull(result);
        assertEquals("Apple", result.entrySet().iterator().next().getKey().getName());
        assertEquals(1, result.entrySet().iterator().next().getValue().intValue());
    }

    @Test
    @Rollback(true)
    public void testGetProductIdsByTaste() throws Exception {
        Taste taste = tasteDao.getTasteByName("Apple");
        List<Long> productIds = productDao.getProductIdsByTaste(new Long(1), taste);
        assertNotNull(productIds);
        assertEquals(new Long(1), productIds.get(0));
    }

    @Test
    @Rollback(true)
    public void testGetProductsByCriteria() throws Exception {
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

        List<Product> products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.NAME_ASC, null, null);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        brandIdList.add(new Long(2));
        brandIdList.add(new Long(3));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.NAME_ASC, null, null);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        brandIdList.clear();
        tastesId.add(new Long(2));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.NAME_DESC, null, null);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Geiner", products.get(0).getName());

        products = null;
        tastesId.clear();
        discountIdList.add(new Long(1));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.NAME_DESC, null, null);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Geiner", products.get(0).getName());

        products = null;
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_ASC, null, null);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = null;
        genderList.add(Gender.UNISEX);
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_DESC, null, null);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super MEGAgeiner", products.get(0).getName());
        assertEquals("Super Geiner", products.get(1).getName());

        products = null;
        formList.add(Form.TABLETS);
        genderList.add(Gender.UNISEX);
        discountIdList.add(new Long(1));
        discountIdList.add(new Long(2));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_DESC, null, null);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super MEGAgeiner", products.get(0).getName());

        products = null;
        formList.clear();
        genderList.clear();
        genderList.clear();
        discountIdList.clear();
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_ASC, 0, 2);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_ASC, 0, 1);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Geiner", products.get(0).getName());

        products = productDao
                .getProductsByCriteria(new Long(1), filterBean, SortType.PRICE_ASC, 0, 10);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Super Amino", products.get(0).getName());

        filterBean.setLowPrice(new BigDecimal(500));
        filterBean.setHighPrice(new BigDecimal(700));
        products = productDao
                .getProductsByCriteria(new Long(3), filterBean, SortType.PRICE_ASC, 0, 10);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Super Geiner", products.get(0).getName());
        assertEquals("Super MEGAgeiner", products.get(1).getName());

    }
}