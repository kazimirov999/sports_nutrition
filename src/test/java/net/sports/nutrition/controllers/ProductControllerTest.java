package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;
import net.sports.nutrition.form.beans.FormSortedBean;
import net.sports.nutrition.services.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.03.2016 10:15
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    private FormServiceImpl formService;
    @Mock
    private BrandServiceImpl brandService;
    @Mock
    private DiscountServiceImpl discountService;
    @Mock
    private MessageSource messageSource;
    @Mock
    private ProductServiceImpl productService;
    @Spy
    private FormSortedBean formSortedBean;

    @Mock
    View mockView;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private Product product;

    @Before
    public void init() {
        this.product = new Product();
        product.setPrice(new BigDecimal(300));
        product.setName("Geiner");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setSingleView(mockView).build();

        when(categoryService.getCategoryById(anyLong())).thenReturn(new Category("Anino", "Amino category"));
        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testProductsFilterFormRedirect() throws Exception {
        mockMvc.perform(post(ConstantsUri.PRODUCT_SHOW_ALL_WITH_PAGE.replace("{pageNumber}", "1").replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("formFilterBean1", notNullValue()))
                .andExpect(request().sessionAttribute("productList", nullValue()))
                .andExpect(view().name("redirect:" + ConstantsUri.PRODUCT_SHOW_ALL_FIRST_PAGE));
    }

    @Test
    public void testProductsRedirect() throws Exception {
        mockMvc.perform(get(ConstantsUri.PRODUCT_SHOW_ALL.replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("formFilterBean1", nullValue()))
                .andExpect(request().sessionAttribute("productList", nullValue()))
                .andExpect(view().name("redirect:" + ConstantsUri.PRODUCT_SHOW_ALL_FIRST_PAGE));
    }

    @Test
    public void testSortFilter() throws Exception {
        mockMvc.perform(get(ConstantsUri.PRODUCT_SORT.replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("productList", nullValue()));
    }

    @Test
    public void testPagedProductsPage() throws Exception {
        when(productService.getProductsByCriteria(anyLong(), any(FormFilterBean.class), any(SortType.class), anyBoolean()))
                .thenReturn(Arrays.asList(this.product));
        mockMvc.perform(get(ConstantsUri.PRODUCT_SHOW_ALL_WITH_PAGE.replace("{pageNumber}", "1").replace("{categoryId}", "1")))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("formFilterBean1", notNullValue()))
                .andExpect(request().sessionAttribute("productList1", notNullValue()))
                .andExpect(model().attributeExists("pager"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_SHOW_ALL));
    }

    @Test
    public void testProductShow() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(this.product);
        mockMvc.perform(get(ConstantsUri.PRODUCT_SHOW.replace("{productId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_SHOW));
    }

    @Test
    public void testAddProductShowForm() throws Exception {
        mockMvc.perform(get(ConstantsUri.PRODUCT_ADD_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("formProductBean"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_ADD));
    }

    @Test
    public void testAddProduct() throws Exception {
        when(productService.productIsExist(any(Product.class))).thenReturn(false);
        when(productService.saveProductWithImage(any(Product.class), any(MultipartFile.class))).thenReturn(product);
        mockMvc.perform(post(ConstantsUri.PRODUCT_ADD))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.success.add"))
                .andExpect(view().name("redirect:" + ConstantsUri.PRODUCT_ADD_FORM));

        mockMvc.perform(post(ConstantsUri.PRODUCT_ADD)//BindingResult has errors
                .param("product.articleNumber", "090909"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_ADD));

        when(productService.productIsExist(any(Product.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PRODUCT_ADD))//product is exist
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "product.failure.put.is_exist"))
                .andExpect(view().name(ConstantsView.PRODUCT_ADD));

        when(productService.productIsExist(any(Product.class))).thenReturn(false);
        when(productService.saveProductWithImage(any(Product.class), any(MultipartFile.class))).thenThrow(DataIntegrityViolationException.class);
        mockMvc.perform(post(ConstantsUri.PRODUCT_ADD))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "product.failure.put"))
                .andExpect(view().name(ConstantsView.PRODUCT_ADD));
    }

    @Test
    public void testShowEditProductForm() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(product);

        mockMvc.perform(get(ConstantsUri.PRODUCT_EDIT_FORM.replace("{productId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("formProductBean"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_EDIT));
    }

    @Test
    public void testEditProduct() throws Exception {
        when(productService.checkBeforeUpdateProduct(any(Product.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PRODUCT_EDIT))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.success.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));

        mockMvc.perform(post(ConstantsUri.PRODUCT_EDIT)//BindingResult has errors
                .param("product.name", "Geiner"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PRODUCT_EDIT));

        when(productService.checkBeforeUpdateProduct(any(Product.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.PRODUCT_EDIT))//product is exist
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "product.failure.put.is_exist"))
                .andExpect(view().name(ConstantsView.PRODUCT_EDIT));

        when(productService.checkBeforeUpdateProduct(any(Product.class))).thenReturn(true);
        when(productService.updateProduct(any(Product.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PRODUCT_EDIT))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.failure.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(productService.deleteProductById(anyLong())).thenReturn(1);
        mockMvc.perform(get(ConstantsUri.PRODUCT_DELETE.replace("{productId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));

        when(productService.deleteProductById(anyLong())).thenReturn(0);
        mockMvc.perform(get(ConstantsUri.PRODUCT_DELETE.replace("{productId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.not.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));

        when(productService.deleteProductById(anyLong())).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.PRODUCT_DELETE.replace("{productId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "product.not.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));
    }
}