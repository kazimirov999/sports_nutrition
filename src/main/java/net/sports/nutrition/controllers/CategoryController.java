package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import net.sports.nutrition.constants.ConstantsView;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.form.beans.FormCategoryBean;
import net.sports.nutrition.utils.ServiceMessage;
import net.sports.nutrition.utils.ServiceRedirectMessage;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * The Controller is responsible for processing user requests
 * related to categories and building appropriate model and
 * passes it to the view for rendering.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class CategoryController extends AbstractGlobalController {

    final private String MODEL_CATEGORY_FORM_BEAN = "formCategoryBean";

    private static final Logger log = Logger.getLogger(CategoryController.class);

    /**
     * Adds new empty category bean to the Model.
     *
     * @return modelAndView
     * @see FormCategoryBean
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_ADD, method = RequestMethod.GET)
    public String addCategoryShowForm(Model uiModel) {
        uiModel.addAttribute(MODEL_CATEGORY_FORM_BEAN, new FormCategoryBean());

        return ConstantsView.CATEGORY_ADD;
    }

    /**
     * Adds new category.
     *
     * @param categoryBean - contains information about the category
     * @param result       - error register
     * @param uiModel      - model attributes
     * @param redirect     - redirect attributes
     * @return modelAndView
     * @see FormCategoryBean
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_ADD, method = RequestMethod.POST)
    public String addCategory(@Valid @ModelAttribute(MODEL_CATEGORY_FORM_BEAN) FormCategoryBean categoryBean,
                              BindingResult result, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        if (result.hasErrors()) {
            return ConstantsView.CATEGORY_ADD;
        }
        try {
            if (categoryService.categoryIsExist(categoryBean.getCategory()) == true) {
                ServiceMessage.write(uiModel, "category.failure.put.is_exist");
                return ConstantsView.CATEGORY_ADD;
            }
            categoryService.saveCategoryWithImage(categoryBean.getCategory(), categoryBean.getFile());
        } catch (Exception e) {
            log.error("Add category", e);
            ServiceMessage.write(uiModel, "category.failure.add");
            return ConstantsView.CATEGORY_ADD;
        }
        ServiceRedirectMessage.write(redirect, "category.success.add");

        return "redirect:" + ConstantsUri.CATEGORY_ADD;

    }

    /**
     * Removes category.
     *
     * @param id       - category id
     * @param uiModel  - model attributes
     * @param redirect - redirect attributes
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_DELETE, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long id, Model uiModel, RedirectAttributes redirect) {
        String serviceMessage = null;
        try {
            serviceMessage = (categoryService.deleteCategoryById(id) == 0) ? "category.not.delete" : "category.success.delete";
        } catch (Exception e) {
            log.error("Delete category", e);
            ServiceRedirectMessage.write(redirect, "failureMessage", "category.failure.delete");
            redirect.addFlashAttribute("category", categoryService.getCategoryById(id));
            return "redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT;
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT;

    }

    /**
     * Shows removing result.
     *
     * @param uiModel - model attributes
     * @return modelAndView
     * @see FormCategoryBean
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_DELETE_RESULT, method = RequestMethod.GET)
    public String deleteCategoryResult(Model uiModel) {

        return ConstantsView.CATEGORY_DELETE;
    }

    /**
     * Removes all products of category.
     *
     * @param categoryId - category id
     * @param uiModel    - model attributes
     * @param redirect   - redirect attributes
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_DELETE_ALL_PRODUCT, method = RequestMethod.GET)
    public String deleteAllProductsByCategoryId(@PathVariable Long categoryId, Model uiModel, RedirectAttributes redirect) {
        productService.deleteAllProductByCategoryId(categoryId);
        ServiceRedirectMessage.write(redirect, "category.products.success.delete");

        return "redirect:" + ConstantsUri.CATEGORY_DELETE_RESULT;
    }

    /**
     * Shows edits result.
     *
     * @param uiModel - model attributes
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_EDIT_RESULT, method = RequestMethod.GET)
    public String editCategoryResult(Model uiModel) {

        return ConstantsView.CATEGORY_EDIT_RESULT;
    }

    /**
     * Writes category for edit to the Model.
     *
     * @param categoryId - category id
     * @param uiModel    - model attributes
     * @param session    - session between an HTTP client and an HTTP server.
     * @return modelAndView
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_SHOW_EDIT_FORM, method = RequestMethod.GET)
    public String showEditCategory(@PathVariable Long categoryId, Model uiModel, HttpSession session) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category.getImageByte() != null) {
            session.setAttribute(category.getId().toString(), category.getImageByte());
        }
        uiModel.addAttribute(MODEL_CATEGORY_FORM_BEAN, new FormCategoryBean(category));

        return ConstantsView.CATEGORY_EDIT;
    }

    /**
     * Adds edited category.
     *
     * @param categoryBean - contains information about the category
     * @param result       - error register
     * @param uiModel      - model attributes
     * @param redirect     - redirect attributes
     * @return modelAndView
     * @see FormCategoryBean
     */
    @RequestMapping(value = ConstantsUri.CATEGORY_EDIT, method = RequestMethod.POST)
    public String editCategory(@Valid FormCategoryBean categoryBean, BindingResult result,
                               RedirectAttributes redirect, HttpSession session, Model uiModel) {
        String serviceMessage = null;
        if (result.hasErrors()) {
            return ConstantsView.CATEGORY_EDIT;
        }
        try {
            Category category = categoryBean.getCategory();
            if (!categoryService.checkBeforeUpdateCategory(category)) {
                ServiceMessage.write(uiModel, "category.failure.put.is_exist");
                return ConstantsView.CATEGORY_EDIT;
            }

            if (categoryBean.getFile() == null || categoryBean.getFile().getSize() == 0
                    && session.getAttribute(category.getId().toString()) != null) {
                category.setImageByte((byte[]) session.getAttribute(category.getId().toString()));
            } else {
                category.setImageByte(categoryBean.getFile().getBytes());
            }
            categoryService.updateCategory(category);
            serviceMessage = "category.success.edit";
        } catch (Exception e) {
            log.error("Edit category", e);
            serviceMessage = "category.edit.failure";
        }
        ServiceRedirectMessage.write(redirect, serviceMessage);

        return "redirect:" + ConstantsUri.CATEGORY_EDIT_RESULT;
    }

}
