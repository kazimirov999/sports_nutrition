package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.repositories.impl.TasteRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 15:16
 */
@RunWith(MockitoJUnitRunner.class)
public class TasteServiceImplTest {

    @Mock
    private TasteRepositoryImpl tasteRepository;
    @InjectMocks
    private TasteServiceImpl tasteService;
    @Mock
    private Taste tasteToCheck;
    @Mock
    private Taste taste;

    @Test
    public void testCheckBeforeUpdateTaste() throws Exception {
        when(taste.getId()).thenReturn(new Long(1));
        when(tasteToCheck.getId()).thenReturn(new Long(1));
        when(tasteToCheck.getName()).thenReturn("Chocolate");
        when(tasteRepository.getTasteByName("Chocolate")).thenReturn(taste);

        Boolean resultTrue = tasteService.checkBeforeUpdateTaste(tasteToCheck);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(taste.getId()).thenReturn(new Long(2));
        Boolean resultFalse = tasteService.checkBeforeUpdateTaste(tasteToCheck);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }

    @Test
    public void testTasteIsExist() throws Exception {
        when(taste.getName()).thenReturn("Chocolate");
        when(tasteRepository.getTasteByName("Chocolate")).thenReturn(taste);
        Boolean resultTrue = tasteService.tasteIsExist(taste);
        assertNotNull(resultTrue);
        assertTrue(resultTrue);

        when(tasteRepository.getTasteByName("Chocolate")).thenReturn(null);
        Boolean resultFalse = tasteService.tasteIsExist(taste);
        assertNotNull(resultFalse);
        assertFalse(resultFalse);
    }
}