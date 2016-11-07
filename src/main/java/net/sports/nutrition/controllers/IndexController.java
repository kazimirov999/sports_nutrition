package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.utils.ServiceMessage;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Controller is responsible for processing  requests
 * related to main page(index) and page for message.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class IndexController extends AbstractGlobalController {

    private static final Logger log = Logger.getLogger(IndexController.class);

    /**
     * Shows index page.
     *
     * @return modelAndView
     */
    @RequestMapping(value = {"/", ConstantsUri.INDEX}, method = RequestMethod.GET)
    public String showIndexPage(){

        return ConstantsView.INDEX;
    }

    /**
     * Writes message to the Model.
     *
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.MESSAGE_SHOW, method = RequestMethod.GET)
    public String showMessage(@RequestParam(value = "message", required = false) String message, Model uiModel) {
        if (message!= null) {
            log.info("Show failure message: " + message);
            ServiceMessage.write(uiModel, message);
        }

        return ConstantsView.MESSAGE;
    }


}
