package net.sports_nutrition.utils.converters;

import net.sports_nutrition.domain.entities.Category;
import net.sports_nutrition.services.ICategoryService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 21:15
 */
public class CategoryEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(CategoryEditor.class);

    final private ICategoryService categoryService;

    public CategoryEditor(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {

        Category category = null;
        if (id != null && !id.isEmpty())
            try {
                category = categoryService.getCategoryById(Long.parseLong(id));
            } catch (Exception e) {
                log.info("Error convert Long to String!!!" + e.getLocalizedMessage());
            }

            this.setValue(category);


    }
}
