package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Country;
import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.services.IUserService;
import net.sports.nutrition.utils.ServiceMessage;
import net.sports.nutrition.utils.ServiceRedirectMessage;
import net.sports.nutrition.utils.UserValidator;
import net.sports.nutrition.utils.converters.CountryEditor;
import net.sports.nutrition.utils.converters.DateTimeEditor;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.02.2016 17:30
 */

@Controller
public class UserController extends AbstractGlobalController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;
    private static final Logger log = Logger.getLogger(CartController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
        binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
    }

    @RequestMapping(value = ConstantsUri.USER_REGISTER, method = RequestMethod.GET)
    public String showRegisterForm(Model uiModel) {
        uiModel.addAttribute("formUserBean", new User());
        uiModel.addAttribute("countryList", countryService.findAllCountries());

        return ConstantsView.USER_REGISTER;
    }

    @RequestMapping(value = ConstantsUri.USER_REGISTER, method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("formUserBean") User user, BindingResult result, RedirectAttributes redirect) {

        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return ConstantsView.USER_REGISTER;
        }
        String serviceMessage = "register.user.success";
        try {
            user.setEnabled(true);
            userService.saveUser(user);
            log.info("Register user: " + user);
        } catch (Exception e) {
            log.error("Register user", e);
            serviceMessage = "register.user.failure";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.MESSAGE_SHOW;
    }

    @RequestMapping(value = ConstantsUri.USER_SHOW_ALL, method = RequestMethod.GET)
    public String showUsers(Model uiModel) {

        uiModel.addAttribute("userList", userService.getAllUsers());

        return ConstantsView.USER_SHOW_ALL;
    }

    @RequestMapping(value = ConstantsUri.USER_ACTIVATE, method = RequestMethod.GET)
    public String activateUser(@PathVariable("userId") Long userId, Model uiModel) {

        String serviceMessage = "activate.success";
        try {
            userService.activateUser(userId);
            log.info("Activate user: id=" + userId);
        } catch (Exception e) {
            log.error("Activate user", e);
            serviceMessage = "activate.failure";
        }
        ServiceMessage.write(uiModel, serviceMessage);

        return "forward:" + ConstantsUri.USER_SHOW_ALL;
    }

    @RequestMapping(value = ConstantsUri.USER_DEACTIVATE, method = RequestMethod.GET)
    public String deactivateUser(@PathVariable("userId") Long userId, Model uiModel) {

        String serviceMessage = "deactivate.success";
        try {
            userService.deactivateUser(userId);
            log.info("Deactivate user: id=" + userId);
        } catch (Exception e) {
            log.error("Deactivate user", e);
            serviceMessage = "deactivate.failure";
        }
        ServiceMessage.write(uiModel, serviceMessage);

        return "forward:" + ConstantsUri.USER_SHOW_ALL;
    }

    @RequestMapping(value = ConstantsUri.USER_MAKE_ADMIN, method = RequestMethod.GET)
    public String setUserRoleAdmin(@PathVariable("userId") Long userId, Model uiModel) {

        String serviceMessage = "setAdmin.success";
        try {
            userService.setUserRoleAdmin(userId);
            log.info("Set role admin: id=" + userId);
        } catch (Exception e) {
            log.error("Set role admin", e);
            serviceMessage = "setAdmin.failure";
        }
        ServiceMessage.write(uiModel, serviceMessage);

        return "forward:" + ConstantsUri.USER_SHOW_ALL;
    }

    @RequestMapping(value = ConstantsUri.USER_DELETE, method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Long userId, Model uiModel) {

        String serviceMessage = "delete.success";
        try {
            userService.deleteUserById(userId);
            log.info("Delete user: id=" + userId);
        } catch (Exception e) {
            log.error("Delete user", e);
            serviceMessage = "delete.failure";
        }
        ServiceMessage.write(uiModel, serviceMessage);

        return "forward:" + ConstantsUri.USER_SHOW_ALL;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
