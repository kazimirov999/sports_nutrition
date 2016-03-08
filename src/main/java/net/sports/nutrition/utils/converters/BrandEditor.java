package net.sports.nutrition.utils.converters;

import net.sports.nutrition.services.IBrandService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;


/**
 * This BrandEditor converting text value(id) to Brand object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class BrandEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(BrandEditor.class);

    final private IBrandService brandService;

    public BrandEditor(IBrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(brandService.getBrandById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }

    }


}

