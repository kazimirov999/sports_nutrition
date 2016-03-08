package net.sports.nutrition.utils.converters;

import net.sports.nutrition.services.ICountryService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * This CountryEditor converting text value(id) to Country object.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class CountryEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(CountryEditor.class);

    final private ICountryService countryService;

    public CountryEditor(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(countryService.getCountryById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }

    }
}
