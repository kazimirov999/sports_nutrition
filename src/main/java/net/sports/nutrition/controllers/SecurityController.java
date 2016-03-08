package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.utils.ServiceRedirectMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * The Controller is responsible for processing user requests
 * related to security and building appropriate model and
 * passes it to the view for rendering.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class SecurityController extends AbstractGlobalController {

    /**
     * Authorizes user.
     *
     * @param error    - unsuccessful authorization
     * @param logout   - out from the site
     * @param session  - session between an HTTP client and an HTTP server.
     * @param redirect - redirect attributes
     */
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
