package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.domain.entities.*;
import net.sports.nutrition.services.impl.CartServiceImpl;
import net.sports.nutrition.services.impl.CategoryServiceImpl;
import net.sports.nutrition.services.impl.TasteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.03.2016 8:14
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CartControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    private CartServiceImpl cartService;
    @Mock
    private TasteServiceImpl tasteServiceimpl;
    @Spy
    private Cart cart;
    @Mock
    View mockView;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;
    private Cart testCart;

    @Before
    public void init() {
        Taste taste = new Taste("Malina");
        Product product = new Product();
        product.setArticleNumber(new Long(909009));
        product.setPrice(new BigDecimal(300));
        testCart = new Cart();
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(new CartItem(product, taste));
        testCart.setCartItems(cartItems);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).setSingleView(mockView).build();

        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testAddToCart() throws Exception {

        mockMvc.perform(get(ConstantsUri.CART_BUY)
                .param("product.articleNumber", "909009")
                .param("product.price", "300")
                .param("taste.name", "Malina"))
                .andExpect(status().isOk());

        assertNotNull(this.cart.getCartItems());
        Product productFromSession = this.cart.getCartItems().iterator().next().getProduct();
        Taste tasteFromSession = this.cart.getCartItems().iterator().next().getTaste();

        assertNotNull(productFromSession);
        assertNotNull(tasteFromSession);

        assertEquals(productFromSession, this.testCart.getCartItems().iterator().next().getProduct());
        assertEquals(tasteFromSession, this.testCart.getCartItems().iterator().next().getTaste());
    }

    @Test
    public void testDeleteFromCart() throws Exception {
        this.cart.setCartItems(this.testCart.getCartItems());
        mockMvc.perform(get(ConstantsUri.CART_MANAGE)
                .param("delete", "delete")
                .param("product.articleNumber", "909009")
                .param("product.price", "300")
                .param("taste.name", "Malina"))
                .andExpect(status().isOk());

        assertTrue(this.cart.getCartItems().isEmpty());
    }

    @Test
    public void testIncreaseQuantityInCart() throws Exception {
        this.cart.setCartItems(this.testCart.getCartItems());
        mockMvc.perform(get(ConstantsUri.CART_MANAGE)
                .param("increase", "increase")
                .param("product.articleNumber", "909009")
                .param("product.price", "300")
                .param("taste.name", "Malina"))
                .andExpect(status().isOk());
        assertFalse(this.cart.getCartItems().isEmpty());
        assertEquals(2, this.cart.getCartItems().iterator().next().getQuantity());
    }

    @Test
    public void testDecreaseQuantityInCart() throws Exception {
        this.cart.setCartItems(this.testCart.getCartItems());
        this.cart.getCartItems().iterator().next().setQuantity(3);
        mockMvc.perform(get(ConstantsUri.CART_MANAGE)
                .param("decrease", "decrease")
                .param("product.articleNumber", "909009")
                .param("product.price", "300")
                .param("taste.name", "Malina"))
                .andExpect(status().isOk());

        assertFalse(this.cart.getCartItems().isEmpty());
        assertEquals(2, this.cart.getCartItems().iterator().next().getQuantity());
    }

    @Test
    public void testCleanCart() throws Exception {
        this.cart.setCartItems(this.testCart.getCartItems());
        mockMvc.perform(get(ConstantsUri.CART_CLEAN))
                .andExpect(status().isOk());

        assertTrue(this.cart.getCartItems().isEmpty());
    }

    @Test
    public void testShowCart() throws Exception {
        mockMvc.perform(get(ConstantsUri.CART_SHOW))
                .andExpect(model().attributeExists("cart"))
                .andExpect(status().isOk());
    }
}