package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.dao.ITasteDao;
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
 * Date: 28.02.2016 21:26
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TasteDaoImplTest {

    @Autowired
    private ITasteDao tasteDao;

    @Test
    @Rollback(true)
    public void testGetTasteByName() throws Exception {
        Taste taste = tasteDao.getTasteByName("Apple");
        assertNotNull(taste);
        assertEquals("Apple", taste.getName());
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        Taste taste = new Taste("Mango");
        Taste tasteSave = tasteDao.save(taste);
        assertNotNull(tasteSave);
        assertEquals("Mango", tasteSave.getName());
        assertNotNull(tasteDao.getTasteByName("Mango"));
    }

    @Test
    @Rollback(true)
    public void testEdit() throws Exception {
        Taste taste = tasteDao.getTasteByName("Malina");
        taste.setName("Test name");
        Taste tasteEdit = tasteDao.edit(taste);
        assertNotNull(tasteEdit);
        assertEquals("Test name", tasteEdit.getName());
        assertNotNull(tasteDao.getTasteByName("Test name"));
        assertNull(tasteDao.getTasteByName("Malina"));
    }

    @Test
    @Rollback(true)
    public void testDeleteWithConstraint() throws Exception {
        Taste taste = tasteDao.getTasteByName("Apple");
        Boolean result = tasteDao.delete(taste);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        Taste taste = tasteDao.getTasteByName("Malina");
        Boolean result = tasteDao.delete(taste);
        assertNotNull(result);
        assertTrue(result);
        assertNull(tasteDao.getTasteByName("Malina"));
    }

    @Test
    public void testGetAllTastesByCategoryId() throws Exception {
        List<Taste> tastes = tasteDao.getAllTastesByCategoryId(new Long(3));
        assertNotNull(tastes);
        assertEquals(1, tastes.size());
        assertEquals("Banana", tastes.get(0).getName());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteTasteByIdWithConstraint() throws Exception {
        Integer result = tasteDao.deleteTasteById(new Long(1));
    }

    @Test
    @Rollback(true)
    public void testDeleteTasteById() throws Exception {
        Integer result = tasteDao.deleteTasteById(new Long(20));
        assertNotNull(result);
        assertEquals(1, result.intValue());
        assertNull(tasteDao.getTasteByName("Malina"));
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Taste> tastes = tasteDao.findAll();
        assertNotNull(tastes);
        assertEquals(4, tastes.size());
    }
}