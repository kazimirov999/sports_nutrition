package net.sports_nutrition.utils.converters;

import net.sports_nutrition.services.IProductService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 13:00
 */
public class ProductEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(ProductEditor.class);

    final private IProductService productService;

    public ProductEditor(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty())
            try {
                this.setValue(productService.getProductById(Long.parseLong(id)));
            } catch (Exception e) {
                log.info("Error convert Long to String!!!" + e.getLocalizedMessage());
            }

    }
}
