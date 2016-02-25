package net.sports_nutrition.controllers;

import net.sports_nutrition.constants.ConstantsUri;
import net.sports_nutrition.constants.ConstantsView;
import net.sports_nutrition.domain.entities.Brand;
import net.sports_nutrition.domain.entities.Country;
import net.sports_nutrition.domain.entities.Discount;
import net.sports_nutrition.domain.entities.Taste;
import net.sports_nutrition.services.IBrandService;
import net.sports_nutrition.services.IDiscountService;
import net.sports_nutrition.utils.ServiceMessage;
import net.sports_nutrition.utils.ServiceRedirectMessage;
import net.sports_nutrition.utils.converters.CountryEditor;
import net.sports_nutrition.utils.converters.DateTimeEditor;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 02.02.2016 19:51
 */
@Controller
public class PropertyController extends AbstractGlobalController {

    @Autowired
    private IBrandService brandService;
    @Autowired
    private IDiscountService discountService;

    private static final Logger log = Logger.getLogger(PropertyController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
        binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_MENU)
    public String showMenuProperty() {

        return ConstantsView.PROPERTY;
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_MENU_DISPATCHER, method = RequestMethod.GET)
    public String propertyMenuDispatcher(@PathVariable("propertyName") String propertyName, Model uiModel) {
        String page = "property_page";
        if ("brand".equalsIgnoreCase(propertyName)) page = "brand_page";
        if ("taste".equalsIgnoreCase(propertyName)) page = "taste_page";
        if ("discount".equalsIgnoreCase(propertyName)) page = "discount_page";

        return page;
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_ADD_FORM, method = RequestMethod.GET)
    public String addPropertyShowForm(@PathVariable("propertyName") String propertyName, Model uiModel) {
        String page = ConstantsView.PROPERTY;
        if ("brand".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("brandToAddBean", new Brand());
            page = ConstantsView.PROPERTY_BRAND;
        }
        if ("taste".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("tasteToAddBean", new Taste());
            page = ConstantsView.PROPERTY_TASTE;
        }
        if ("discount".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("discountToAddBean", new Discount());
            page = ConstantsView.PROPERTY_DISCOUNT;
        }

        return page;
    }


    @RequestMapping(value = ConstantsUri.PROPERTY_EDIT_FORM, method = RequestMethod.GET)
    public String editPropertyShowForm(@PathVariable("propertyName") String propertyName, @PathVariable("propertyId") Long propertyId, Model uiModel) {
        String page = ConstantsView.PROPERTY;
        if ("brand".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("brandToEditBean", brandService.getBrandById(propertyId));
            page = ConstantsView.PROPERTY_BRAND;
        }
        if ("taste".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("tasteToEditBean", tasteService.getTasteById(propertyId));
            page = ConstantsView.PROPERTY_TASTE;
        }
        if ("discount".equalsIgnoreCase(propertyName)) {
            uiModel.addAttribute("discountToEditBean", discountService.getDiscountById(propertyId));
            page = ConstantsView.PROPERTY_DISCOUNT;
        }

        return page;
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_BRAND_ADD, method = RequestMethod.POST)
    public String addBrand(@Valid @ModelAttribute("brandToAddBean") Brand brand, BindingResult result, RedirectAttributes redirect, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_BRAND;
        ;
        try {
            if (brandService.brandIsExist(brand) == true) {
                ServiceMessage.write(uiModel, "brand.failure.put.is_exist");
                return ConstantsView.PROPERTY_BRAND;
            }
            brandService.saveBrand(brand);
        } catch (Exception e) {
            log.error("Add brand" + e);
            ServiceMessage.write(uiModel, "brand.failure.put");
            return ConstantsView.PROPERTY_BRAND;
        }
        ServiceRedirectMessage.write(redirect, "brand.add.success");

        return "redirect:" + ConstantsUri.PROPERTY + "brand";
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_TASTE_ADD, method = RequestMethod.POST)
    public String addTaste(@Valid @ModelAttribute("tasteToAddBean") Taste taste, BindingResult result,
                           RedirectAttributes redirect, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_TASTE;
        try {
            if (tasteService.brandIsExist(taste)) {
                ServiceMessage.write(uiModel, "taste.failure.put.is_exist");
                return ConstantsView.PROPERTY_TASTE;
            }
            tasteService.saveTaste(taste);
        } catch (Exception e) {
            log.error("Add taste" + e);
            ServiceMessage.write(uiModel, "taste.failure.put");
        }
        ServiceRedirectMessage.write(redirect, "taste.add.success");

        return "redirect:" + ConstantsUri.PROPERTY + "taste";
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_DISCOUNT_ADD, method = RequestMethod.POST)
    public String addDiscount(@Valid @ModelAttribute("discountToAddBean") Discount discount, BindingResult result, RedirectAttributes redirect, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_DISCOUNT;
        try {
            if (discountService.discountIsExist(discount) == true) {
                ServiceMessage.write(uiModel, "discount.failure.put.is_exist");
                return ConstantsView.PROPERTY_DISCOUNT;
            }
            discountService.saveDiscount(discount);
        } catch (Exception e) {
            log.error("Add Discount" + e);
            ServiceMessage.write(uiModel, "discount.failure.put");

            return ConstantsView.PROPERTY_DISCOUNT;
        }
        ServiceRedirectMessage.write(redirect, "discount.add.success");

        return "redirect:" + ConstantsUri.PROPERTY + "discount";
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_BRAND_EDIT, method = RequestMethod.POST)
    public String editBrand(@Valid @ModelAttribute("brandToEditBean") Brand brand,
                            BindingResult result, RedirectAttributes redirect, HttpSession session, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_BRAND;
        String serviceMessage = null;
        try {
            if (!brandService.checkBeforeUpdateBrand(brand)) {
                ServiceMessage.write(uiModel, "brand.failure.put.is_exist");
                return ConstantsView.PROPERTY_BRAND;
            }
            brandService.updateBrand(brand);
            serviceMessage = "brand.success.edit";
        } catch (Exception e) {
            log.error("Edit brand" + e);
            serviceMessage = "brand.failure.edit";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "brand";
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_TASTE_EDIT, method = RequestMethod.POST)
    public String editTaste(@Valid @ModelAttribute("tasteToEditBean") Taste taste,
                            BindingResult result, RedirectAttributes redirect, HttpSession session, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_TASTE;
        String serviceMessage = null;
        try {
            if (!tasteService.checkBeforeUpdateTaste(taste)) {
                ServiceMessage.write(uiModel, "taste.failure.put.is_exist");
                return ConstantsView.PROPERTY_TASTE;
            }
            tasteService.saveTaste(taste);
            serviceMessage = "taste.success.edit";
        } catch (Exception e) {
            log.error("Edit taste" + e);
            serviceMessage = "taste.failure.edit";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "taste";
    }

    @RequestMapping(value = ConstantsUri.PROPERTY_DISCOUNT_EDIT, method = RequestMethod.POST)
    public String editDiscount(@Valid @ModelAttribute("discountToEditBean") Discount discount, BindingResult result, RedirectAttributes redirect, HttpSession session, Model uiModel) {
        if (result.hasErrors())
            return ConstantsView.PROPERTY_DISCOUNT;
        String serviceMessage = null;
        try {
            if (!discountService.checkBeforeUpdateDiscount(discount)) {
                ServiceMessage.write(uiModel, "discount.failure.put.is_exist");
                return ConstantsView.PROPERTY_DISCOUNT;
            }
            discountService.updateDiscount(discount);
            serviceMessage = "discount.success.edit";
        } catch (Exception e) {
            log.error("Edit discount" + e);
            serviceMessage = "discount.failure.edit";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "discount";
    }


    @RequestMapping(value = ConstantsUri.PROPERTY_BRAND_DELETE, method = RequestMethod.GET)
    public String deleteBrand(@PathVariable Long brandId, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        try {
            serviceMessage = (brandService.deleteById(brandId) == 0) ? "brand.delete.failure" : "brand.success.delete";
        } catch (ConstraintViolationException e) {
            serviceMessage = "brand.delete.failure.is_exist.product";
        } catch (Exception e) {
            log.error("Delete brand" + e);
            serviceMessage = "brand.delete.failure";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "brand";

    }

    @RequestMapping(value = ConstantsUri.PROPERTY_TASTE_DELETE, method = RequestMethod.GET)
    public String deleteTaste(@PathVariable Long tasteId, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        try {
            serviceMessage = (tasteService.deleteTasteById(tasteId) == 0) ? "taste.delete.failure" : "taste.success.delete";
        } catch (ConstraintViolationException e) {
            serviceMessage = "taste.delete.failure.is_exist.product";
        } catch (Exception e) {
            log.error("Delete taste" + e);
            serviceMessage = "taste.delete.failure";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "taste";

    }

    @RequestMapping(value = ConstantsUri.PROPERTY_DISCOUNT_DELETE, method = RequestMethod.GET)
    public String deleteDiscount(@PathVariable Long discountId, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        try {
            serviceMessage = (discountService.deleteDiscountById(discountId) != true) ? "discount.delete.failure" : "discount.success.delete";
        } catch (Exception e) {
            log.error("Delete discount" + e);
            serviceMessage = "taste.delete.failure";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.PROPERTY + "discount";

    }

    @ModelAttribute("brandList")
    public List<Brand> getAllBrands() {

        return brandService.findAllBrands();
    }

    @ModelAttribute("tasteList")
    public List<Taste> getAllTastes() {

        return tasteService.getAllTastes();
    }

    @ModelAttribute("discountList")
    public List<Discount> getAllDiscounts() {

        return discountService.findAllDiscounts();
    }

    @ModelAttribute("countryList")
    public List<Country> getAllCountries() {

        return countryService.getAllCountries();
    }
}
