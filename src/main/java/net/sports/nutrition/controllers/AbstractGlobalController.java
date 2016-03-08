package net.sports.nutrition.controllers;

import net.sports.nutrition.services.ICategoryService;
import net.sports.nutrition.services.ICountryService;
import net.sports.nutrition.services.IProductService;
import net.sports.nutrition.services.ITasteService;
import net.sports.nutrition.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Parent class for all controllers.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public abstract class AbstractGlobalController {

    @Autowired
    protected IProductService productService;
    @Autowired
    protected ICountryService countryService;
    @Autowired
    protected ICategoryService categoryService;
    @Autowired
    protected ITasteService tasteService;

    /**
     * Writes all categories to the Model
     */
    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAllCategories();
    }

}
