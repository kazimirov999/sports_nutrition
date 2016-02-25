package net.sports_nutrition.controllers;

import net.sports_nutrition.constants.ConstantsUri;
import net.sports_nutrition.constants.ConstantsView;
import net.sports_nutrition.domain.entities.Cart;
import net.sports_nutrition.domain.entities.Country;
import net.sports_nutrition.domain.entities.Customer;
import net.sports_nutrition.services.ICartService;
import net.sports_nutrition.services.IMailService;
import net.sports_nutrition.utils.Pager;
import net.sports_nutrition.utils.ServiceRedirectMessage;
import net.sports_nutrition.utils.converters.CountryEditor;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 13:08
 */
@Controller
public class OrderController extends AbstractGlobalController {

    @Autowired
    private IMailService mailService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private MessageSource messageSource;

    private final int ORDER_LIST_PAGE_SIZE = 6;
    private static final Logger log = Logger.getLogger(OrderController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
    }

    @RequestMapping(value = ConstantsUri.ORDER_SHOW_FORM, method = RequestMethod.GET)
    public String showOrderForm(Model uiModel) {
        uiModel.addAttribute("formCustomerBean", new Customer());

        return ConstantsView.ORDER_PLACE;
    }

    @RequestMapping(value = ConstantsUri.ORDER_SAVE, method = RequestMethod.POST)
    public String saveOrder(@Valid @ModelAttribute("formCustomerBean") Customer customer, BindingResult result,
                            RedirectAttributes redirect, HttpSession session) {
        if (result.hasErrors())
            return ConstantsView.ORDER_PLACE;
        Cart cart = (Cart) session.getAttribute("cart");
        cart.setCustomer(customer);
        Cart originalCart = new Cart().buildByProxy(cart);
        cartService.saveCart(originalCart);
        cart.cleanCart();
        log.info("Save order: " + originalCart);
        ServiceRedirectMessage.write(redirect, "order.save.success");

        return "redirect:" + ConstantsUri.MESSAGE_SHOW;
    }

    @RequestMapping(value = ConstantsUri.ORDER_SHOW_ALL, method = RequestMethod.GET)
    public String getAllOrders(Model uiModel, @PathVariable Integer pageNumber, HttpSession session) {

        PagedListHolder<Cart> pagedListHolder = new PagedListHolder<Cart>(cartService.findAllCarts());
        pagedListHolder.setPageSize(ORDER_LIST_PAGE_SIZE);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
            pagedListHolder.setPage(goToPage);
        }

        BigDecimal totalPrice = new BigDecimal(0);
        for (Cart cart : pagedListHolder.getSource())
            totalPrice = totalPrice.add(cart.getGrandTotalPrice());

        session.setAttribute("holderCarts", pagedListHolder);
        uiModel.addAttribute("pager", Pager.currentPage(pagedListHolder, ConstantsUri.ORDER_BASE_URL, ORDER_LIST_PAGE_SIZE));
        uiModel.addAttribute("holderCarts", pagedListHolder);
        uiModel.addAttribute("totalPrice", totalPrice);
        uiModel.addAttribute("totalProducts", pagedListHolder.getSource().size());

        return ConstantsView.ORDER_SHOW_ALL;
    }

    @RequestMapping(value = ConstantsUri.ORDER_PLACE_DELETE, method = RequestMethod.GET)
    public String placeOrderDelete(@PathVariable("orderId") Long orderId, HttpSession session, final RedirectAttributes redirect) {

        final String emailSubject = messageSource.getMessage("email.order.subject", null, LocaleContextHolder.getLocale());
        final String emailTextPattern = messageSource.getMessage("email.order.text.for.failure.place", null, LocaleContextHolder.getLocale());

        String serviceMessage = "order.success.delete.order";
        try {
            Cart cart = cartService.getCartById(orderId);
            System.out.println(emailSubject + " " + emailTextPattern);
            String emailText = cartService.generateBodyTextForFailurePlaceOrderEmail(emailTextPattern, cart);
            mailService.sendMail(cart.getCustomer().getEmail(), emailSubject, emailText);
            cartService.deleteCart(cart);
        } catch (MailException e) {
            log.error("Mail send before delete order",e);
            serviceMessage = "order.failure.delete.mail.error";
        } catch (Exception e) {
            log.error("Delete order",e);
            serviceMessage = "order.failure.delete";
        }

        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + (String)session.getAttribute("lastUri");
    }

    @RequestMapping(value = ConstantsUri.ORDER_PLACE_SEND, method = RequestMethod.GET)
    public String placeOrderSend(@PathVariable("orderId") Long orderId, HttpSession session, final RedirectAttributes redirect) {

        final String emailSubject = messageSource.getMessage("email.order.subject", null, LocaleContextHolder.getLocale());
        final String emailTextPattern = messageSource.getMessage("email.order.text.for.success.place", null, LocaleContextHolder.getLocale());

        String serviceMessage = "order.success.place.order";

        try {
            Cart cart = cartService.getCartById(orderId);
            String emailText = cartService.generateBodyTextForSuccessPlaceOrderEmail(emailTextPattern, cart);
            mailService.sendMail(cart.getCustomer().getEmail(), emailSubject, emailText);
            cartService.deleteCart(cart);
        } catch (MailException e) {
            log.error("Male send before place order "+e);
            serviceMessage = "order.failure.place.mail.error";
        } catch (Exception e) {
            log.error("Place order"+e);
            serviceMessage = "order.failure.place";
        }

        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + (String)session.getAttribute("lastUri");
    }

    @ModelAttribute("countryList")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
