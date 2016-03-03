package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.*;
import net.sports.nutrition.services.impl.*;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 01.03.2016 17:34
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertyControllerTest {

    @Mock
    protected TasteServiceImpl tasteService;
    @Mock
    private BrandServiceImpl brandService;
    @Mock
    private DiscountServiceImpl discountService;
    @Mock
    private CountryServiceImpl countryService;
    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    View mockView;

    @InjectMocks
    private PropertyController propertyController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).setSingleView(mockView).build();

        when(countryService.getCountryById(new Long(1))).thenReturn(new Country("ua", "Ukraine"));
        when(tasteService.findAllTastes()).thenReturn(Arrays.asList(new Taste()));
        when(brandService.findAllBrands()).thenReturn(Arrays.asList(new Brand()));
        when(discountService.findAllDiscounts()).thenReturn(Arrays.asList(new Discount()));
        when(countryService.findAllCountries()).thenReturn(Arrays.asList(new Country()));
        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testShowMenuProperty() throws Exception {
        mockMvc.perform(get(ConstantsUri.PROPERTY_MENU))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY));
    }

    @Test
    public void testPropertyMenuDispatcher() throws Exception {
        mockMvc.perform(get(ConstantsUri.PROPERTY_MENU_DISPATCHER.replace("{propertyName}", "brand")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        mockMvc.perform(get(ConstantsUri.PROPERTY_MENU_DISPATCHER.replace("{propertyName}", "taste")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        mockMvc.perform(get(ConstantsUri.PROPERTY_MENU_DISPATCHER.replace("{propertyName}", "discount")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));
    }

    @Test
    public void testAddPropertyShowForm() throws Exception {
        mockMvc.perform(get(ConstantsUri.PROPERTY_ADD_FORM.replace("{propertyName}", "brand")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("brandToAddBean"))
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        mockMvc.perform(get(ConstantsUri.PROPERTY_ADD_FORM.replace("{propertyName}", "taste")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tasteToAddBean"))
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        mockMvc.perform(get(ConstantsUri.PROPERTY_ADD_FORM.replace("{propertyName}", "discount")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("discountToAddBean"))
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));
    }

    @Test
    public void testEditPropertyShowForm() throws Exception {
        Long propertyId = new Long(1);
        when(brandService.getBrandById(propertyId)).thenReturn(new Brand());
        mockMvc.perform(get(ConstantsUri.PROPERTY_EDIT_FORM.replace("{propertyName}", "brand").replace("{propertyId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("brandToEditBean"))
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        when(tasteService.getTasteById(propertyId)).thenReturn(new Taste());
        mockMvc.perform(get(ConstantsUri.PROPERTY_EDIT_FORM.replace("{propertyName}", "taste").replace("{propertyId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("brandToEditBean"))
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        when(discountService.getDiscountById(propertyId)).thenReturn(new Discount());
        mockMvc.perform(get(ConstantsUri.PROPERTY_EDIT_FORM.replace("{propertyName}", "discount").replace("{propertyId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("discountToEditBean"))
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));
    }

    @Test
    public void testAddBrand() throws Exception {
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_ADD)
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.add.success"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_ADD).param("name", "Olimp"))//BindingResult has error
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        when(brandService.brandIsExist(any(Brand.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_ADD)//brand is exist
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "brand.failure.put.is_exist"))
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        when(brandService.brandIsExist(any(Brand.class))).thenReturn(false);
        when(brandService.saveBrand(any(Brand.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_ADD)//save brand throw Exception
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "brand.failure.put"))
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));
    }

    @Test
    public void testAddTaste() throws Exception {
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_ADD)
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.add.success"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_ADD)//BindingResult has error
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        when(tasteService.tasteIsExist(any(Taste.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_ADD)//taste is exist
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "taste.failure.put.is_exist"))
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        when(tasteService.tasteIsExist(any(Taste.class))).thenReturn(false);
        when(tasteService.saveTaste(any(Taste.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_ADD)//save taste throw Exception
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "taste.failure.put"))
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));
    }

    @Test
    public void testAddDiscount() throws Exception {
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_ADD)
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.add.success"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_ADD)//BindingResult has error
                .param("name", "Summer"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));

        when(discountService.discountIsExist(any(Discount.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_ADD)//discount is exist
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "discount.failure.put.is_exist"))
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));

        when(discountService.discountIsExist(any(Discount.class))).thenReturn(false);
        when(discountService.saveDiscount(any(Discount.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_ADD)//save discount throw Exception
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "discount.failure.put"))
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));
    }

    @Test
    public void testEditBrand() throws Exception {
        when(brandService.checkBeforeUpdateBrand(any(Brand.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_EDIT)
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.success.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_EDIT).param("name", "Olimp"))//BindingResult has error
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        when(brandService.checkBeforeUpdateBrand(any(Brand.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_EDIT)//brand is exist
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "brand.failure.put.is_exist"))
                .andExpect(model().attributeExists("brandList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_BRAND));

        when(brandService.checkBeforeUpdateBrand(any(Brand.class))).thenReturn(true);
        when(brandService.updateBrand(any(Brand.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_BRAND_EDIT)//save brand throw Exception
                .param("name", "Olimp")
                .param("country", "1"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.failure.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));

    }

    @Test
    public void testEditTaste() throws Exception {
        when(tasteService.checkBeforeUpdateTaste(any(Taste.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_EDIT)
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.success.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_EDIT)//BindingResult has error
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("tasteList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        when(tasteService.checkBeforeUpdateTaste(any(Taste.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_EDIT)//taste is exist
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "taste.failure.put.is_exist"))
                .andExpect(view().name(ConstantsView.PROPERTY_TASTE));

        when(tasteService.checkBeforeUpdateTaste(any(Taste.class))).thenReturn(true);
        when(tasteService.checkBeforeUpdateTaste(any(Taste.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_TASTE_EDIT)//save taste throw Exception
                .param("name", "Malina"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.failure.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));
    }

    @Test
    public void testEditDiscount() throws Exception {
        when(discountService.checkBeforeUpdateDiscount(any(Discount.class))).thenReturn(true);
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_EDIT)
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.success.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));

        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_EDIT)//BindingResult has error
                .param("name", "Summer"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));

        when(discountService.checkBeforeUpdateDiscount(any(Discount.class))).thenReturn(false);
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_EDIT)//discount is exist
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("serviceMessage", "discount.failure.put.is_exist"))
                .andExpect(model().attributeExists("discountList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name(ConstantsView.PROPERTY_DISCOUNT));

        when(discountService.checkBeforeUpdateDiscount(any(Discount.class))).thenReturn(true);
        when(discountService.updateDiscount(any(Discount.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.PROPERTY_DISCOUNT_EDIT)//save discount throw Exception
                .param("name", "Summer")
                .param("size", "50")
                .param("expirationDate", "2017-10-02"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.failure.edit"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));
    }

    @Test
    public void testDeleteBrand() throws Exception {
        long id = 1;
        when(brandService.deleteById(id)).thenReturn(1);
        mockMvc.perform(get(ConstantsUri.PROPERTY_BRAND_DELETE.replace("{brandId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));

        when(brandService.deleteById(id)).thenReturn(0);
        mockMvc.perform(get(ConstantsUri.PROPERTY_BRAND_DELETE.replace("{brandId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));

        when(brandService.deleteById(id)).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.PROPERTY_BRAND_DELETE.replace("{brandId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "brand.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "brand"));
    }

    @Test
    public void testDeleteTaste() throws Exception {
        Long id = new Long(1);
        when(tasteService.deleteTasteById(id)).thenReturn(1);
        mockMvc.perform(get(ConstantsUri.PROPERTY_TASTE_DELETE.replace("{tasteId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));

        when(tasteService.deleteTasteById(id)).thenReturn(0);
        mockMvc.perform(get(ConstantsUri.PROPERTY_TASTE_DELETE.replace("{tasteId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));

        when(tasteService.deleteTasteById(id)).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.PROPERTY_TASTE_DELETE.replace("{tasteId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "taste.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "taste"));
    }

    @Test
    public void testDeleteDiscount() throws Exception {
        Long id = new Long(1);
        when(discountService.deleteDiscountById(id)).thenReturn(true);
        mockMvc.perform(get(ConstantsUri.PROPERTY_DISCOUNT_DELETE.replace("{discountId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.success.delete"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));

        when(discountService.deleteDiscountById(id)).thenReturn(false);
        mockMvc.perform(get(ConstantsUri.PROPERTY_DISCOUNT_DELETE.replace("{discountId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));

        when(tasteService.deleteTasteById(id)).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.PROPERTY_DISCOUNT_DELETE.replace("{discountId}", "1")))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "discount.delete.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.PROPERTY + "discount"));
    }
}