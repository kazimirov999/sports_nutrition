package net.sports_nutrition.utils.converters;

import net.sports_nutrition.services.IBrandService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;


/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 21:15
 */
public class BrandEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(BrandEditor.class);

    final private IBrandService brandService;

    public BrandEditor(IBrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty())
            try {
                this.setValue(brandService.getBrandById(Long.parseLong(id)));
            } catch (Exception e) {
                log.info("Error convert Long to String!!!" + e.getLocalizedMessage());
            }

    }


}

