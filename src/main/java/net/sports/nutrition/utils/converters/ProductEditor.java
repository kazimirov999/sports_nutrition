package net.sports.nutrition.utils.converters;

import net.sports.nutrition.services.IProductService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * This ProductEditor converting text value(id) to Product object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class ProductEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(ProductEditor.class);

    final private IProductService productService;

    public ProductEditor(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(productService.getProductById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
    }
}
