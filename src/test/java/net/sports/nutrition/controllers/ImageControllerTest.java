package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.services.impl.CategoryServiceImpl;
import net.sports.nutrition.services.impl.ProductServiceImpl;
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

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 02.03.2016 19:00
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ImageControllerTest {

    @Mock
    private ProductServiceImpl productService;
    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    View mockView;

    private MockMvc mockMvc;
    Category category = new Category();
    Product product = new Product();
    @InjectMocks
    private ImageController imageController;

    @Before
    public void init() {
        byte[]testArray = "123".getBytes();
        category.setImageByte(testArray);
        product.setImageByte(testArray);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).setSingleView(mockView).build();

        System.out.println(productService);
        when(categoryService.getCategoryById(anyLong())).thenReturn(category);
        when(productService.getProductById(anyLong())).thenReturn(product);
    }

    @Test
    public void testDownloadCategoryPhoto() throws Exception {
        mockMvc.perform(get(ConstantsUri.CATEGORY_IMG.replace("{categoryId}", "1") ))
                .andExpect(status().isOk())
                .andExpect(content().bytes("123".getBytes()));
    }

    @Test
    public void testDownloadProductPhoto() throws Exception {
        mockMvc.perform(get(ConstantsUri.PRODUCT_IMG.replace("{productId}", "1") ))
                .andExpect(status().isOk())
                .andExpect(content().bytes("123".getBytes()));
    }
}