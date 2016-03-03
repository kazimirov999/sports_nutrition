package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.services.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 02.03.2016 15:40
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTest {

    @Mock
    private PropertyController propertyController;
    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    View mockView;

    private MockMvc mockMvc;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).setSingleView(mockView).build();

        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testShowIndexPage() throws Exception {
        mockMvc.perform(get(ConstantsUri.INDEX))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.INDEX));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.INDEX));
    }

    @Test
    public void testShowMessage() throws Exception {
        mockMvc.perform(get(ConstantsUri.MESSAGE_SHOW))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.MESSAGE));

        mockMvc.perform(get(ConstantsUri.MESSAGE_SHOW + "?message=text"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("serviceMessage"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.MESSAGE));
    }
}