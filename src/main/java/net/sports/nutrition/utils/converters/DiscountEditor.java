package net.sports.nutrition.utils.converters;


import net.sports.nutrition.services.IDiscountService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * This DiscountEditor converting text values to Discount object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class DiscountEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(DiscountEditor.class);

    final private IDiscountService discountService;

    public DiscountEditor(IDiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {

        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(discountService.getDiscountById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
    }


}
