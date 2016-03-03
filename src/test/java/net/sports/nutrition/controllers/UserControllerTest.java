package net.sports.nutrition.controllers;


import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.services.impl.CategoryServiceImpl;
import net.sports.nutrition.services.impl.CountryServiceImpl;
import net.sports.nutrition.services.impl.UserServiceImpl;
import net.sports.nutrition.utils.UserValidator;
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
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 01.03.2016 9:50
 */

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:/front_dispatcher-servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Mock
    private UserValidator userValidator;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private CountryServiceImpl countryService;
    @Mock
    private CategoryServiceImpl categoryService;
    @Mock
    View mockView;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(userValidator.supports(any(Class.class))).thenReturn(true);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setSingleView(mockView).build();

        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(new Category()));
    }

    @Test
    public void testShowRegisterForm() throws Exception {
        List<Country> countries = Arrays.asList(new Country("ua", "Ukraine"));
        when(countryService.findAllCountries()).thenReturn(countries);

        mockMvc.perform(get(ConstantsUri.USER_REGISTER))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("countryList", is(countries)))
                .andExpect(model().attribute("formUserBean", instanceOf(User.class)))
                .andExpect(view().name(ConstantsView.USER_REGISTER));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post(ConstantsUri.USER_REGISTER))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "register.user.success"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));

        mockMvc.perform(post(ConstantsUri.USER_REGISTER)//BindingResult has.errors
                .param("firstName", "Sasha")
                .param("loginEmail", "errorEmail"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(view().name(ConstantsView.USER_REGISTER));

        when(userService.saveUser(any(User.class))).thenThrow(Exception.class);
        mockMvc.perform(post(ConstantsUri.USER_REGISTER)//register user throw Exception
                .param("userId", "1"))//save user throw Exception
                .andExpect(status().isOk())
                .andExpect(flash().attribute("serviceMessage", "register.user.failure"))
                .andExpect(view().name("redirect:" + ConstantsUri.MESSAGE_SHOW));
    }

    @Test
    public void testShowUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User()));
        mockMvc.perform(get(ConstantsUri.USER_SHOW_ALL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("userList", userService.getAllUsers()))
                .andExpect(view().name(ConstantsView.USER_SHOW_ALL));
    }

    @Test
    public void testActivateUser() throws Exception {
        mockMvc.perform(get(ConstantsUri.USER_ACTIVATE.replace("{userId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "activate.success"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));

        doThrow(Exception.class).when(userService).activateUser(new Long(1));
        mockMvc.perform(get(ConstantsUri.USER_ACTIVATE.replace("{userId}", "1")))//activate user throw Exception
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "activate.failure"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));
    }

    @Test
    public void testDeactivateUser() throws Exception {
        mockMvc.perform(get(ConstantsUri.USER_DEACTIVATE.replace("{userId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "deactivate.success"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));

        doThrow(Exception.class).when(userService).deactivateUser(new Long(1));
        mockMvc.perform(get(ConstantsUri.USER_DEACTIVATE.replace("{userId}", "1")))//deactivate user throw Exception
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "deactivate.failure"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));
    }

    @Test
    public void testSetUserRoleAdmin() throws Exception {
        mockMvc.perform(get(ConstantsUri.USER_MAKE_ADMIN.replace("{userId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "setAdmin.success"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));

        doThrow(Exception.class).when(userService).setUserRoleAdmin(new Long(1));
        mockMvc.perform(get(ConstantsUri.USER_MAKE_ADMIN.replace("{userId}", "1")))//set user role admin throw Exception
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "setAdmin.failure"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(get(ConstantsUri.USER_DELETE.replace("{userId}", "1")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "delete.success"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));

        doThrow(Exception.class).when(userService).deleteUserById(new Long(1));
        mockMvc.perform(get(ConstantsUri.USER_DELETE.replace("{userId}", "1")))//delete user throw Exception
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("serviceMessage", "delete.failure"))
                .andExpect(view().name("forward:" + ConstantsUri.USER_SHOW_ALL));
    }
}