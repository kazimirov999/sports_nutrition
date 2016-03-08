package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.dao.ICartDao;
import net.sports.nutrition.domain.dao.ICategoryDao;
import net.sports.nutrition.domain.dao.IProductDao;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.02.2016 22:27
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoImplTest {

    @Autowired
    private IProductDao productDao;
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private ICategoryDao categoryDao;

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Category> categories = categoryDao.findAll();
        assertNotNull(categories);
        assertEquals(3, categories.size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteByIdWithConstraint() throws Exception {
        Integer result = categoryDao.deleteById(new Long(3));
    }

    @Test
    @Rollback(true)
    public void testSave() {
        Category category = new Category();
        category.setName("Test name");
        category.setDescription("Test decscription");
        Category categorySave = categoryDao.save(category);
        assertNotNull(categorySave);
        assertEquals("Test name", categorySave.getName());
        assertNotNull(categoryDao.getCategoryByName("Test name"));
    }

    @Test
    @Rollback(true)
    public void testEdit(){
        Category category = categoryDao.getCategoryByName("Geiners");
        category.setName("Test name");
        Category categoryEdit = categoryDao.edit(category);
        assertNotNull(categoryEdit);
        assertEquals("Test name", categoryEdit.getName());
        assertNotNull(categoryDao.getCategoryByName("Test name"));
    }

    @Test
    @Rollback(true)
    public void testDeleteById() throws Exception {
        Cart cart = cartDao.findById(new Long(2));
        cartDao.delete(cart);
        productDao.deleteProductById(new Long(3));
        productDao.deleteProductById(new Long(4));

        Integer result = categoryDao.deleteById(new Long(3));
        assertNotNull(result);
        assertEquals(1, result.intValue());
    }

    @Test
    @Rollback(true)
    public void testDeleteWithConstraint() throws Exception {
        Category category = categoryDao.getCategoryByName("Geiners");
        Boolean result = categoryDao.delete(category);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        Cart cart = cartDao.findById(new Long(2));
        cartDao.delete(cart);
        productDao.deleteProductById(new Long(3));
        productDao.deleteProductById(new Long(4));

        Category category = categoryDao.getCategoryByName("Geiners");
        Boolean result = categoryDao.delete(category);
        assertNotNull(result);
        assertTrue(result);
        assertNull(categoryDao.getCategoryByName("Geiners"));
    }

    @Test
    @Rollback(true)
    public void testGetCategoryByName() throws Exception {
        Category category = categoryDao.getCategoryByName("Geiners");
        assertNotNull(category);
        assertEquals("Geiners", category.getName());
    }
}