package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.repositories.impl.CategoryRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 12:27
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepositoryImpl categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private Category categoryToCheck;
    @Mock
    private Category category;

    @Test
    public void testCheckBeforeUpdateCategory() throws Exception {
        when(category.getId()).thenReturn(new Long(1));
        when(categoryToCheck.getId()).thenReturn(new Long(1));
        when(categoryToCheck.getName()).thenReturn("Amino");
        when(categoryRepository.getCategoryByName("Amino")).thenReturn(category);

        Boolean resultTrue = categoryService.checkBeforeUpdateCategory(categoryToCheck);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(category.getId()).thenReturn(new Long(2));
        Boolean resultFalse = categoryService.checkBeforeUpdateCategory(categoryToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }

    @Test
    public void testCategoryIsExist() throws Exception {
        when(category.getName()).thenReturn("Amino");
        when(categoryRepository.getCategoryByName("Amino")).thenReturn(category);

        Boolean resultTrue = categoryService.categoryIsExist(category);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(categoryRepository.getCategoryByName("Amino")).thenReturn(null);
        Boolean resultFalse = categoryService.categoryIsExist(category);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }
}