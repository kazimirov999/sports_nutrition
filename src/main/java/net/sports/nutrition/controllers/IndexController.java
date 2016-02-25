package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.utils.ServiceMessage;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 14:31
 */

@Controller
public class IndexController extends AbstractGlobalController {

    private static final Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping(value = {"/", ConstantsUri.INDEX}, method = RequestMethod.GET)
    public String showIndexPage(ModelMap model) {

        return ConstantsView.INDEX;
    }

    @RequestMapping(value = ConstantsUri.MESSAGE_SHOW, method = RequestMethod.GET)
    public String showMessage(@RequestParam(value = "message", required = false) String message, Model uiModel) {

        if (message!= null) {
            log.info("Show failure message: " + message);
            ServiceMessage.write(uiModel, message);
        }

        return ConstantsView.MESSAGE;
    }


}
