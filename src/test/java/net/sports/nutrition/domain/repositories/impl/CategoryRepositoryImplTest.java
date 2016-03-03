package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.repositories.ICartRepository;
import net.sports.nutrition.domain.repositories.ICategoryRepository;
import net.sports.nutrition.domain.repositories.IProductRepository;
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
public class CategoryRepositoryImplTest {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertEquals(3, categories.size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteByIdWithConstraint() throws Exception {
        Integer result = categoryRepository.deleteById(new Long(3));
    }

    @Test
    @Rollback(true)
    public void testSave() {
        Category category = new Category();
        category.setName("Test name");
        category.setDescription("Test decscription");
        Category categorySave = categoryRepository.save(category);
        assertNotNull(categorySave);
        assertEquals("Test name", categorySave.getName());
        assertNotNull(categoryRepository.getCategoryByName("Test name"));
    }

    @Test
    @Rollback(true)
    public void testEdit(){
        Category category = categoryRepository.getCategoryByName("Geiners");
        category.setName("Test name");
        Category categoryEdit = categoryRepository.edit(category);
        assertNotNull(categoryEdit);
        assertEquals("Test name", categoryEdit.getName());
        assertNotNull(categoryRepository.getCategoryByName("Test name"));
    }

    @Test
    @Rollback(true)
    public void testDeleteById() throws Exception {
        Cart cart = cartRepository.findById(new Long(2));
        cartRepository.delete(cart);
        productRepository.deleteProductById(new Long(3));
        productRepository.deleteProductById(new Long(4));

        Integer result = categoryRepository.deleteById(new Long(3));
        assertNotNull(result);
        assertEquals(1, result.intValue());
    }

    @Test
    @Rollback(true)
    public void testDeleteWithConstraint() throws Exception {
        Category category = categoryRepository.getCategoryByName("Geiners");
        Boolean result = categoryRepository.delete(category);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        Cart cart = cartRepository.findById(new Long(2));
        cartRepository.delete(cart);
        productRepository.deleteProductById(new Long(3));
        productRepository.deleteProductById(new Long(4));

        Category category = categoryRepository.getCategoryByName("Geiners");
        Boolean result = categoryRepository.delete(category);
        assertNotNull(result);
        assertTrue(result);
        assertNull(categoryRepository.getCategoryByName("Geiners"));
    }

    @Test
    @Rollback(true)
    public void testGetCategoryByName() throws Exception {
        Category category = categoryRepository.getCategoryByName("Geiners");
        assertNotNull(category);
        assertEquals("Geiners", category.getName());
    }
}