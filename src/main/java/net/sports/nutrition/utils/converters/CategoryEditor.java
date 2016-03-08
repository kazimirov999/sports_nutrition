package net.sports.nutrition.utils.converters;

import net.sports.nutrition.services.ICategoryService;
import net.sports.nutrition.domain.entities.Category;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * This CategoryEditor converting text value(id) to Category object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
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
        if (id != null && !id.isEmpty()) {
            try {
                category = categoryService.getCategoryById(Long.parseLong(id));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
        this.setValue(category);


    }
}
