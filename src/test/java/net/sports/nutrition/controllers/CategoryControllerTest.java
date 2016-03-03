package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Category;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 02.03.2016 20:25
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    private ProductServiceImpl productService;
    @Mock
    View mockView;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;
    private Category category;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).setSingleView(mockView).build();
        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));

        category = new Category("Amino", "Amino category");
        category.setId(new Long(1));
        when(categoryService.getCategoryById(anyLong())).thenReturn(category);
    }

    @Test
    public void testAddCategoryShowForm() throws Exception {
        mockMvc.perform(get(ConstantsUri.CATEGORY_ADD))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("formCategoryBean"))
                .andExpect(view().name(ConstantsView.CATEGORY_ADD));
    }

    @Test
    public void testAddCategory() throws Exception {
        doNothing().when(categoryService).saveCategoryWithImage(any(Category.class), any(MultipartFile.class));
        when(categoryService.categoryIsExist(any(Category.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.CATEGORY_ADD)
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.success.add"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_ADD));

        mockMvc.perform(post(ConstantsUri.CATEGORY_ADD)//BindingResult has errors
                .param("category.name", "")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(view().name(ConstantsView.CATEGORY_ADD));

        when(categoryService.categoryIsExist(any(Category.class))).thenReturn(true);//category is exist
        mockMvc.perform(post(ConstantsUri.CATEGORY_ADD)
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "category.failure.put.is_exist"))
                .andExpect(view().name(ConstantsView.CATEGORY_ADD));

        doThrow(Exception.class).when(categoryService).saveCategoryWithImage(any(Category.class), any(MultipartFile.class));
        when(categoryService.categoryIsExist(any(Category.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.CATEGORY_ADD)
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "category.failure.add"))
                .andExpect(view().name(ConstantsView.CATEGORY_ADD));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        when(categoryService.deleteCategoryById(anyLong())).thenReturn(1);
        mockMvc.perform(get(ConstantsUri.CATEGORY_DELETE.replace("{id}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT));

        when(categoryService.deleteCategoryById(anyLong())).thenReturn(0);
        mockMvc.perform(get(ConstantsUri.CATEGORY_DELETE.replace("{id}", "1")))//
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.not.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT));

        when(categoryService.deleteCategoryById(anyLong())).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.CATEGORY_DELETE.replace("{id}", "1")))//
                .andExpect(status().isOk())
                .andExpect(flash().attribute("failureMessage", "category.failure.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT));
    }

    @Test
    public void testDeleteCategoryResult() throws Exception {
        mockMvc.perform(get(ConstantsUri.CATEGORY_DELETE_RESULT))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.CATEGORY_DELETE));
    }

    @Test
    public void testDeleteAllProductsByCategoryId() throws Exception {
        when(productService.deleteAllProductByCategoryId(anyLong())).thenReturn(1);
        mockMvc.perform(get(ConstantsUri.CATEGORY_DELETE_ALL_PRODUCT.replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.products.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT));
    }

    @Test
    public void testEditCategoryResult() throws Exception {
        mockMvc.perform(get(ConstantsUri.CATEGORY_EDIT_RESULT))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.CATEGORY_EDIT_RESULT));
    }

    @Test
    public void testShowEditCategory() throws Exception {
        mockMvc.perform(get(ConstantsUri.CATEGORY_SHOW_EDIT_FORM.replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("1", nullValue()))
                .andExpect(model().attributeExists("formCategoryBean"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.CATEGORY_EDIT));

        this.category.setImageByte("image".getBytes());
        mockMvc.perform(get(ConstantsUri.CATEGORY_SHOW_EDIT_FORM.replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("1", equalTo("image".getBytes())))
                .andExpect(model().attributeExists("formCategoryBean"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.CATEGORY_EDIT));
    }

    @Test
    public void testEditCategory() throws Exception {
        when(categoryService.checkBeforeUpdateCategory(any(Category.class))).thenReturn(true);
        when(categoryService.updateCategory(any(Category.class))).thenReturn(category);
        mockMvc.perform(post(ConstantsUri.CATEGORY_EDIT)
                .param("category.id", "1")
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.success.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_EDIT_RESULT));

        mockMvc.perform(post(ConstantsUri.CATEGORY_EDIT)//BindingResult has errors
                .param("category.name", "")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.CATEGORY_EDIT));

        when(categoryService.checkBeforeUpdateCategory(any(Category.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.CATEGORY_EDIT)//category is exist
                .param("category.id", "1")
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "category.failure.put.is_exist"))
                .andExpect(view().name(ConstantsView.CATEGORY_EDIT));

        when(categoryService.checkBeforeUpdateCategory(any(Category.class))).thenReturn(true);
        when(categoryService.updateCategory(any(Category.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.CATEGORY_EDIT)
                .param("category.id", "1")
                .param("category.name", "Amino")
                .param("category.description", "Amino category"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "category.edit.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.CATEGORY_EDIT_RESULT));
    }
}