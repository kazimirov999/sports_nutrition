package net.sports.nutrition.utils.converters;


import net.sports.nutrition.services.IDiscountService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 31.01.2016 2:13
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
