package net.sports_nutrition.controllers;

import net.sports_nutrition.constants.ConstantsUri;
import net.sports_nutrition.constants.ConstantsView;
import net.sports_nutrition.domain.entities.Brand;
import net.sports_nutrition.domain.entities.Category;
import net.sports_nutrition.domain.entities.Discount;
import net.sports_nutrition.domain.entities.Product;
import net.sports_nutrition.exceptions.ProductNotFoundException;
import net.sports_nutrition.form.beans.FormBuyBean;
import net.sports_nutrition.form.beans.FormFilterBean;
import net.sports_nutrition.form.beans.FormProductBean;
import net.sports_nutrition.form.beans.FormSortedBean;
import net.sports_nutrition.form.containers.FormFilterContent;
import net.sports_nutrition.form.containers.FormPropertyContent;
import net.sports_nutrition.services.IBrandService;
import net.sports_nutrition.services.IDiscountService;
import net.sports_nutrition.services.IFormService;
import net.sports_nutrition.utils.Pager;
import net.sports_nutrition.utils.ServiceMessage;
import net.sports_nutrition.utils.ServiceRedirectMessage;
import net.sports_nutrition.utils.converters.BrandEditor;
import net.sports_nutrition.utils.converters.CategoryEditor;
import net.sports_nutrition.utils.converters.DiscountEditor;
import net.sports_nutrition.utils.converters.TasteCustomCollectionEditor;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes(value = "formSortedBean", types = {FormSortedBean.class})
public class ProductController extends AbstractGlobalController {

    @Autowired
    private IFormService formService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IDiscountService discountService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FormSortedBean formSortedBean;


    private final String MODEL_ATTRIBUTE_PRODUCTS = "products";
    private final String MODEL_ATTRIBUTE_PAGER = "pager";
    private final String MODEL_ATTRIBUTE_FILTER_CONTENT = "formPropertyContent";
    private final String MODEL_ATTRIBUTE_FILTER = "formFilterBean";
    private final String SESSION_ATTRIBUTE_PRODUCT_LIST = "productList";
    private final int PRODUCT_LIST_PAGE_SIZE = 21;
    private static final Logger log = Logger.getLogger(ProductController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
        binder.registerCustomEditor(Discount.class, new DiscountEditor(discountService));
        binder.registerCustomEditor(List.class, "product.tasteList", new TasteCustomCollectionEditor(List.class, tasteService));
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_SHOW_ALL_WITH_PAGE, method = RequestMethod.POST)
    public String productsFilterFormRedirect(@PathVariable Long categoryId, FormFilterBean formFilterBean, HttpSession session) {
        session.setAttribute(MODEL_ATTRIBUTE_FILTER + categoryId, formFilterBean);
        session.setAttribute(SESSION_ATTRIBUTE_PRODUCT_LIST + categoryId, null);

        return "redirect:" + ConstantsUri.PRODUCT_SHOW_ALL_FIRST_PAGE;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_SHOW_ALL, method = RequestMethod.GET)
    public String productsRedirect(@PathVariable Long categoryId, FormFilterBean formFilterBean, HttpSession session) {
        session.setAttribute(MODEL_ATTRIBUTE_FILTER + categoryId, null);
        session.setAttribute(SESSION_ATTRIBUTE_PRODUCT_LIST + categoryId, null);

        return "redirect:" + ConstantsUri.PRODUCT_SHOW_ALL_FIRST_PAGE;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_SORT, method = RequestMethod.GET)
    public String sortFilter(@PathVariable Long categoryId, @ModelAttribute("formSortedBean") FormSortedBean formSortedBean, HttpSession session) {
        session.setAttribute(SESSION_ATTRIBUTE_PRODUCT_LIST + categoryId, null);

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_SHOW_ALL_WITH_PAGE, method = RequestMethod.GET)
    public String pagedProductsPage(HttpSession session, @PathVariable Long categoryId, @PathVariable Integer pageNumber, Model uiModel) throws ProductNotFoundException {
        Long start = System.nanoTime();
        FormFilterBean formFilterBean = (FormFilterBean) session.getAttribute(MODEL_ATTRIBUTE_FILTER + categoryId);
        if (formFilterBean == null) {
            formFilterBean = new FormFilterBean();
            session.setAttribute(MODEL_ATTRIBUTE_FILTER + categoryId, formFilterBean);
        }

        FormFilterContent formFilterContent = (FormFilterContent) session.getAttribute(MODEL_ATTRIBUTE_FILTER_CONTENT + categoryId);
        if (pageNumber <= 1 || formFilterContent == null) {
            System.out.println(1);
           formFilterContent = formService.createContentForFilterFormWithoutAmount(categoryId);
          //  formFilterContent = formService.createContentForFilterFormWithAmount(categoryId, formFilterBean);
            session.setAttribute(MODEL_ATTRIBUTE_FILTER_CONTENT + categoryId, formFilterContent);
        }
        uiModel.addAttribute(formFilterBean);
        uiModel.addAttribute(MODEL_ATTRIBUTE_FILTER_CONTENT, formFilterContent);

        PagedListHolder<Product> pagedListHolder = (PagedListHolder<Product>) session.getAttribute(SESSION_ATTRIBUTE_PRODUCT_LIST + categoryId);

        FormSortedBean formSortedBean = (session.getAttribute("formSortedBean") == null) ? new FormSortedBean() : (FormSortedBean) session.getAttribute("formSortedBean");

        if (pagedListHolder == null) {
            List<Product> productList = productService.getProductsByCriteria(categoryId, formFilterBean, formSortedBean.getSortType(), formSortedBean.getProductAvailability());
            if (productList == null || productList.isEmpty()) throw new ProductNotFoundException("products.not.found");

            pagedListHolder = new PagedListHolder<Product>(productList);
            pagedListHolder.setPageSize(PRODUCT_LIST_PAGE_SIZE);
            pagedListHolder.setMaxLinkedPages(productList.size()); //set products Amount
        }

        final int goToPage = pageNumber - 1;
        if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
            pagedListHolder.setPage(goToPage);
        }

        session.setAttribute(SESSION_ATTRIBUTE_PRODUCT_LIST + categoryId, pagedListHolder);

        final String BASE_URL = "/products/" + categoryId + "/page/";

        uiModel.addAttribute(MODEL_ATTRIBUTE_PAGER, Pager.currentPage(pagedListHolder, BASE_URL, PRODUCT_LIST_PAGE_SIZE));
        uiModel.addAttribute(MODEL_ATTRIBUTE_PRODUCTS, pagedListHolder);


        log.info("Time work method: " + (System.nanoTime()-start));

        return ConstantsView.PRODUCT_SHOW_ALL;
    }


    @RequestMapping(value = ConstantsUri.PRODUCT_SHOW, method = RequestMethod.GET)
    public String productShow(@PathVariable("productId") Long productId, Model model) {

        Product product = productService.getProductById(productId);
        log.info("Show product: " + product);
        model.addAttribute("product", product);
        return ConstantsView.PRODUCT_SHOW;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_ADD_FORM, method = RequestMethod.GET)
    public String addProductShowForm(Model uiModel) {
        uiModel.addAttribute("formProductBean", new FormProductBean());

        return ConstantsView.PRODUCT_ADD;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_ADD, method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("formProductBean") FormProductBean productBean,
                             BindingResult result, Model uiModel, RedirectAttributes redirect) {
        if (result.hasErrors())
            return ConstantsView.PRODUCT_ADD;

        Product product = productBean.getProduct();
        try {
            if (productService.productIsExist(product) == true) {
                ServiceMessage.write(uiModel, "product.failure.put.is_exist");
                return ConstantsView.PRODUCT_ADD;
            }
            productService.saveProductWithImage(product, productBean.getFile());
        } catch (DataIntegrityViolationException | IOException e) {
            log.error("Add product " + e);
            ServiceMessage.write(uiModel, "product.failure.put");
            return ConstantsView.PRODUCT_ADD;
        }
        ServiceRedirectMessage.write(redirect, "product.success.add");

        return "redirect:" + ConstantsUri.PRODUCT_ADD_FORM;


    }

    @RequestMapping(value = ConstantsUri.PRODUCT_EDIT_FORM, method = RequestMethod.GET)
    public String showEditProductForm(@PathVariable Long idProduct, Model uiModel, HttpSession session) {
        Product product = productService.getProductById(idProduct);
        if (product.getImageByte() != null)
            session.setAttribute(product.getId().toString(), product.getImageByte());
        uiModel.addAttribute("formProductBean", new FormProductBean(product));

        return ConstantsView.PRODUCT_EDIT;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_EDIT, method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("formProductBean") FormProductBean productBean,
                              BindingResult result, RedirectAttributes redirect, HttpSession session, Model uiModel) {
        String serviceMessage = null;
        if (result.hasErrors())
            return ConstantsView.PRODUCT_EDIT;
        Product product = productBean.getProduct();
        if (!productService.checkBeforeUpdateProduct(product)) {
            ServiceMessage.write(uiModel, "product.failure.put.is_exist");
            return ConstantsView.PRODUCT_EDIT;
        }
        try {
            if (productBean.getFile().getSize() == 0 && session.getAttribute(product.getId().toString()) != null) {
                product.setImageByte((byte[]) session.getAttribute(product.getId().toString()));
            } else {
                product.setImageByte(productBean.getFile().getBytes());
            }
            productService.updateProduct(product);
            serviceMessage = "product.success.edit";
        } catch (Exception e) {
            log.error("Edit product " + e);
            serviceMessage = "product.failure.edit";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.MESSAGE_SHOW;
    }

    @RequestMapping(value = ConstantsUri.PRODUCT_DELETE, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Long idProduct, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        try {
            serviceMessage = (productService.deleteProductById(idProduct) == 0) ? "brand.not.delete" : "product.success.delete";
        } catch (Exception e) {
            log.error("Delete product " + e);
            serviceMessage = "brand.not.delete";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.MESSAGE_SHOW;

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleProductNotFoundException(ProductNotFoundException e) {
        log.error("Product not found " + e);
        return new ModelAndView("redirect:" + ConstantsUri.MESSAGE_SHOW, "message", e.getMessage());
    }


    @ModelAttribute(MODEL_ATTRIBUTE_FILTER_CONTENT)
    public FormPropertyContent getPropertyContent() {
        return formService.createContentForProductForm();
    }

    @ModelAttribute("formSortedBean")
    public FormSortedBean getFormSortedBean() {
        return this.formSortedBean;
    }

    @ModelAttribute("formBuyBean")
    public FormBuyBean getBuyBean() {
        return new FormBuyBean();
    }


}
