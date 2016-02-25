package net.sports_nutrition.utils.converters;

import net.sports_nutrition.services.ICountryService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 03.02.2016 14:52
 */
public class CountryEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(CountryEditor.class);

    final private ICountryService countryService;

    public CountryEditor(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty())
            try {
                this.setValue(countryService.getCountryById(Long.parseLong(id)));
            } catch (Exception e) {
                log.info("Error convert Long to String!!!" + e.getLocalizedMessage());
            }

    }
}
