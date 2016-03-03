package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.entities.Customer;
import net.sports.nutrition.services.impl.CartServiceImpl;
import net.sports.nutrition.services.impl.CategoryServiceImpl;
import net.sports.nutrition.services.impl.CountryServiceImpl;
import net.sports.nutrition.services.impl.MailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 02.03.2016 10:49
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    private CountryServiceImpl countryService;
    @Mock
    private MailServiceImpl mailService;
    @Mock
    private CartServiceImpl cartService;
    @Mock
    private MessageSource messageSource;
    @Mock
    View mockView;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).setSingleView(mockView).build();

        when(countryService.getCountryById(new Long(1))).thenReturn(new Country("ua", "Ukraine"));
        when(countryService.findAllCountries()).thenReturn(Arrays.asList(new Country("ua", "Ukraine")));
        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testShowOrderForm() throws Exception {
        mockMvc.perform(get(ConstantsUri.ORDER_SHOW_FORM))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countryList"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("formCustomerBean"))
                .andExpect(view().name(ConstantsView.ORDER_PLACE));
    }

    @Test
    public void testSaveOrder() throws Exception {
        mockMvc.perform(post(ConstantsUri.ORDER_SAVE)
                .param("firstName", "Nik")
                .param("lastName", "Born")
                .param("address", "Soborna,8")
                .param("email", "email@email.net")
                .param("phoneNumber", "09600000")
                .param("country", "1")
                .sessionAttr("cart", new Cart()))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "order.save.success"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));

        mockMvc.perform(post(ConstantsUri.ORDER_SAVE)//Binding result has errors
                .param("firstName", "")
                .param("lastName", "Born")
                .param("address", "Soborna,8")
                .param("email", "email@email.net")
                .param("phoneNumber", "09600000")
                .param("country", "1"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("countryList"))
                .andExpect(status().isOk())
                .andExpect(view().name(ConstantsView.ORDER_PLACE));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        when(cartService.findAllCarts()).thenReturn(Arrays.asList(new Cart()));
        mockMvc.perform(get(ConstantsUri.ORDER_SHOW_ALL.replace("{pageNumber}", "1")))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("holderCarts"))
                .andExpect(model().attributeExists("pager"))
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(model().attribute("totalProducts", is(1)))
                .andExpect(status().isOk())
                .andExpect(view().name(ConstantsView.ORDER_SHOW_ALL));
    }

    @Test
    public void testPlaceOrderDelete() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("nik@n.net");
        Cart cart = new Cart();
        cart.setCustomer(customer);
        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("message");
        when(cartService.getCartById(new Long(1))).thenReturn(cart);
        doNothing().when(mailService).sendMail(anyString(), anyString(), anyString());
        when(cartService.deleteCart(any(Cart.class))).thenReturn(true);

        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_DELETE.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.success.delete.order"))
                .andExpect(status().isOk());

        doThrow(MailSendException.class).when(mailService).sendMail(anyString(), anyString(), anyString());
        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_DELETE.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.failure.delete.mail.error"))
                .andExpect(status().isOk());

        doNothing().when(mailService).sendMail(anyString(), anyString(), anyString());
        when(cartService.deleteCart(any(Cart.class))).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_DELETE.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.failure.delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPlaceOrderSend() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("nik@n.net");
        Cart cart = new Cart();
        cart.setCustomer(customer);
        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("message");
        when(cartService.getCartById(new Long(1))).thenReturn(cart);
        doNothing().when(mailService).sendMail(anyString(), anyString(), anyString());
        when(cartService.deleteCart(any(Cart.class))).thenReturn(true);

        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_SEND.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.success.place.order"))
                .andExpect(status().isOk());

        doThrow(MailSendException.class).when(mailService).sendMail(anyString(), anyString(), anyString());
        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_SEND.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.failure.place.mail.error"))
                .andExpect(status().isOk());

        doNothing().when(mailService).sendMail(anyString(), anyString(), anyString());
        when(cartService.deleteCart(any(Cart.class))).thenThrow(Exception.class);
        mockMvc.perform(get(ConstantsUri.ORDER_PLACE_SEND.replace("{orderId}", "1")))
                .andExpect(flash().attribute("serviceMessage", "order.failure.place"))
                .andExpect(status().isOk());
    }
}