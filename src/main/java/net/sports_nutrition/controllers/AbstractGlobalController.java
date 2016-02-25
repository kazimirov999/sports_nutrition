package net.sports_nutrition.controllers;

import net.sports_nutrition.domain.entities.Category;
import net.sports_nutrition.services.ICategoryService;
import net.sports_nutrition.services.ICountryService;
import net.sports_nutrition.services.IProductService;
import net.sports_nutrition.services.ITasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.02.2016 13:13
 */

public abstract class  AbstractGlobalController {

    @Autowired
    protected IProductService productService;
    @Autowired
    protected ICountryService countryService;
    @Autowired
    protected ICategoryService categoryService;
    @Autowired
    protected ITasteService tasteService;


    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAllCategories();
    }

}