package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.repositories.ITasteRepository;
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
public class TasteRepositoryImplTest {

    @Autowired
    private ITasteRepository tasteRepository;

    @Test
    @Rollback(true)
    public void testGetTasteByName() throws Exception {
        Taste taste = tasteRepository.getTasteByName("Apple");
        assertNotNull(taste);
        assertEquals("Apple", taste.getName());
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        Taste taste = new Taste("Mango");
        Taste tasteSave = tasteRepository.save(taste);
        assertNotNull(tasteSave);
        assertEquals("Mango", tasteSave.getName());
        assertNotNull(tasteRepository.getTasteByName("Mango"));
    }

    @Test
    @Rollback(true)
    public void testEdit() throws Exception {
        Taste taste = tasteRepository.getTasteByName("Malina");
        taste.setName("Test name");
        Taste tasteEdit = tasteRepository.edit(taste);
        assertNotNull(tasteEdit);
        assertEquals("Test name", tasteEdit.getName());
        assertNotNull(tasteRepository.getTasteByName("Test name"));
        assertNull(tasteRepository.getTasteByName("Malina"));
    }

    @Test
    @Rollback(true)
    public void testDeleteWithConstraint() throws Exception {
        Taste taste = tasteRepository.getTasteByName("Apple");
        Boolean result = tasteRepository.delete(taste);
        assertNotNull(result);
        assertFalse(result);
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        Taste taste = tasteRepository.getTasteByName("Malina");
        Boolean result = tasteRepository.delete(taste);
        assertNotNull(result);
        assertTrue(result);
        assertNull(tasteRepository.getTasteByName("Malina"));
    }

    @Test
    public void testGetAllTastesByCategoryId() throws Exception {
        List<Taste> tastes = tasteRepository.getAllTastesByCategoryId(new Long(3));
        assertNotNull(tastes);
        assertEquals(1, tastes.size());
        assertEquals("Banana", tastes.get(0).getName());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void testDeleteTasteByIdWithConstraint() throws Exception {
        Integer result = tasteRepository.deleteTasteById(new Long(1));
    }

    @Test
    @Rollback(true)
    public void testDeleteTasteById() throws Exception {
        Integer result = tasteRepository.deleteTasteById(new Long(20));
        assertNotNull(result);
        assertEquals(1, result.intValue());
        assertNull(tasteRepository.getTasteByName("Malina"));
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Taste> tastes = tasteRepository.findAll();
        assertNotNull(tastes);
        assertEquals(4, tastes.size());
    }
}