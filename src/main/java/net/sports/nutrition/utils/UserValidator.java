package net.sports.nutrition.utils;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.02.2016 22:09
 */

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validator for User.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    public boolean supports(Class clazz) {
        System.out.println(clazz);
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.empty.first.name", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.empty.last.name", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginEmail", "error.empty.login.email", "Login(email) is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.empty.phone", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordOriginal", "error.empty.password", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.empty.address", "Field is required.");

        if (user.getPasswordOriginal() != null && user.getPasswordDubl() != null
                && !user.getPasswordOriginal().equalsIgnoreCase(user.getPasswordDubl())) {
            errors.rejectValue("passwordOriginal", "error.password.not.correct", "Password is not correct");
        }
        if (user.getLoginEmail() != null && userService.loginIsExist(user.getLoginEmail())) {
            errors.rejectValue("loginEmail", "error.login.is_exist", "Login is exist.");
        }
    }
}