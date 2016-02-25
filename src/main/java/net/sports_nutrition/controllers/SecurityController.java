package net.sports_nutrition.controllers;

import net.sports_nutrition.constants.ConstantsUri;
import net.sports_nutrition.constants.ConstantsView;
import net.sports_nutrition.utils.ServiceRedirectMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 12.02.2016 18:24
 */
@Controller
public class SecurityController extends AbstractGlobalController {

    @RequestMapping(value = ConstantsUri.SECURITY_USER_LOGIN, method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpSession session, RedirectAttributes redirect) {
        if (error != null) {
            ServiceRedirectMessage.write(redirect, "errorAuth", "authentication.failure");
            return "redirect:" + (String) session.getAttribute("lastUri");
        }
        if (logout != null) {
            ServiceRedirectMessage.write(redirect, "errorAuth", "logout.success");
            return "redirect:" + ConstantsUri.INDEX;
        }

        return ConstantsView.AUTHENTICATION;

    }


}
