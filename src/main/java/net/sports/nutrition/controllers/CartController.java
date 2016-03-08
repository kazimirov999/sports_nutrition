package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.*;
import net.sports.nutrition.form.beans.FormBuyBean;
import net.sports.nutrition.services.ICartService;
import net.sports.nutrition.utils.converters.CountryEditor;
import net.sports.nutrition.utils.converters.DateTimeEditor;
import net.sports.nutrition.utils.converters.ProductEditor;
import net.sports.nutrition.utils.converters.TasteEditor;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * The Controller is responsible for processing user requests
 * related to cart and building appropriate model and
 * passes it to the view for rendering.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
@SessionAttributes(value = "cart", types = Cart.class)
public class CartController extends AbstractGlobalController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private Cart cart;

    private static final Logger log = Logger.getLogger(CartController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
        binder.registerCustomEditor(Product.class, new ProductEditor(productService));
        binder.registerCustomEditor(Taste.class, new TasteEditor(tasteService));
        binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
    }

    /**
     * Adds product to the shopping cart.
     *
     * @param formBuyBean - contains information about the product that is bought
     * @param session     - session between an HTTP client and an HTTP server.
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.CART_BUY, method = RequestMethod.GET)
    public String addToCart(@ModelAttribute("formBuyBean") FormBuyBean formBuyBean, HttpSession session) {
        log.info("Add to cart product: " + formBuyBean.toString());

        if (formBuyBean != null) {
            cart.addCartItem(new CartItem(formBuyBean.getProduct(), formBuyBean.getTaste()));
        }

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    /**
     * Removes product from the shopping cart.
     *
     * @param formBuyBean - contains information about the product that is bought
     * @param delete      - name of action button
     * @param session     - session between an HTTP client and an HTTP server.
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.CART_MANAGE, method = RequestMethod.GET, params = {"delete"})
    public String deleteFromCart(@ModelAttribute("formBuyBean") FormBuyBean formBuyBean, @RequestParam String delete, HttpSession session) {
        log.info("Delete from cart product: " + formBuyBean.toString());

        cart.removeCartItem(new CartItem(formBuyBean.getProduct(), formBuyBean.getTaste()));

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    /**
     * Increases the number of products in the shopping cart.
     *
     * @param formBuyBean - contains information about the product that is bought
     * @param increase    - name of action button
     * @param session     - session between an HTTP client and an HTTP server.
     * @return modelAndView
     * @see CartController#decreaseQuantityInCart(FormBuyBean, String, HttpSession)
     */
    @RequestMapping(value = ConstantsUri.CART_MANAGE, method = RequestMethod.GET, params = {"increase"})
    public String increaseQuantityInCart(@ModelAttribute("formBuyBean") FormBuyBean formBuyBean, @RequestParam String increase, HttpSession session) {
        cart.increaseQuantity(new CartItem(formBuyBean.getProduct(), formBuyBean.getTaste()));

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    /**
     * Decreases the number of products in the shopping cart.
     *
     * @param formBuyBean - contains information about the product that is bought
     * @param decrease    - name of action button
     * @param session     - session between an HTTP client and an HTTP server.
     * @return modelAndView
     * @see CartController#increaseQuantityInCart(FormBuyBean, String, HttpSession)
     */
    @RequestMapping(value = ConstantsUri.CART_MANAGE, method = RequestMethod.GET, params = {"decrease"})
    public String decreaseQuantityInCart(@ModelAttribute("formBuyBean") FormBuyBean formBuyBean, @RequestParam String decrease, HttpSession session) {
        cart.decreaseQuantity(new CartItem(formBuyBean.getProduct(), formBuyBean.getTaste()));

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    /**
     * Clears shopping cart.
     *
     * @param session - session between an HTTP client and an HTTP server.
     * @return modelAndView
     * @see CartController#addToCart(FormBuyBean, HttpSession)
     */
    @RequestMapping(value = ConstantsUri.CART_CLEAN, method = RequestMethod.GET)
    public String cleanCart(HttpSession session) {
        cart.cleanCart();

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    @RequestMapping(value = ConstantsUri.CART_SHOW, method = RequestMethod.GET)
    public String showCart() {

        return ConstantsView.CART;
    }

    /**
     * Adds cart to the Model
     */
    @ModelAttribute("cart")
    Cart getCart() {

        return this.cart;
    }

    /**
     * Adds formBuyBean to the Model
     */
    @ModelAttribute("formBuyBean")
    public FormBuyBean getBuyBean() {
        return new FormBuyBean();
    }


}
